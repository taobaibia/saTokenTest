package com.yeweiyang.token.concurrent;

import com.yeweiyang.token.config.EumeAndWeb.CommonCodeEnum;
import lombok.Data;

/**
 * AsyncTaskResult: 异步任务结果
 *
 * @author hao.jiang
 * @date 2022/9/20 18:36
 * @since 2.21
 */
@Data
public class AsyncTaskResult<P, T> {
    private String code;
    private AsyncTask<P, T> asyncTask;
    private Throwable throwable;
    private T result;
    public boolean isSuccess() {
        return CommonCodeEnum.SUCCESS.getCode().equals(this.code);
    }
}
