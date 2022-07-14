package com.yeweiyang.token.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package me.zhengjie.modules.claim.domain.request
 * @date 2022/1/6 1:18 下午
 * 理赔信息
 */
@Data
public class ClaimInfo implements Serializable {
    /**
     * 理赔类型
     */
    private String caseType;
    /**
     * 理赔结论
     */
    private String conclusion;
    /**
     * 药品总金额
     */
    private Double drugTotalAmt;
    /**
     * 医保统筹金额
     */
    private Double medicalAmt;
    /**
     * 保险公司赔付金额
     */
    private Double bsPayAmt;
    /**
     * 个人自付金额
     */
    private Double personPayAmt;
    /**
     * 理赔金支付时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String bsPayDate;
}
