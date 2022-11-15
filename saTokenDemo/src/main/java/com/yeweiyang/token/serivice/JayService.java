package com.yeweiyang.token.serivice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeweiyang.token.pojo.copy.Jay;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface JayService extends IService<Jay> {

    List<Jay> findByName(String name);

    List<Jay> findOtherName(Jay Jay);

    String sendMessage(Long phoneNumber);

    String esayPoi(HttpServletResponse response, HttpServletRequest request) throws Exception;

    String esayPoi222(HttpServletResponse response) throws IOException;

    /**
     * 动态map数据的导出
     */
    List<Map<String, Object>> findByMap(HttpServletResponse response)throws Exception;
    /**
     * Excel导入
     */
    void importExcel(MultipartFile file) throws Exception;

    /**
     * 多线程处理
     */
    Boolean pollExecutor(Jay jay);
    //删除
    Boolean deletePollExecutor(String jay);
}
