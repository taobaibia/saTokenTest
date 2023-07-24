package com.yeweiyang.token.es.pojo;

import lombok.Data;
import org.dromara.easyes.annotation.HighLight;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.IdType;

import java.util.Date;
import java.util.List;


/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.es.pojo
 * @date 2023/7/18 13:44
 */
@Data
@IndexName(value = "estestnestedquery1_s0",shardsNum = 1,replicasNum = 2)
public class EsTestNestedTest1 {
    /**
     * es中的唯一id
     */
//    @IndexId(type = IdType.NONE)
    private String id;
    /**
     * 文档标题 不指定类型默认被创建为keyword_text类型,可进行精确查询
     */
    private String title;
    /**
     * 文档内容
     * 指定了类型及存储/查询分词器
     */
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String dataInfo;

    @IndexField(fieldType = FieldType.DATE)
    private Date createTime;

    /**
     * 嵌套类型
     */
    @IndexField(fieldType = FieldType.NESTED, nestedClass = EsTestNestedChildTest.class)
    private List<EsTestNestedChildTest> esTestNestedChildTestList;
}