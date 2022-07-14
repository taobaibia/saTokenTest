package com.yeweiyang.token.serivice.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeweiyang.token.mapper.JayMapper;
import com.yeweiyang.token.mapper.JayTestPoiMapper;
import com.yeweiyang.token.pojo.copy.Jay;
import com.yeweiyang.token.pojo.JayTestPoi;
import com.yeweiyang.token.pojo.excelPojo.JayExcel;
import com.yeweiyang.token.serivice.JayService;
import com.yeweiyang.token.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.scheduler.Scheduler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author jay
 */
@Service
public class JayServiceImpl extends ServiceImpl<JayMapper, Jay> implements JayService {

    @Autowired
    private JayMapper jayMapper;
    @Autowired
    private JayTestPoiMapper jayTestPoiMapper;
//    @Autowired
//    private CacheManager cacheManager;

    @Override
    public List<Jay> findByName(String name) {
        List<Long> longs = new ArrayList<>();
        longs.add(2L);
        longs.add(1L);
        QueryWrapper<Jay> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Jay::getName, "Jay");
        if (!CollectionUtils.isEmpty(longs)) {
            wrapper.lambda().in(Jay::getId, longs);
        }
        HashSet<Scheduler.Worker> workers = new HashSet<>();
        List<Jay> list = jayMapper.selectList(wrapper);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 10,
                24000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        return list;
    }

    @Override
    public List<Jay> findOtherName(Jay jay) {

        return jayMapper.selectOtherName(jay);
    }

    @Override
    public String sendMessage(Long phoneNumber) {
//        cacheManager.

        return null;
    }

    /*导出esayPoi*/
//    @Override
//    public String esayPoi(HttpServletResponse response) throws Exception {
//        QueryWrapper<Jay> wrapper = new QueryWrapper<>();
//        List<Jay> jays = jayMapper.selectList(wrapper);
////        List<DynamicTablePo> resultList = new ArrayList<>();
////        Jay jay1 = jays.get(0);
////        DynamicTablePo tablePo = new DynamicTablePo();
////        tablePo.setFiledShowName(String.valueOf(jay1.getId()));
////        tablePo.setFiledCode("id");
////        tablePo.setOrderNum(1);
////        tablePo.setDataType(1);
////        DynamicTablePo tablePo1 = new DynamicTablePo();
////        tablePo1.setFiledShowName(String.valueOf(jay1.getUpdateTime()));
////        tablePo1.setFiledCode("userAddress");
////        tablePo1.setOrderNum(2);
////        tablePo1.setDataType(1);
////        DynamicTablePo tablePo2 = new DynamicTablePo();
////        tablePo2.setFiledShowName(String.valueOf(jay1.getDelFlag()));
////        tablePo2.setFiledCode("userAddress");
////        tablePo2.setOrderNum(2);
////        tablePo2.setDataType(1);
////        DynamicTablePo tablePo3 = new DynamicTablePo();
////        tablePo3.setFiledShowName(jay1.getName());
////        tablePo3.setFiledCode("userAddress");
////        tablePo3.setOrderNum(2);
////        tablePo3.setDataType(1);
////        DynamicTablePo tablePo4 = new DynamicTablePo();
////        tablePo4.setFiledShowName(jay1.getCreateUser());
////        tablePo4.setFiledCode("userAddress");
////        tablePo4.setOrderNum(2);
////        tablePo4.setDataType(1);
////        DynamicTablePo tablePo5 = new DynamicTablePo();
////        tablePo5.setFiledShowName(String.valueOf(jay1.getCreateTime()));
////        tablePo5.setFiledCode("userAddress");
////        tablePo5.setOrderNum(2);
////        tablePo5.setDataType(1);
////        DynamicTablePo tablePo6 = new DynamicTablePo();
////        tablePo6.setFiledShowName(jay1.getUpdateUser());
////        tablePo6.setFiledCode("userAddress");
////        tablePo6.setOrderNum(2);
////        tablePo6.setDataType(1);
////        resultList.add(tablePo);
////        resultList.add(tablePo1);
////        resultList.add(tablePo2);
////        resultList.add(tablePo3);
////        resultList.add(tablePo4);
////        resultList.add(tablePo5);
////        resultList.add(tablePo6);
////
////
////        List<ExcelExportEntity> beanList = new ArrayList<>(resultList.size());
////        for (DynamicTablePo dynamicTablePo : resultList) {
////            ExcelExportEntity entity = new ExcelExportEntity();
////            entity.setName(dynamicTablePo.getFiledShowName());
////            entity.setKey(dynamicTablePo.getFiledCode());
////            entity.setOrderNum(dynamicTablePo.getOrderNum());
////            if (dynamicTablePo.getDataType() == 1) {//TODO 这块控制一下，是不是要写成数值型，默认
////                entity.setType(BaseEntityTypeConstants.STRING_TYPE);
////            }
////            beanList.add(entity);
////        }
////        try {
////            String excelName = "用户信息表";
////            ExportParams exportParams = new ExportParams(excelName, "sheet1");
////            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, beanList, resultList);
////            response.setHeader("content-Type", "application/vnd.ms-excel");
////            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelName + "导出表", "UTF-8") + ".xls");
////            response.setCharacterEncoding("UTF-8");
////            workbook.write(response.getOutputStream());
////            workbook.close();
////        } catch (FileNotFoundException e) {
////            return "导出选中的单据 exportOrderList=====ERROR=====";
////        } catch (IOException e) {
////            return "导出选中的单据 exportOrderList=====ERROR=====";
////        }
//        ExcelUtils.exportExcelToTarget(response,"导出名",jays, JayExcel.class);
//        return "成功";
//    }

    @Override
    public String esayPoi(HttpServletResponse response, HttpServletRequest request) throws Exception {
        QueryWrapper<Jay> wrapper = new QueryWrapper<>();
        List<Jay> jays = jayMapper.selectList(wrapper);

/*        TemplateExportParams params = new TemplateExportParams(
                "WEB-INF/doc/jxlpmx.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);*/

        Jay jay = jays.get(0);

        QueryWrapper<JayTestPoi> jayTestPoiQueryWrapper = new QueryWrapper<>();
        List<JayTestPoi> jayTestPois = jayTestPoiMapper.selectList(jayTestPoiQueryWrapper);
        JayTestPoi poi = jayTestPois.get(0);

        ArrayList<Workbook> workbooks = new ArrayList<>();

        ExportParams exportParams = new ExportParams();
        exportParams.setTitle(poi.getId());
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, JayTestPoi.class, jayTestPois);

        ExcelUtils.out(sheets, "codedFileName", request, response);
        ExcelUtils.exportExcelToTarget(response, "导出名", jays, JayExcel.class);
        return "成功";
    }

    @Override
    public String esayPoi222(HttpServletResponse response) throws IOException {

//        TemplateExportParams params = new TemplateExportParams(
//                "/templates/lpmx.xls");
        TemplateExportParams params = new TemplateExportParams(
                "/Users/jay/Desktop/work/project/test1114/src/main/resources/templates/lpmx.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("/Users/jay/Desktop/xCodeTest/moban/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("/Users/jay/Desktop/xCodeTest/moban/lpmx.xls");
//        FileOutputStream fos = new FileOutputStream("D:/home/excel/jxlpmx.xls");
        workbook.write(fos);
        fos.close();


//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("content-Type", "application/vnd.ms-excel");
//        response.setHeader("Content-Disposition",
//                "attachment;filename=" + URLEncoder.encode("fileName", "UTF-8") + ".xls");
//        ServletOutputStream out = response.getOutputStream();
//        workbook.write(out);


        return null;
    }

    @Override
    public List<Map<String, Object>> findByMap(HttpServletResponse response) throws Exception {

        /*解密字段*/
        List<String> decryptList = new ArrayList<>();
        decryptList.add("name");
        decryptList.add("id");

        /*表头*/
        List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
//        ExcelExportEntity colEntity = new ExcelExportEntity("商品名称", "title");
//        colEntity.setNeedMerge(true);
//        colList.add(colEntity);

        /*总数*/
        List<Map<String, Object>> stringObjectMap = jayMapper.selectAllMap();
        /*title*/
        Map<String, Object> stringObjectMap1 = stringObjectMap.get(0);
        for (Map.Entry<String, Object> entry : stringObjectMap1.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            ExcelExportEntity colEntity = new ExcelExportEntity(String.valueOf(value), key);
            colEntity.setNeedMerge(true);
            colList.add(colEntity);
        }
        /*遍历集合*/
        for (int i = 0; i < stringObjectMap.size(); i++) {
            Map<String, Object> objectMap = stringObjectMap.get(i);
            /*遍历属性*/
            for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (decryptList.contains(key)) {
                    value = "解密咯";
                    entry.setValue(value);
                    System.out.println(value);
                }
                System.out.println(entry);
            }
        }

//        for (int i = 0; i < stringObjectMap.size(); i++) {
//            Map<String, List<String>> stringListMap = stringObjectMap.get(i);
//            /*属性遍历*/
//            for (Map.Entry<String, List<String>> entry : stringListMap.entrySet()) {
//                String key = entry.getKey();
//                List<String> valueList = entry.getValue();
//                /*解密*/
//                String decrypt = "";
//                for (String s : valueList) {
//                    if (decryptList.contains(s)) {
//                        String j = "解密咯";
//                        s = j;
////                        entry.setValue(j);
//                    }
//                }
//
//            }
//        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "数据"), colList, stringObjectMap);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("嘉兴大病无忧自导", "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);

        return stringObjectMap;
    }

    /**
     * excel导入
     */
    @Override
    public void importExcel(MultipartFile file) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);

        List<Jay> excel = ExcelImportUtil.importExcel((InputStream) file, JayExcel.class, params);

        for (Jay jay : excel) {
            int insert = jayMapper.insert(jay);
            System.out.println(insert);
        }

//        QueryWrapper<Jay> jayQueryWrapper = new QueryWrapper<>();
//        List<Map<String, Object>> maps = jayMapper.selectMaps(jayQueryWrapper);
    }

    @Override
    public Boolean pollExecutor(Jay jay) {
        //SynchronousQueue 无缓冲等待队列，不存储等待队列
        //ArrayBlockingQueue    有界缓存队列
        //LinkedBlockingQueue   无界缓存队列
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10,
                24000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        //执行线程
        for (int i = 0; i < 1000; i++) {
            jay.setName(String.valueOf(i));
            jay.setDelFlag(Boolean.TRUE);
            jay.setCreateTime(new Date());
            jay.setCreateUser("多线程");
            jay.setUpdateUser("多线程");
            pool.execute(
                    ()->save(jay)
            );
//            int finalI = i;
//            pool.execute(()-> System.out.println());
//            System.out.println("保存数据："+jay);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deletePollExecutor(String jay) {
        LambdaQueryWrapper<Jay> wrapper = Wrappers.<Jay>lambdaQuery()
                .eq(Jay::getDelFlag,Boolean.TRUE);
        int delete = baseMapper.delete(wrapper);
        return Boolean.TRUE;
    }


    public static void main(String[] args) {
//        String[] strs = {"flower", "flow", "flight"};
//        System.out.println(ppad(strs));
//        BigDecimal decimal = new BigDecimal("1300000");
//        System.out.println(decimal);
//        decimal = (null == decimal ? new BigDecimal(0):decimal.setScale(0));
//        System.out.println(decimal.setScale(0));

        List<Jay> list = new ArrayList<>();
        Jay jay1 = new Jay();
        jay1.setId(1L);
        jay1.setName("jay");
        jay1.setDelFlag(Boolean.TRUE);
        Jay jay2 = new Jay();
        jay2.setId(1L);
        jay2.setName("jay");
        jay2.setDelFlag(Boolean.TRUE);
        Jay jay3 = new Jay();
        jay3.setId(2L);
        jay3.setName("jay3");
        jay3.setDelFlag(Boolean.TRUE);
        list.add(jay1);
        list.add(jay2);
        list.add(jay3);
        Map<Long, List<Jay>> map = list.stream().collect(Collectors.groupingBy(Jay::getId));
        System.out.println(map);
        List<Object> arrayList = new ArrayList<>();
        map.forEach((k, v) ->{
            Set<String> liabilities = v.stream().map(Jay::getName).collect(Collectors.toSet());
            arrayList.add(liabilities);
        });
        System.out.println(arrayList);





    }

    private static String ppad(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < result.length() && j < strs[i].length(); j++) {
                if (result.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            result = result.substring(0, j);
            if (result.equals("")) {
                return result;
            }
            return result;
        }
        return result;
    }


}
