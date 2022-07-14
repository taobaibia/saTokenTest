package com.yeweiyang.token.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.pojo
 * @date 2021/12/10 5:18 下午
 */
@Data
@TableName(value = "testpoi")
public class JayTestPoi implements Serializable {
//    @BeanField("用户名")
    private String id;
    private String name;
    private String age;
    private String address;
}
