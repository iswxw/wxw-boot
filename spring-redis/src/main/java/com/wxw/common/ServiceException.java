package com.wxw.common;

/**
 * @ Author ：wxw.
 * @ Date ： 11:41 2020/9/28
 * @ Description：运行时异常
 * @ Version:   v_0.0.1
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Result result;

    public ServiceException() {
        super();
    }

    public ServiceException(Result result) {
        super(result.getMessage());
        this.result = result;
    }

    public ServiceException(Result result, String message) {
        super(message == null ? result.getMessage() : message);
        this.result = result;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
