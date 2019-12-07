package com.fijo.boot.exceptions;

/**
 * @ Author     ：zhangbo.
 * @ Date       ：Created in 19:13 2019/12/1
 * @ Description：
 * @ Modified By：
 * @Version:
 */

public class AppException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -1607063540894022535L;
    private String errorCode;
    private String errorMessage;

    public AppException(){
        super();
    }

    public AppException(Throwable cause){
        super(cause);
    }

    /**
     *
     * @param errorCode
     * @param errorMessage
     */
    public AppException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     *
     * @param errorCode
     * @param errorMessage
     * @param e
     */
    public AppException(String errorCode, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
