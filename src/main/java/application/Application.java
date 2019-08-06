package application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);


    public static void main(String[] args) {
        logger.debug("Start main ---------------- ");
        SpringApplication.run(Application.class, args);
    }
}
