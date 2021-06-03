package com.zwh.staservice.schedule;

import com.zwh.staservice.service.StatisticsDailyService;
import com.zwh.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;



    //corn表达式生成工具：https://cron.qqe2.com/ 最多六位，七位报错

    //每五秒执行一次
//    @Scheduled(cron="0/5 * * * * ?")
//    public void task1(){
//        System.out.println("-----------------------");
//    }

    //每天凌晨一点执行一次，将前一天的数据添加到数据库
    @Scheduled(cron="0 0 1 * * ?")
    public void task2(){
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }

}
