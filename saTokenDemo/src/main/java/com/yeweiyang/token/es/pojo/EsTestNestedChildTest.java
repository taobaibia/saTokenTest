package com.yeweiyang.token.es.pojo;

import lombok.Data;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.rely.FieldType;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.es.pojo
 * @date 2023/7/20 17:03
 */
@Data
//@IndexName(value = "estestnestedchildtest1_s0",shardsNum = 1,replicasNum = 2)
public class EsTestNestedChildTest {
    /**
     * es中的唯一id
     */
//    @IndexId(type = IdType.NONE)
    private String sonId;
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String eat;
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String food;
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String address;
}
