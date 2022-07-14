package com.yeweiyang.token.serivice.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeweiyang.token.mapper.ExaminationAppointmentMapper;
import com.yeweiyang.token.pojo.ExaminationAppointment;
import com.yeweiyang.token.serivice.ExaminationAppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice.serviceImpl
 * @date 2022/1/21 10:58 上午
 */
@Service
public class ExaminationAppointmentServiceImpl extends ServiceImpl<ExaminationAppointmentMapper, ExaminationAppointment> implements ExaminationAppointmentService{


    /**
     * 更新表空数据
     */
    @Override
    public int deleteTableNull() {

        QueryWrapper<ExaminationAppointment> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                        .eq(ExaminationAppointment::getUserId,"")
                ;
        List<ExaminationAppointment> list = baseMapper.selectList(wrapper);
        int delete = baseMapper.delete(wrapper);
        return delete;
    }


}
