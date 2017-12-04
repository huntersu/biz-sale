package com.biz.common;

import java.io.Serializable;

/**
 * 返回结果集
 */
public class ResultDTO implements Serializable{

    private Boolean isSuccess;
    private String errCode;
    private String errMsg;
    private Object data;

    public ResultDTO(){}

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
