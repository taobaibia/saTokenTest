package com.yeweiyang.token.es.pojo;

import lombok.Data;
import org.dromara.easyes.annotation.HighLight;
import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.IndexId;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.rely.Analyzer;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.annotation.rely.IdType;

import java.util.Date;


/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.token.es.pojo
 * @date 2023/7/18 13:44
 */
@Data
@IndexName(value = "documenttest1",shardsNum = 3,replicasNum = 2)
public class DocumentTest1 {
    /**
     * es中的唯一id
     */
    @IndexId(type = IdType.NONE)
    private String id;
    /**
     * 文档标题 不指定类型默认被创建为keyword_text类型,可进行精确查询
     */
    private String title;
    /**
     * 文档内容
     * 指定了类型及存储/查询分词器
     */
    @HighLight(mappingField="applyNo")
    @IndexField(fieldType = FieldType.TEXT)
    private String content;

    @IndexField(fieldType = FieldType.DATE)
    private Date createTime;
}


/*
* public class Document {
    // 此处省略其它字段...

    // 场景一:标记es中不存在的字段
    @IndexField(exist = false)
    private String notExistsField;

    // 场景二:更新时,此字段非空字符串才会被更新
    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String creator;

    // 场景三: 指定fieldData
    @IndexField(fieldType = FieldType.TEXT, fieldData = true)
    private String filedData;

    // 场景四:自定义字段名
    @IndexField("wu-la")
    private String ula;

    // 场景五:支持日期字段在es索引中的format类型
    @IndexField(fieldType = FieldType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private String gmtCreate;

    // 场景六:支持指定字段在es索引中的分词器类型
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String content;

    // 场景七：支持指定字段在es的索引中忽略大小写,以便在term查询时不区分大小写,仅对keyword类型字段生效,es的规则,并非框架限制.
    @IndexField(fieldType = FieldType.KEYWORD, ignoreCase = true)
    private String caseTest;
}
其中场景四和场景五仅在索引自动托管模式下生效,如果开启了手动处理索引模式,则需要用户通过手动调用我提供的API传入相应的分词器及日期格式化参数进行索引的创建/更新.

#
* */