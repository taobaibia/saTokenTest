package com.yeweiyang.token.pojo.excelPojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.pojo.excelPojo
 * @date 2021/12/10 3:28 下午
 */
@Data
public class DynamicTablePo implements Serializable {
    //表头名字
    private String filedShowName;
    //表头key值和查询出来的数据的实体相对应
    private String filedCode;

    //表头顺序
    private Integer orderNum;

    //字段数据类型
    private Integer dataType;
}
