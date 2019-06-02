/*
 *
 */
package com.fijo.boot.exceptions;

import lombok.NoArgsConstructor;

/**
 * 用途：生产主键异常
 * 作者: zhangbo
 * 时间: 2018/6/7  20:03
 */
@NoArgsConstructor
public class IdGeneratorException extends RuntimeException {

    /**
     * Constructs an <code>InsertExistObjectException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public IdGeneratorException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>InsertExistObjectException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public IdGeneratorException(String msg, Throwable t) {
        super(msg, t);
    }
}
