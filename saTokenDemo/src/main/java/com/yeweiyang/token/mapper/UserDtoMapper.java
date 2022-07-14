package com.yeweiyang.token.mapper;


import com.yeweiyang.token.pojo.copy.Jay;
import com.yeweiyang.token.pojo.copy.JayDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.Map;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.mapper
 * @date 2021/12/1 2:13 下午
 * injectionStrategy 依赖注入
 * componentModel = "cdi"
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDtoMapper {

    /**
     * mapper实例化工厂
     */
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);


    @Mappings({
            @Mapping(source = "name", target = "userName"),//不同名
//            @Mapping(source = "fengLinList", target = "fengLinListDto"),//不同名
            @Mapping(ignore = true, source = "delFlag", target = "delFlag2"),//忽略
            @Mapping(defaultValue = "周杰伦", source = "createUser", target = "createUser"),//空则为默认值
//            @Mapping(source = "fengLinList", target = "fengLinListDto"),
            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd")
//            @Mapping(target = "createTime", expression = "java(new java.text.SimpleDateFormat().parse(org.apache.commons.lang3.time.DateFormatUtils.format(jay.getCreateTime(), \"yyyy-MM-dd\")))"),//自定义属性通过java代码映射
//            new SimpleDateFormat().parse(DateFormatUtils.format(jay.getCreateTime(), "yyyy-MM-dd"))
//            ,
    })
    JayDto jayToJayDto(Jay jay) throws Exception;

    /*集合*/
//    List<JayDto> jayToJayDto(List<Jay> jays);




    /**
     * 更新， 注意注解 @MappingTarget
     * 注解 @MappingTarget后面跟的对象会被更新。
     */
    void updateDeliveryAddressFromAddress(Jay jay,@MappingTarget JayDto jayDto);

    @MapMapping(valueDateFormat ="yyyy-MM-dd HH:mm:ss")
    public Map<String ,String> DateMapToStringMap(Map<String, Date> sourceMap);






}
