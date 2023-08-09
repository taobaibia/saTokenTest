package com.yeweiyang.es;

import com.yeweiyang.token.SatokenDemoApplication;
import com.yeweiyang.token.es.esMapper.DocumentTest1Mapper;
import com.yeweiyang.token.es.pojo.DocumentTest1;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.common.utils.Assert;
import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.biz.SAPageInfo;
import org.dromara.easyes.core.conditions.index.LambdaEsIndexWrapper;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.es
 * @date 2023/7/18 13:47
 * 普通crud
 */
@Disabled
@SpringBootTest(classes = SatokenDemoApplication.class)
public class EsTest {

    @Resource
    private DocumentTest1Mapper documentTest1Mapper;

    @Test
    public void testCreateIndex() {
        LambdaEsIndexWrapper<DocumentTest1> indexWrapper = EsWrappers.lambdaIndex(DocumentTest1.class)
                .indexName(DocumentTest1.class.getSimpleName().toLowerCase(Locale.ROOT))
                //keyword类型(不支持分词),文档内容映射为text类型(支持分词查询)
                .mapping(DocumentTest1::getTitle, FieldType.TEXT, 2.0f)
//                .mapping(Document::getLocation, FieldType.GEO_POINT)
//                .mapping(Document::getGeoLocation, FieldType.GEO_SHAPE)
//                .mapping(Document::getContent, FieldType.TEXT, Analyzer.IK_SMART, Analyzer.IK_MAX_WORD);
                //设置分片及副本信息,可缺省
                .settings(3, 2)
                // 设置别名信息,可缺省
                .createAlias("daily")
                //设置父子信息,若无父子文档关系则无需设置
//                .join("joinField", "document", "comment")
                ;


        // 测试创建索引,框架会根据实体类及字段上加的自定义注解一键帮您生成索引 需确保索引托管模式处于manual手动挡(默认处于此模式),若为自动挡则会冲突
//        boolean success = documentTest1Mapper.createIndex(indexWrapper);
        boolean success = documentTest1Mapper.createIndex();
        Assertions.assertTrue(success);
    }
    @Test
    public void deleteIndexTest(){

        Boolean aBoolean = documentTest1Mapper.deleteIndex("documenttest1");
        Assertions.assertTrue(aBoolean);
    }

    @Test
    public void testInsert() {
        // 测试插入数据
        DocumentTest1 document = new DocumentTest1();
        document.setTitle("老汉");
        document.setContent("applyNo=13123123123,id=100");
        document.setCreateTime(new Date());
//        documentTest1Mapper.setCurrentActiveIndex("123");//自定义索引
        int successInsert = documentTest1Mapper.insert(document);
        Assertions.assertTrue(successInsert==1);
    }
    @Test
    public void testInsert1() {
        // 测试插入数据
        DocumentTest1 document = new DocumentTest1();
        document.setTitle("drug");
        document.setContent("drug 英飞凡\n 90ml/3");
        document.setCreateTime(new Date());
//        documentTest1Mapper.setCurrentActiveIndex("123");//自定义索引
        int successInsert = documentTest1Mapper.insert(document);
        Assertions.assertTrue(successInsert==1);
    }

    @Test
    public void testSelect() {
        // 测试查询 写法和MP一样 可以用链式,也可以非链式 根据使用习惯灵活选择即可
        String title = "老汉";


        List<DocumentTest1> document = documentTest1Mapper.selectList(
                EsWrappers.lambdaQuery(DocumentTest1.class).eq(DocumentTest1::getTitle, title)
        );
        System.out.println(document);
        Assert.isTrue(title.equals(document.get(0).getTitle()), document.get(0).getTitle());

        //        在对应的参数中指定当前操作作用的索引,例如 wrapper.index(String indexName),通过此API修改索引名称后,仅作用于该wrapper对应的操作,粒度最细.
//        esQueryWrapper.index("");
    }

    @Test
    public void testSelectAll() {
        // 测试查询 写法和MP一样 可以用链式,也可以非链式 根据使用习惯灵活选择即可
        String title = "老汉";
        LambdaEsQueryWrapper<DocumentTest1> esQueryWrapper = new LambdaEsQueryWrapper<>();
//        esQueryWrapper.eq(DocumentTest1::getTitle, title);
        List<DocumentTest1> document = documentTest1Mapper.selectList(esQueryWrapper);
        System.out.println(document);
        Assert.isTrue(title.equals(document.get(0).getTitle()), document.get(0).getTitle());
    }

    @Test
    public void selectIndexes() throws Exception {
        // 测试是否存在指定名称的索引
        String indexName = DocumentTest1.class.getSimpleName().toLowerCase(Locale.ROOT);
        boolean existsIndex = documentTest1Mapper.existsIndex(DocumentTest1.class.getSimpleName().toLowerCase(Locale.ROOT));
        Assertions.assertTrue(existsIndex);
    }

    @Test
    public void updateIndexes() throws Exception {
        LambdaEsIndexWrapper<DocumentTest1> indexWrapper = EsWrappers.lambdaIndex(DocumentTest1.class)
                .indexName(DocumentTest1.class.getSimpleName().toLowerCase(Locale.ROOT))
                //keyword类型(不支持分词),文档内容映射为text类型(支持分词查询)
                .mapping(DocumentTest1::getTitle, FieldType.TEXT, 2.0f)
//                .mapping(Document::getLocation, FieldType.GEO_POINT)
//                .mapping(Document::getGeoLocation, FieldType.GEO_SHAPE)
//                .mapping(Document::getContent, FieldType.TEXT, Analyzer.IK_SMART, Analyzer.IK_MAX_WORD);
                //设置分片及副本信息,可缺省
                .settings(3, 2)
                // 设置别名信息,可缺省
                .createAlias("daily")
                //设置父子信息,若无父子文档关系则无需设置
                .join("joinField", "document", "comment");
        Boolean aBooleanIndex = documentTest1Mapper.updateIndex(indexWrapper);
        Assertions.assertTrue(aBooleanIndex);
    }

    @Test
    public void pageEsQuery() throws Exception {
        Date parse = new Date(2023 - 1900, 6, 19, 18, 3, 49); // 注意月份从0开始，6 表示 7 月
        long timestamp = parse.getTime(); // 获取 Unix 时间戳
        LambdaEsQueryWrapper<DocumentTest1> queryWrapper = EsWrappers.lambdaQuery(DocumentTest1.class)
//                .eq(DocumentTest1::getTitle, "老汉")
//                .ge(DocumentTest1::getCreateTime,timestamp)
                .likeLeft(DocumentTest1::getContent,"100")
                ;
        EsPageInfo<DocumentTest1> esPageInfo = documentTest1Mapper.pageQuery(queryWrapper, 1, 10);
        System.out.println(esPageInfo);
    }
    @Test
    public void testSearchAfter() {
        LambdaEsQueryWrapper<DocumentTest1> lambdaEsQueryWrapper = EsWrappers.lambdaQuery(DocumentTest1.class);
        lambdaEsQueryWrapper.size(10);
        // 必须指定一种排序规则,且排序字段值必须唯一 此处我选择用id进行排序 实际可根据业务场景自由指定,不推荐用创建时间,因为可能会相同
        lambdaEsQueryWrapper.orderByDesc(DocumentTest1::getId);
        SAPageInfo<DocumentTest1> saPageInfo = documentTest1Mapper.searchAfterPage(lambdaEsQueryWrapper, null, 10);
        // 第一页
        System.out.println(saPageInfo);
//        Assertions.assertEquals(10, saPageInfo.getList().size());

        // 获取下一页
        List<Object> nextSearchAfter = saPageInfo.getNextSearchAfter();
        SAPageInfo<DocumentTest1> next = documentTest1Mapper.searchAfterPage(lambdaEsQueryWrapper, nextSearchAfter, 10);
        Assertions.assertEquals(10, next.getList().size());
    }





    /*
        这里面的or是嵌套or
        where name = '老汉' or (age = 18 and size = 18)
        用MP或EE来写就是
        wrapper.eq(name,"老汉").or(i->i.eq(age,18).eq(size,18));
    * */
}
