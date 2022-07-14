package com.yeweiyang.token;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@MapperScan(basePackages = {"com.shanghai.test1114.mapper"})

//定时任务启动类
@EnableScheduling
public class SatokenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatokenDemoApplication.class, args);
		log.info("=========start=========");
		System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
	}
}



