package com.yeweiyang.token.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package me.zhengjie.modules.claim.domain.pojo
 * @date 2022/1/6 1:22 下午
 * 收货信息
 * 表    claim_drug_order_info
 */
@Data
public class ReceiveInfo implements Serializable {
    /**
     * 配送结果
     */
    private String auditResult;
    /**
     * 实际送达时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String actualDeliveryDate;
}
