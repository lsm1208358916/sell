package com.mcit.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    public static Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void test() {
        String str = "lsm";
        logger.info("info:{}",str);
        logger.debug("debug:{}",str);
        logger.error("error:{}",str);
    }
}
