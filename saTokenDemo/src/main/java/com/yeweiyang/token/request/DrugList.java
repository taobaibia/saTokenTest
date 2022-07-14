package com.yeweiyang.token.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package me.zhengjie.modules.claim.domain.pojo
 * @date 2022/1/6 1:21 下午
 * 药品列表----结果返回
 * 表    claim_drug_info
 */
@Data
public class DrugList implements Serializable {
    /**
     * 药品名称
     */
    private String drugName;
    /**
     * 药品单价
     */
    private Double drugPrice;
    /**
     * 药品代码
     */
    private String drugCode;
    /**
     * 商品名称
     */
    private String drugBussName;
    /**
     * 药品数量
     */
    private String quantity;
    /**
     * 药品厂家
     */
    private String drugFactory;
    /**
     * 规格型号
     */
    private String drugSpec;
    /**
     * 药品审核结果
     */
    private String drugAudit;
    /**
     * 药品审核备注
     */
    private String drugAuditDesc;
}
