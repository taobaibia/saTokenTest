package com.yeweiyang.token.concurrent;

import java.util.UUID;

/**
 * AsyncTask: 异步任务
 *
 * @author hao.jiang
 * @date 2022/9/20 14:26
 * @since 2.21
 */
public abstract class AsyncTask<P, T> {

    protected String taskName() {
        return UUID.randomUUID().toString();
    }

    protected abstract T doExecute() throws Exception;

    public P p;
}
