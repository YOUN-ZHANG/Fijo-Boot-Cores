package com.fijo.boot.exceptions;

/**
 * @ Author     ：zhangbo.
 * @ Date       ：Created in 19:15 2019/12/1
 * @ Description：
 * @ Modified By：
 * @Version:
 */

public class TransactionRollbackException extends AppException{

    /**
     *
     */
    private static final long serialVersionUID = 4432025698389231168L;

    public TransactionRollbackException(){}

    public TransactionRollbackException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public TransactionRollbackException(String errorCode, String errorMessage,
                                        Throwable e) {
        super(errorCode, errorMessage, e);
    }
}
