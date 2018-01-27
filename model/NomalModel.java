package com.ivan.timingsystem.model;

/**
 * Created by Administrator on 2017/11/27/027.
 */

public class NomalModel {

    /**
     * ResultStatus : Success
     * Result : 修改完成!
     */

    private String ResultStatus;
    private Object Result;

    public String getResultStatus() {
        return ResultStatus;
    }

    public void setResultStatus(String ResultStatus) {
        this.ResultStatus = ResultStatus;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object Result) {
        this.Result = Result;
    }
}
