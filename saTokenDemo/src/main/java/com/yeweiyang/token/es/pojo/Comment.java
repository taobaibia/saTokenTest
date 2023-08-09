package com.yeweiyang.token.es.pojo;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.es.pojo
 * @date 2023/7/24 15:18
 */

import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.JoinField;

/**
 * 子文档
 */
@IndexName(child = true)
public class Comment {
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String eat;
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String food;
    @IndexField(fieldType = FieldType.KEYWORD_TEXT)
    private String address;
    /**
     * 父子关系字段 须通过注解在父文档及子文档的实体类中指明其类型为Join,子文档中的父子关系可省略
     */
    @IndexField(fieldType = FieldType.JOIN)
    private JoinField joinField;
}
