package com.yeweiyang.token.mapperCopyDTO;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.mapper
 * @date 2021/12/1 2:13 下午
 * injectionStrategy 依赖注入
 * componentModel = "cdi"
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDtoToMapper {
//
//    /*mapper实例化工厂*/
//    UserDtoToMapper INSTANCE = Mappers.getMapper(UserDtoToMapper.class);
//
//
//    @Mappings({
////            @Mapping(source = "ClaimBill", target = "ClaimDrugInfo"),//不同名
////            @Mapping(ignore = true, source = "delFlag", target = "delFlag2"),//忽略
////            @Mapping(defaultValue = "周杰伦", source = "createUser", target = "createUser"),//空则为默认值
////            @Mapping(source = "fengLinList", target = "fengLinListDto"),
////            @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd")
////            @Mapping(target = "createTime", expression = "java(new java.text.SimpleDateFormat().parse(org.apache.commons.lang3.time.DateFormatUtils.format(jay.getCreateTime(), \"yyyy-MM-dd\")))"),//自定义属性通过java代码映射
////            new SimpleDateFormat().parse(DateFormatUtils.format(jay.getCreateTime(), "yyyy-MM-dd"))
////            ,
//    })
//    List<UserResp> userToUserResp(List<User> usersjays);
//

}
