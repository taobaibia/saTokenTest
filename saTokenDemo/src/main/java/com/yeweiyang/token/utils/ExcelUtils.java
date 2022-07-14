package com.yeweiyang.token.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * excel工具类
 *
 * @author tecsmile@outlook.com
 */
public class ExcelUtils {

    /**
     * Excel导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list,
                                     Class<?> pojoClass) throws IOException {
        if(StringUtils.isBlank(fileName)){
            //当前日期
            fileName = DateUtils.format(new Date());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param sourceList    原数据List
     * @param targetClass   目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
                                     Class<?> targetClass) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for(Object source : sourceList){
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, targetList, targetClass);
    }


    private static final String CONTENT_TYPE = "text/html;application/vnd.ms-excel";

    protected static final String HSSF = ".xls";
    protected static final String XSSF = ".xlsx";



    public static void out(Workbook workbook, String codedFileName, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        if (workbook instanceof HSSFWorkbook) {
            codedFileName += HSSF;
        } else {
            codedFileName += XSSF;
        }
        // 用工具类生成符合RFC 5987标准的文件名header, 去掉UA判断
        response.setHeader("content-disposition", WebFilenameUtils.disposition(codedFileName));
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
}
