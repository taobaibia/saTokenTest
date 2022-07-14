package com.yeweiyang.token.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeweiyang.token.pojo.copy.Jay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JayMapper extends BaseMapper<Jay> {

    List<Jay> selectOtherName(@Param("entity") Jay jay);

    List<Map<String,Object>> selectAllMap();

    String getcount();
}
