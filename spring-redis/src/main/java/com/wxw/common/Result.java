package com.wxw.common;

/**
 * @ Author ：wxw.
 * @ Date ： 11:38 2020/9/28
 * @ Description：枚举结果描述
 * @ Version:   v_0.0.1
 */
public enum Result {
    SUCCESS(200, "成功"),
    FAILURE(-1, "失败"),
    EXCEPTIONMESSAGE(-2, "失败")
    ;

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

//	enum 禁止修改
//	public void setCode(int code) {
//		this.code = code;
//	}

    public String getMessage() {
        return message;
    }

    //	enum 禁止修改
//	public void setMessage(String message) {
//		this.message = message;
//	}

}
