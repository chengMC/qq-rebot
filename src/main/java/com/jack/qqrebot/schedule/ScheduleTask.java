package com.jack.qqrebot.schedule;

import com.jack.qqrebot.service.task.SchedualServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    private final SchedualServiceI schedualService;

    @Autowired
    public ScheduleTask(SchedualServiceI schedualService) {
        this.schedualService = schedualService;
    }

    @Scheduled(cron = "30 0 0 * * ?")
    public void coderCalendar() {
        schedualService.coderCalendar();
    }

    //早上问候（每天早上八点）
    @Scheduled(cron = "0 0 8 * * ?")
    public void goodMorning() {
        schedualService.goodMorning();
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void historyOnToday() {
        schedualService.leetCode();
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void snhMember() {
        schedualService.sNHMember();
    }

    //微博热搜
    @Scheduled(cron = "0 40 11 * * ?")
    public void sendWeiBo(){
        schedualService.weibo();
    }

    //每日新闻
    @Scheduled(cron = "0 30 13,20 * * ?")
    public void article(){
        schedualService.articles();
    }

    //每日新闻
    @Scheduled(cron = "0 30 17 * * ?")
    public void everyDayNews(){
        schedualService.everyDayNews();
    }

    @Scheduled(cron = "0 30 22 * * ?")
    public void light(){
        schedualService.goodLight();
    }


}
