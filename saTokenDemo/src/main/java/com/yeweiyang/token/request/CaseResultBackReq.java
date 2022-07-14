package com.yeweiyang.token.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package me.zhengjie.web.rep
 * @date 2021/12/30 11:18 上午
 */
@Data
public class CaseResultBackReq implements Serializable {
    /**
     * 案件信息
     */
    private CaseInfo caseInfo;
    /**
     * 理赔信息
     */
    private ClaimInfo claimInfo;
    /**
     * 药品列表
     */
    private List<DrugList> drugList;
    /**
     * 收货信息
     */
    private ReceiveInfo receiveInfo;

}
