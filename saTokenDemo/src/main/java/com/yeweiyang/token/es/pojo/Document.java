package com.yeweiyang.token.es.pojo;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.es.pojo
 * @date 2023/7/24 15:17
 */

import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.JoinField;

import java.util.Date;

/**
 * 父文档
 */
@IndexName(childClass = Comment.class)
public class Document{
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
     * 须通过注解在父文档及子文档的实体类中指明其类型为Join,及其父名称和子名称,这里的JoinField类框架已内置,无需重复造轮子
     * JoinField类全路径为cn.easyes.common.params.JoinField,如果你非要自己造轮子,也支持,那么需要在@TableField注解中指明joinFieldClass=你造的轮子
     */
    @IndexField(fieldType = FieldType.JOIN, parentName = "document", childName = "comment")
    private JoinField joinField;
}


