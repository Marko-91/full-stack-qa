package tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DelayManager {
    private static final Logger LOG = LogManager.getLogger();

    private DelayManager() {
    }

    public static void sleep() {
        sleep(2000L);
    }

    public static void sleep(long in_waitTimeMs) {
        try {
            Thread.sleep(in_waitTimeMs);
        } catch (InterruptedException var3) {
            LOG.error(var3);
            Thread.currentThread().interrupt();
        }

    }
}