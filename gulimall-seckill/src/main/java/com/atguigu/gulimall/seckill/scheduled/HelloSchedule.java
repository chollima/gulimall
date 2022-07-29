package com.atguigu.gulimall.seckill.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@EnableAsync
//@EnableScheduling
@Slf4j
@Component
public class HelloSchedule {

    /**
     * Spring中的cron表达式只允许6位，不允许第7位的年
     * 1-7代表周一到周日，也可以用MON-SUN来表示
     */
    //
    @Async
    @Scheduled(cron = "*/5 * * ? * 1")
    public void hello() {
        log.info("hello......");
    }
}


