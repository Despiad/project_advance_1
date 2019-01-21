package util;


import io.qameta.allure.junit4.DisplayName;
import main.tests.TestWithSmallSize;
import org.apache.log4j.Logger;
import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;


import java.util.concurrent.TimeUnit;

public class MyJUnitStopWatch extends Stopwatch {

    private static Logger logger = Logger.getLogger(MyJUnitStopWatch.class);

    private static void logInfo(Description description, String status, long nanos) {
        DisplayName methodAnnotation = description.getAnnotation(DisplayName.class);
        logger.info(String.format("Test '%s' %s, spent %d microseconds",
                methodAnnotation.value(), status, TimeUnit.NANOSECONDS.toMicros(nanos)));
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}
