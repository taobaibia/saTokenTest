package com.yeweiyang.token.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package me.zhengjie.modules.claim.domain.request
 * @date 2022/1/10 5:17 下午
 */
@Data
public class CaseReq implements Serializable {
    private CaseResultBackReq caseResultBackReq;
    private String cid;
    private String sign;
    private String ts;
    private String uuid;
}
