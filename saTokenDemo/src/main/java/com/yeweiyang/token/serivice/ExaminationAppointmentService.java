package com.yeweiyang.token.serivice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeweiyang.token.pojo.ExaminationAppointment;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.serivice
 * @date 2022/1/21 10:57 上午
 */
public interface ExaminationAppointmentService extends IService<ExaminationAppointment> {

    /**
     * 更新表空数据
     */
    int deleteTableNull();
}
