package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Deliberately vulnerable demo for ChainGuard. NEVER deploy: it logs untrusted input with Log4j. */
public final class Greeter {
    private static final Logger LOG = LogManager.getLogger(Greeter.class);

    private Greeter() {}

    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "world";
        LOG.info("Hello, {}", name); // with log4j-core 2.14.1, a name such as ${jndi:...} is looked up (Log4Shell)
    }
}
