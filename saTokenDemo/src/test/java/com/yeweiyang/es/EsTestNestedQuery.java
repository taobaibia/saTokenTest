package com.yeweiyang.es;

import com.yeweiyang.token.SatokenDemoApplication;
import com.yeweiyang.token.es.esMapper.EsTestNestedChildTestMapper;
import com.yeweiyang.token.es.esMapper.EsTestNestedTest1Mapper;
import com.yeweiyang.token.es.pojo.EsTestNestedChildTest;
import com.yeweiyang.token.es.pojo.EsTestNestedTest1;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.common.utils.Assert;
import org.dromara.easyes.core.biz.EsPageInfo;
import org.dromara.easyes.core.biz.SAPageInfo;
import org.dromara.easyes.core.conditions.index.LambdaEsIndexWrapper;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.dromara.easyes.core.toolkit.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.es
 * @date 2023/7/20 16:21
 */
@Disabled
@SpringBootTest(classes = SatokenDemoApplication.class)
public class EsTestNestedQuery {

    @Resource
    private EsTestNestedTest1Mapper esTestNestedTest1Mapper;
    @Resource
    private EsTestNestedChildTestMapper esTestNestedChildTestMapper;

    @Test
    public void testCreateIndex() {
        LambdaEsIndexWrapper<EsTestNestedTest1> indexWrapper = EsWrappers.lambdaIndex(EsTestNestedTest1.class)
                .indexName(EsTestNestedTest1.class.getSimpleName().toLowerCase(Locale.ROOT))
                //keyword类型(不支持分词),文档内容映射为text类型(支持分词查询)
                .mapping(EsTestNestedTest1::getTitle, FieldType.TEXT, 2.0f)
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
//        boolean success = esTestNestedTest1Mapper.createIndex(indexWrapper);
        boolean success = esTestNestedTest1Mapper.createIndex();
        Assertions.assertTrue(success);
    }

    @Test
    public void deleteIndexTest() {

        Boolean aBoolean = esTestNestedTest1Mapper.deleteIndex("estestnestedquery1","estestnestedchildtest1_s0","estestnestedquery1_s0");
        Assertions.assertTrue(aBoolean);

        testInsert();
    }

    @Test
    public void testInsert() {
        // 测试插入数据
        EsTestNestedTest1 document = new EsTestNestedTest1();
        document.setTitle("嵌套查询");
        document.setDataInfo("吃喝玩乐");
        document.setCreateTime(new Date());
//        esTestNestedTest1Mapper.setCurrentActiveIndex("123");//自定义索引

        ArrayList<EsTestNestedChildTest> childTestList = new ArrayList<>();
        EsTestNestedChildTest esTestNestedChildTest1 = new EsTestNestedChildTest();
        esTestNestedChildTest1.setEat("吃");
        esTestNestedChildTest1.setFood("火锅");
        esTestNestedChildTest1.setAddress("海岛");

        EsTestNestedChildTest esTestNestedChildTest2 = new EsTestNestedChildTest();
        esTestNestedChildTest2.setEat("he");
        esTestNestedChildTest2.setFood("pijiu");
        esTestNestedChildTest2.setAddress("德国啤酒节");


        childTestList.add(esTestNestedChildTest1);
        childTestList.add(esTestNestedChildTest2);
        document.setEsTestNestedChildTestList(childTestList);
        int successInsert = esTestNestedTest1Mapper.insert(document);
        Assertions.assertTrue(successInsert == 1);
    }

    @Test
    public void testInsert1() {
        // 测试插入数据
        EsTestNestedTest1 document = new EsTestNestedTest1();
        document.setTitle("drug");
        document.setDataInfo("drug 英飞凡\n 90ml/3");
        document.setCreateTime(new Date());
//        esTestNestedTest1Mapper.setCurrentActiveIndex("123");//自定义索引
        int successInsert = esTestNestedTest1Mapper.insert(document);
        Assertions.assertTrue(successInsert == 1);
    }

    @Test
    public void testSelect() {
        // 测试查询 写法和MP一样 可以用链式,也可以非链式 根据使用习惯灵活选择即可
        String title = "嵌套查询";


        List<EsTestNestedTest1> document = esTestNestedTest1Mapper.selectList(
                EsWrappers.lambdaQuery(EsTestNestedTest1.class).eq(EsTestNestedTest1::getTitle, title)
        );
        System.out.println(document);
        Assert.isTrue(title.equals(document.get(0).getTitle()), document.get(0).getTitle());

        //        在对应的参数中指定当前操作作用的索引,例如 wrapper.index(String indexName),通过此API修改索引名称后,仅作用于该wrapper对应的操作,粒度最细.
//        esQueryWrapper.index("");
    }

    @Test
    public void testSelectAll() {
        // 测试查询 写法和MP一样 可以用链式,也可以非链式 根据使用习惯灵活选择即可
        String title = "嵌套查询";
        LambdaEsQueryWrapper<EsTestNestedTest1> esQueryWrapper = new LambdaEsQueryWrapper<>();
//        esQueryWrapper.eq(EsTestNestedTest1::getTitle, title);
        List<EsTestNestedTest1> document = esTestNestedTest1Mapper.selectList(esQueryWrapper);
        System.out.println(document);
        Assert.isTrue(title.equals(document.get(0).getTitle()), document.get(0).getTitle());
    }

    @Test
    public void selectIndexes() throws Exception {
        // 测试是否存在指定名称的索引
        String indexName = EsTestNestedTest1.class.getSimpleName().toLowerCase(Locale.ROOT);
        String fatherName = "estestnestedquery1";
        String son = "estestnestedchildtest1";
        boolean existsIndex = esTestNestedTest1Mapper.existsIndex(son);
        Assertions.assertTrue(existsIndex);
    }

    @Test
    public void son() {

//        estestnestedquery1
//        estestnestedchildtest1
        Boolean aBoolean = esTestNestedTest1Mapper.deleteIndex("estestnestedquery1", "estestnestedchildtest1");
        Assertions.assertTrue(aBoolean);

        LambdaEsIndexWrapper<EsTestNestedTest1> wrapper = new LambdaEsIndexWrapper<>();
        wrapper.mapping(EsTestNestedTest1::getEsTestNestedChildTestList, FieldType.NESTED)
                .indexName("estestnestedquery1")
        ;
        Boolean index = esTestNestedTest1Mapper.createIndex(wrapper);
        Assertions.assertTrue(index);
        LambdaEsIndexWrapper<EsTestNestedChildTest> wrapper1 = new LambdaEsIndexWrapper<>();
        wrapper1.mapping(EsTestNestedChildTest::getSonId, FieldType.NESTED)
                .indexName("estestnestedchildtest1");
        Boolean index1 = esTestNestedChildTestMapper.createIndex(wrapper1);
        Assertions.assertTrue(index1);

    }

    @Test
    public void updateIndexes() throws Exception {
        LambdaEsIndexWrapper<EsTestNestedTest1> indexWrapper = EsWrappers.lambdaIndex(EsTestNestedTest1.class)
                .indexName(EsTestNestedTest1.class.getSimpleName().toLowerCase(Locale.ROOT))
                //keyword类型(不支持分词),文档内容映射为text类型(支持分词查询)
                .mapping(EsTestNestedTest1::getTitle, FieldType.TEXT, 2.0f)
//                .mapping(Document::getLocation, FieldType.GEO_POINT)
//                .mapping(Document::getGeoLocation, FieldType.GEO_SHAPE)
//                .mapping(Document::getContent, FieldType.TEXT, Analyzer.IK_SMART, Analyzer.IK_MAX_WORD);
                //设置分片及副本信息,可缺省
                .settings(3, 2)
                // 设置别名信息,可缺省
                .createAlias("daily")
                //设置父子信息,若无父子文档关系则无需设置
                .join("joinField", "document", "comment");
        Boolean aBooleanIndex = esTestNestedTest1Mapper.updateIndex(indexWrapper);
        Assertions.assertTrue(aBooleanIndex);
    }

    @Test
    public void pageEsQuery() throws Exception {
        Date parse = new Date(2023 - 1900, 6, 19, 18, 3, 49); // 注意月份从0开始，6 表示 7 月
        long timestamp = parse.getTime(); // 获取 Unix 时间戳
        LambdaEsQueryWrapper<EsTestNestedTest1> queryWrapper = EsWrappers.lambdaQuery(EsTestNestedTest1.class)
//                .eq(EsTestNestedTest1::getTitle, "老汉")
//                .ge(EsTestNestedTest1::getCreateTime,timestamp)
                .likeLeft(EsTestNestedTest1::getDataInfo, "le");
        EsPageInfo<EsTestNestedTest1> esPageInfo = esTestNestedTest1Mapper.pageQuery(queryWrapper, 1, 10);
        System.out.println(esPageInfo);
    }

    @Test
    public void testSearchAfter() {
        LambdaEsQueryWrapper<EsTestNestedTest1> lambdaEsQueryWrapper = EsWrappers.lambdaQuery(EsTestNestedTest1.class);
        lambdaEsQueryWrapper.size(10);
        // 必须指定一种排序规则,且排序字段值必须唯一 此处我选择用id进行排序 实际可根据业务场景自由指定,不推荐用创建时间,因为可能会相同
        lambdaEsQueryWrapper.orderByDesc(EsTestNestedTest1::getId);
        SAPageInfo<EsTestNestedTest1> saPageInfo = esTestNestedTest1Mapper.searchAfterPage(lambdaEsQueryWrapper, null, 10);
        // 第一页
        System.out.println(saPageInfo);
//        Assertions.assertEquals(10, saPageInfo.getList().size());

        // 获取下一页
        List<Object> nextSearchAfter = saPageInfo.getNextSearchAfter();
        SAPageInfo<EsTestNestedTest1> next = esTestNestedTest1Mapper.searchAfterPage(lambdaEsQueryWrapper, nextSearchAfter, 10);
        Assertions.assertEquals(10, next.getList().size());
    }

    @Test
    public void testNestedMatch() {
        // 嵌套查询 查询年龄等于18或8，且密码等于123的数据
        LambdaEsQueryWrapper<EsTestNestedTest1> wrapper = new LambdaEsQueryWrapper<>();
        /*
        * test: 分词 不支持中文
        * keyword: 不分词 可中文查询
        * */
        wrapper.nested("es_test_nested_child_test_list", w ->
//                w.eq("es_test_nested_child_test_list."+FieldUtils.val(EsTestNestedChildTest::getEat)+".keyword", "吃")
//                .eq("es_test_nested_child_test_list."+FieldUtils.val(EsTestNestedChildTest::getFood)+".keyword", "火锅")
                w.eq("es_test_nested_child_test_list."+FieldUtils.val(EsTestNestedChildTest::getEat), "he")
//                .eq("es_test_nested_child_test_list."+FieldUtils.val(EsTestNestedChildTest::getFood), "pijiu")
//                .eq("es_test_nested_child_test_list.address.keyword", "德国啤酒节")
                        )
        ;

        List<EsTestNestedTest1> documents = esTestNestedTest1Mapper.selectList(wrapper);
        System.out.println(documents);


        // 嵌套查询 查询年龄满足18或者问题名称匹配'size也18吗'的全部数据
       /* LambdaEsQueryWrapper<EsTestNestedTest1> wrapper2 = new LambdaEsQueryWrapper<>();
        wrapper2.nested("esTestNestedChildTestList", w -> w.in("eat", "喝"))
                .or()
                .nested("esTestNestedChildTestList.address", w -> w.match("address", "德国啤酒节"));
        List<EsTestNestedTest1> documents2 = esTestNestedTest1Mapper.selectList(wrapper2);
        System.out.println(documents2);*/
    }



    @Test
    public void testSearch1() {
        // 嵌套查询 查询年龄等于18或8，且密码等于123的数据
        LambdaEsQueryWrapper<EsTestNestedTest1> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.nested("es_test_nested_child_test_list", w ->
                w.eq("es_test_nested_child_test_list.eat", "chi"));
        List<EsTestNestedTest1> documents = esTestNestedTest1Mapper.selectList(wrapper);
        ;

//        LambdaEsQueryWrapper<EsTestNestedTest1> wrapper = new LambdaEsQueryWrapper<>();
//        wrapper.in(EsTestNestedTest1::getTitle,"qiantaochaxun")
//                .multiMatchQuery("es_test_nested_child_test_list","")
//
//
//        ;
//        List<EsTestNestedTest1> documents = esTestNestedTest1Mapper.selectList(wrapper);
        System.out.println(documents);


        // 嵌套查询 查询年龄满足18或者问题名称匹配'size也18吗'的全部数据
       /* LambdaEsQueryWrapper<EsTestNestedTest1> wrapper2 = new LambdaEsQueryWrapper<>();
        wrapper2.nested("esTestNestedChildTestList", w -> w.in("eat", "喝"))
                .or()
                .nested("esTestNestedChildTestList.address", w -> w.match("address", "德国啤酒节"));
        List<EsTestNestedTest1> documents2 = esTestNestedTest1Mapper.selectList(wrapper2);
        System.out.println(documents2);*/
    }


}
