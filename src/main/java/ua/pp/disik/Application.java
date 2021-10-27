package ua.pp.disik;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class Application {
    @SneakyThrows
    public static void main(String[] args) {
        log.debug("Execution1: {}", executionTime(args, Application::execute1));
        log.debug("Execution2: {}", executionTime(args, Application::execute2));
    }

    @SneakyThrows
    public static void execute1(String[] args) {
        FutureTask<Integer> future1 = new FutureTask<>(Application::getRandomWithLatency);
        FutureTask<Integer> future2 = new FutureTask<>(Application::getRandomWithLatency);
        FutureTask<Integer> future3 = new FutureTask<>(Application::getRandomWithLatency);

        new Thread(future1).start();
        int result1 = future1.get();

        new Thread(future2).start();
        int result2 = future2.get();

        new Thread(future3).start();
        int result3 = future3.get();

        log.debug("{}, {}, {}", result1, result2, result3);
    }

    @SneakyThrows
    public static void execute2(String[] args) {
        FutureTask<Integer> future1 = new FutureTask<>(Application::getRandomWithLatency);
        FutureTask<Integer> future2 = new FutureTask<>(Application::getRandomWithLatency);
        FutureTask<Integer> future3 = new FutureTask<>(Application::getRandomWithLatency);


        new Thread(future1).start();
        new Thread(future2).start();
        new Thread(future3).start();

        int result1 = future1.get();
        int result2 = future2.get();
        int result3 = future3.get();

        log.debug("{}, {}, {}", result1, result2, result3);
    }

    @SneakyThrows
    private static int getRandomWithLatency() {
        Thread.sleep(1000);
        return RandomUtils.nextInt();
    }

    private static long executionTime(String[] args, Consumer<String[]> consumer) {
        long start = System.currentTimeMillis();

        consumer.accept(args);

        return System.currentTimeMillis() - start;
    }
}
