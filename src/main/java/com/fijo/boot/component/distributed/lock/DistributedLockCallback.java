/*
 *
 */
package com.fijo.boot.component.distributed.lock;

/**
 * 用途：分布式锁回调接口
 * 参考：https://layznet.iteye.com/blog/2307179
 * 作者: zhangbo
 * 时间: 2018/6/22  15:05
 */
public interface DistributedLockCallback<T> {
    /**
     * 调用者必须在此方法中实现需要加分布式锁的业务逻辑
     *
     * @return
     */
    T process();

    /**
     * 得到分布式锁名称
     *
     * @return
     */
    String getLockName();

}
