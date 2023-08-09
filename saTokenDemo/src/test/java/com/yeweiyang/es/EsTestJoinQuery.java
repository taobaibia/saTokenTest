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
 * 父子查询
 */
@Disabled
@SpringBootTest(classes = SatokenDemoApplication.class)
public class EsTestJoinQuery {

    @Resource
    private EsTestNestedTest1Mapper esTestNestedTest1Mapper;
    @Resource
    private EsTestNestedChildTestMapper esTestNestedChildTestMapper;

    @Test
    public void testCreateIndex() {

        Assertions.assertTrue();
    }

    @Test
    public void deleteIndexTest() {

        Boolean aBoolean = esTestNestedTest1Mapper.deleteIndex();
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


}
