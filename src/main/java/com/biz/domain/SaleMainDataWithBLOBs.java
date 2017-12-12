package com.biz.domain;

public class SaleMainDataWithBLOBs extends SaleMainData {
    private String importantReq;

    private String logInfo;

    public String getImportantReq() {
        return importantReq;
    }

    public void setImportantReq(String importantReq) {
        this.importantReq = importantReq == null ? null : importantReq.trim();
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo == null ? null : logInfo.trim();
    }
}