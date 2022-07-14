package com.yeweiyang.token.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package me.zhengjie.modules.claim.domain.pojo
 * @date 2022/1/6 1:14 下午
 * 案件信息
 * 表    claim_main
 */
@Data
public class CaseInfo implements Serializable {
    /**
     * 赔案号
     */
    private String caseNumber;
    /**
     * 审核结果
     */
    private String auditResult;
    /**
     * 审核备注
     */
    private String remark;

}
