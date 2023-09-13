package com.yeweiyang.ThreadPool;

import com.yeweiyang.token.SatokenDemoApplication;
import com.yeweiyang.token.es.pojo.EsTestNestedTest1;
import org.dromara.easyes.annotation.rely.FieldType;
import org.dromara.easyes.core.conditions.index.LambdaEsIndexWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Jay
 * @version V1.0
 * @Package com.yeweiyang.ThreadPool
 * @date 2023/7/21 17:13
 */
@Disabled
@SpringBootTest(classes = SatokenDemoApplication.class)
public class ThreadPoolTest {

    @Test
    @Order(3)
    public void ThreadPoolTest1() {

        /*
         * FixedThreadPool
         * SingleThreadExecutor
         * LinkedBlockingQueue，任务队列最大长度为 Integer.MAX_VALUE,可能堆积大量的请求，从而导致 OOM。
         * */
        Assertions.assertTrue(true);
    }

        public static void main(String[] args){
            Map<String, String> map = new HashMap<>(4);
            map.put("1","1");
            map.put("2","2");
            map.put("3","3");
            map.put("4","4");
            System.out.println(map);

        }

        private static int testla(){
        int a = 1;
            try{
                System.out.println("try");
                throw new Exception();
            }catch(Exception e){
                System.out.println("error");
                return a;
            }finally{
                System.out.println("finally");
            }

        }
}
