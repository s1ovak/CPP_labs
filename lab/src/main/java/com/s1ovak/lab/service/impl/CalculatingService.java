package com.s1ovak.lab.service.impl;

import com.s1ovak.lab.cache.Cache;
import com.s1ovak.lab.cache.CacheMap;
import com.s1ovak.lab.cache.InputParameters;
import com.s1ovak.lab.counter.Counter;
import com.s1ovak.lab.entity.*;
import com.s1ovak.lab.models.Input;
import com.s1ovak.lab.models.Output;
import com.s1ovak.lab.service.InputService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Component
public class CalculatingService {

    private static final Logger logger = Logger.getLogger(CalculatingService.class);

    private Counter counter;

    private CacheMap cache;
    private final Cache futureCache;
    private final InputService inputService;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    public CalculatingService(CacheMap cache, Counter counter, InputService inputService, Cache futureCache) {
        this.cache = cache;
        this.counter = counter;
        this.inputService = inputService;
        this.futureCache = futureCache;
    }

    public AnswerModel checkCache(String string, String symbol) {
        return cache.getMap().get(new InputParameters(string, symbol));
    }

    public void addIntoCache(String string, String symbol, AnswerModel answerModel) {
        cache.add(new InputParameters(string, symbol), answerModel);
    }

    public AnswerModel getEntity(String string, String symbol) {

        if (logger.isDebugEnabled()) {
            logger.debug("getEntity method is called!");
        }
        if (string == null || string.equals("")) {
            return isError("Invalid params! Need parameter string");
        }

        if (symbol == null || symbol.equals("")) {
            return isError("Invalid params! Need parameter symbol");
        }


        if (symbol.length() != 1) {
            return isError("Invalid params! Param symbol must be char");
        }

        logger.debug("getEntity method is successfully completed");
        this.increment();

        AnswerModel cacheResult = this.checkCache(string, symbol);


        if (cacheResult == null) {
            AnswerModel answerModel = this.calculateSymbol(string, symbol);
            this.addIntoCache(string, symbol, answerModel);
            return answerModel;
        }

        return cacheResult;


    }

    public synchronized void increment() {
        counter.inc();
        logger.info("Incremented count, current: " + counter.getCounter());
    }

    public AnswerModel calculateSymbol(String string, String symbol) {
        AnswerModel count = new AnswerModel(0);
        char checkedSymbol = symbol.charAt(0);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == checkedSymbol) {
                count.setNumber(count.getNumber() + 1);
            }
        }

        return count;
    }

    private AnswerModel isError(String msg) {
        logger.error(msg);
        return new AnswerModel(msg);
    }


    ///////////////////////////////////////lab 8, asynchronous call
    public synchronized Answers checkList(InputList list) {
        Answers answers = new Answers();

        for(InputParameters value : list.getParameters()) {
            AnswerModel answer = getEntity(value.getString(), value.getSymbol());
            answers.add(answer);

            this.inputService.saveValue(new Input(value.getString(), value.getSymbol(),
                    new Output(answer.getErrorMessage(), answer.getNumber())));
        };
        return answers;
    }


    public Integer getResponseId(InputList list) {
        Future<Answers> future = this.calculate(list);
        return this.futureCache.add(future);
    }


    public Answers getAnswers(Integer num){
        Future<Answers> answersFuture = this.futureCache.getMap().get(num);

        if(answersFuture.isDone()) {
            try {
                return answersFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Future<Answers> calculate(InputList list) {
        return executor.submit(() -> {
            Answers answers = new Answers();

            answers = this.checkList(list);
            return answers;
        });
    }
    ////////////////////////////////////////////////////////////////////

    public Long calculateInputErrors(Answers answers) {
        Stream<AnswerModel> errorAnswers = answers.getResultList()
                .stream().filter(value -> value.getErrorMessage() != null
                );

        return errorAnswers.count();
    }

    public Integer findMin(Answers answers) {
        return answers.getResultList()
                .stream().filter(value -> value.getErrorMessage() == null)
                .min(Comparator.comparing(AnswerModel::getNumber)).get().getNumber();
    }

    public Integer findMax(Answers answers) {
        return answers.getResultList()
                .stream().filter(value -> value.getErrorMessage() == null)
                .max(Comparator.comparing(AnswerModel::getNumber)).get().getNumber();
    }

    public Integer findMostPopular(Answers answers) {
        Map<Integer, Integer> counters = new HashMap<>();

        Stream<AnswerModel> stream = answers.getResultList().stream()
                .filter(value -> value.getErrorMessage() == null);

        stream.forEach(value -> {
            Integer num = value.getNumber();
            if (counters.containsKey(num)) {
                counters.replace(num, counters.get(num) + 1);
            } else {
                counters.put(num, 1);
            }
        });

        Integer maxKey = 0;
        Integer maxValue = 0;
        for (Map.Entry entry : counters.entrySet()) {
            Integer value = (Integer) entry.getValue();
            if (maxValue < value) {
                maxValue = value;
                maxKey = (Integer) entry.getKey();
            }
        }
        return maxKey;
    }

    public StatisticAnswer calculateStatistic(InputList list) {
        StatisticAnswer statisticAnswer = new StatisticAnswer();

        Answers answers = checkList(list);
        statisticAnswer.setAnswers(answers);
        statisticAnswer.setTotalInputAmount(list.getParameters().size());
        statisticAnswer.setTotalIncorrectInputAmount(this.calculateInputErrors(answers));
        statisticAnswer.setMinResult(this.findMin(answers));
        statisticAnswer.setMaxResult(this.findMax(answers));
        statisticAnswer.setMostPopular(findMostPopular(answers));

        return statisticAnswer;
    }

    public static Character[] boxChar(char[] chars) {
        return IntStream.range(0, chars.length)
                .mapToObj((i) -> chars[i])
                .toArray(Character[]::new);
    }

    public static Integer evaluate(Character[] characters, Predicate<Character> predicate) {
        Integer i = 0;

        for (Character n : characters) {
            if (predicate.test(n)) {
                i++;
            }
        }

        return i;
    }
}
