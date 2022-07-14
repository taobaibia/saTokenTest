package com.yeweiyang.token.job;

import com.yeweiyang.token.serivice.ExaminationAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.job
 * @date 2022/1/21 10:45 上午
 */
@Slf4j
@Component
//@EnableScheduling
public class ScheduledTestJob {
    @Autowired
    private ExaminationAppointmentService examinationAppointmentService;


    /*
    * 启动运行
    * */
//    @PostConstruct
//    @Scheduled(cron = "* 10 * * * ?")
    public void deleteTableNullJob(){
        log.info("deleteTableNullJob=====>start         "+System.currentTimeMillis());

        int s = examinationAppointmentService.deleteTableNull();

        log.info("deleteTableNullJob=====>over      数据"+s);
    }

//    @XxlJob("demoJobHandler")
    public void deleteTableNullJob1(){
        log.info("deleteTableNullJob=====>start   xxljob     "+System.currentTimeMillis());

        int s = examinationAppointmentService.deleteTableNull();

        log.info("deleteTableNullJob=====>over  xxljob    数据"+s);
    }



}
