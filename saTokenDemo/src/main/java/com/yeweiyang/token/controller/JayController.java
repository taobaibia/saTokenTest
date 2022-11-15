package com.yeweiyang.token.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import cn.afterturn.easypoi.exception.excel.ExcelImportException;
import cn.dev33.satoken.annotation.SaCheckSafe;
import com.yeweiyang.token.mapper.UserDtoMapper;
import com.yeweiyang.token.pojo.copy.FengLin;
import com.yeweiyang.token.pojo.copy.Jay;
import com.yeweiyang.token.pojo.copy.JayDto;
import com.yeweiyang.token.serivice.JayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "测试:测试功能模块")
@RequestMapping("/jay/test/api")
public class JayController {

    @Autowired
    private JayService jayService;
//    @Autowired
//    private UserDtoMapper userDtoMapper;



    @GetMapping("/v1/select")
    @ApiOperation("test查看")
//    @SaCheckSafe
    public List<Jay> select() throws Exception {
        String name = "";
        List<Jay> list = jayService.findByName(name);
        return list;
    }

    @PostMapping("/get/name/other")
    @ApiOperation("多条件查询")
    public List<Jay> selectOther(@RequestBody Jay jay) {
        return jayService.findOtherName(jay);
    }


    @GetMapping("/v1/esay/poi")
    @ApiOperation("导出esayPoi")
    public String esayPoi(HttpServletResponse response) throws Exception {

        return jayService.esayPoi222(response);
    }

    @GetMapping("/v1/redis/message")
    @ApiOperation("短信发送")
    public String message(Long phoneNumber) throws Exception {
        phoneNumber = 1512215123L;

        return jayService.sendMessage(phoneNumber);
    }

    @PostMapping("/v1/import/excel")
    @ApiOperation("Excel导入")
    public void importExcel(MultipartFile file) throws Exception {
        jayService.importExcel(file);
    }


    @GetMapping("/v1/object/conversion")
    @ApiOperation("对象转换")
    public void objectConversion() throws Exception {
        Jay jay = new Jay();
//        JayDto jayDto = userDtoMapper.jayToJayDto(jay);
    }

    @GetMapping("/v1/list/map")
    @ApiOperation("map查询动态导出")
    public List<Map<String, Object>> mapList(@ApiIgnore HttpServletResponse response) throws Exception {
        List<Map<String, Object>> byMap = jayService.findByMap(response);
        return byMap;
    }

    @PostMapping("/v1/file/upload")
    @ApiOperation("文件上传导入")
    public List fileUpload(@ApiIgnore HttpServletResponse response, MultipartFile file) throws Exception {

//        FileInputStream in = null;
        List list = null;
        try {
            InputStream inputStream = file.getInputStream();
            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(1);
            importParams.setTitleRows(1);
//            list = new ExcelImportService().importExcelByIs(inputStream, Jay.class, importParams, false).getList();
            return new ExcelImportService().importExcelByIs(inputStream, Jay.class, importParams, false).getList();
        } catch (ExcelImportException e) {

            throw new ExcelImportException(e.getType(), e);
        } catch (Exception e) {
            throw new ExcelImportException(e.getMessage(), e);
        } finally {
//            IOUtils.closeQuietly(in);
        }

    }

    @PostMapping("/thread/pool/executor")
    @ApiOperation("多线程处理")
    public Boolean pollExecutor(@RequestBody Jay jay){
        return jayService.pollExecutor(jay);
    }


    @PostMapping("/file/upload")
    @ApiOperation("文件上传啊啊啊啊啊啊啊啊啊啊")
    public Boolean pollExecutor(@RequestParam("file") MultipartFile[] file){

        for (MultipartFile uploadFile : file) {
            String originalFilename = uploadFile.getOriginalFilename();
            System.out.println(originalFilename);
        }
        return Boolean.TRUE;
    }

    @GetMapping("/jay/delete")
    @ApiOperation("删除")
    public Boolean jayDelete(String jay){
        return jayService.deletePollExecutor(jay);
    }

    public static void main(String[] args) throws Exception {
        ArrayList<FengLin> list = new ArrayList<>();
        FengLin fengLin = new FengLin();
        fengLin.setId(1L);
        fengLin.setName("张国荣");
        fengLin.setCreateUser("admin");
        fengLin.setDelFlag(Boolean.TRUE);
        FengLin fengLin1 = new FengLin();
        fengLin1.setId(2L);
        fengLin1.setName("梁朝伟");
        fengLin1.setCreateUser("system");
        fengLin1.setDelFlag(Boolean.FALSE);
        list.add(fengLin);
        list.add(fengLin1);
        /*========================================================================================*/
        Jay jay = new Jay();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(new Date());
        jay.setName("刘德华");
        jay.setId(1L);
//        try {
//            jay.setCreateTime(dateFormat.parse(s));
//        } catch (ParseException e) {
//        }
        jay.setDelFlag(true);
//        jay.setUpdateUser(s);
//        jay.setFengLinList(list);
        System.out.println(jay);
        System.out.println("-----------------原------------------");
        UserDtoMapper mapper = Mappers.getMapper(UserDtoMapper.class);
        JayDto jayDto = mapper.jayToJayDto(jay);
        System.out.println("jayToJayDto     " + jayDto);
        System.out.println("----------------工厂--------------------");
        JayDto jayDto1 = UserDtoMapper.INSTANCE.jayToJayDto(jay);
        System.out.println(jayDto1);

//        String format = DateFormatUtils.format(jay.getCreateTime(), "yyyy-MM-dd");
//        Date parse = new SimpleDateFormat().parse(DateFormatUtils.format(jay.getCreateTime(), "yyyy-MM-dd"));
//        String format = DateFormatUtils.format(jay.getCreateTime(), "yyyy-MM-dd");
    }





}
