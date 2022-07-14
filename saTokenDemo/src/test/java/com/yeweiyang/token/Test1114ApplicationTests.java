package com.yeweiyang.token;

import com.alibaba.fastjson.JSON;
import com.yeweiyang.token.request.CaseResultBackReq;
import com.yeweiyang.token.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Test1114ApplicationTests {
	@Resource
	private RedisUtils redisUtils;

	/**
	 * 插入缓存数据
	 */
	@Test
	public void set() {
		redisUtils.set("redis_key", "redis_vale");
	}

	/**
	 * 读取缓存数据
	 */
	@Test
	public void get() {
		String value = redisUtils.get("name");
		System.out.println(value);
	}
	@Test
	void contextLoads() {
		String data = "{\\\"caseInfo\\\":{\\\"auditResult\\\":\\\"4\\\",\\\"caseNumber\\\":\\\"2021122000002677\\\",\\\"remark\\\":\\\"1\\\"},\\\"claimInfo\\\":{\\\"bsPayAmt\\\":\\\"99\\\",\\\"bsPayDate\\\":\\\"20211221124816\\\",\\\"caseType\\\":\\\"2\\\",\\\"drugTotalAmt\\\":\\\"99\\\",\\\"medicalAmt\\\":\\\"99\\\",\\\"personPayAmt\\\":\\\"99\\\"},\\\"drugList\\\":[{\\\"drugAudit\\\":\\\"1\\\",\\\"drugAuditDesc\\\":\\\"通过\\\",\\\"drugBussName\\\":\\\"可瑞达\\\",\\\"drugCode\\\":\\\"1018\\\",\\\"drugFactory\\\":\\\"默沙东\\\",\\\"drugName\\\":\\\"帕博利珠单抗注射液\\\",\\\"drugPrice\\\":\\\"null\\\",\\\"drugSpec\\\":\\\"34\\\",\\\"quantity\\\":\\\"1.00\\\"}],\\\"receiveInfo\\\":{\\\"actualDeliveryDate\\\":\\\"20211221124816\\\",\\\"auditResult\\\":\\\"1\\\"}}";
		CaseResultBackReq caseResultBackReq = JSON.parseObject(data, CaseResultBackReq.class);
		System.out.println(caseResultBackReq);
	}

}
