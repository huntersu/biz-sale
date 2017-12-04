package com.biz.domain;

public class SaleMainDataWithBLOBs extends SaleMainData {
    private String isRealComment;

    private String fiveUserUpComment;

    private String importantReq;

    public String getIsRealComment() {
        return isRealComment;
    }

    public void setIsRealComment(String isRealComment) {
        this.isRealComment = isRealComment == null ? null : isRealComment.trim();
    }

    public String getFiveUserUpComment() {
        return fiveUserUpComment;
    }

    public void setFiveUserUpComment(String fiveUserUpComment) {
        this.fiveUserUpComment = fiveUserUpComment == null ? null : fiveUserUpComment.trim();
    }

    public String getImportantReq() {
        return importantReq;
    }

    public void setImportantReq(String importantReq) {
        this.importantReq = importantReq == null ? null : importantReq.trim();
    }
}