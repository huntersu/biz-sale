package com.biz.domain;

import com.biz.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class SaleMainData {
    private String id;

    private String cusName;

    private Date beginDate;

    private Date updateDate;

    private String seenPolicymaker;

    private String policymakerPosition;

    private String donePolicymaker;

    private String donePolicymakerPosition;

    private String isReal;

    private String isRealComment;

    private String fiveUserUp;

    private String fiveUserUpComment;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private Integer cusEmpNum;

    private String cusCity;

    private Date launchTime;

    private Date closeTime;

    private String nextPolicymakerAction;

    private String nextReqAction;

    private Date trueCloseTime;

    private String status;

    private String importantReq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName == null ? null : cusName.trim();
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSeenPolicymaker() {
        return seenPolicymaker;
    }

    public void setSeenPolicymaker(String seenPolicymaker) {
        this.seenPolicymaker = seenPolicymaker == null ? null : seenPolicymaker.trim();
    }

    public String getPolicymakerPosition() {
        return policymakerPosition;
    }

    public void setPolicymakerPosition(String policymakerPosition) {
        this.policymakerPosition = policymakerPosition == null ? null : policymakerPosition.trim();
    }

    public String getDonePolicymaker() {
        return donePolicymaker;
    }

    public void setDonePolicymaker(String donePolicymaker) {
        this.donePolicymaker = donePolicymaker == null ? null : donePolicymaker.trim();
    }

    public String getDonePolicymakerPosition() {
        return donePolicymakerPosition;
    }

    public void setDonePolicymakerPosition(String donePolicymakerPosition) {
        this.donePolicymakerPosition = donePolicymakerPosition == null ? null : donePolicymakerPosition.trim();
    }

    public String getIsReal() {
        return isReal;
    }

    public void setIsReal(String isReal) {
        this.isReal = isReal == null ? null : isReal.trim();
    }

    public String getIsRealComment() {
        return isRealComment;
    }

    public void setIsRealComment(String isRealComment) {
        this.isRealComment = isRealComment == null ? null : isRealComment.trim();
    }

    public String getFiveUserUp() {
        return fiveUserUp;
    }

    public void setFiveUserUp(String fiveUserUp) {
        this.fiveUserUp = fiveUserUp == null ? null : fiveUserUp.trim();
    }

    public String getFiveUserUpComment() {
        return fiveUserUpComment;
    }

    public void setFiveUserUpComment(String fiveUserUpComment) {
        this.fiveUserUpComment = fiveUserUpComment == null ? null : fiveUserUpComment.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public Integer getCusEmpNum() {
        return cusEmpNum;
    }

    public void setCusEmpNum(Integer cusEmpNum) {
        this.cusEmpNum = cusEmpNum;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String cusCity) {
        this.cusCity = cusCity == null ? null : cusCity.trim();
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getNextPolicymakerAction() {
        return nextPolicymakerAction;
    }

    public void setNextPolicymakerAction(String nextPolicymakerAction) {
        this.nextPolicymakerAction = nextPolicymakerAction == null ? null : nextPolicymakerAction.trim();
    }

    public String getNextReqAction() {
        return nextReqAction;
    }

    public void setNextReqAction(String nextReqAction) {
        this.nextReqAction = nextReqAction == null ? null : nextReqAction.trim();
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getTrueCloseTime() {
        return trueCloseTime;
    }

    public void setTrueCloseTime(Date trueCloseTime) {
        this.trueCloseTime = trueCloseTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getImportantReq() {
        return importantReq;
    }

    public void setImportantReq(String importantReq) {
        this.importantReq = importantReq == null ? null : importantReq.trim();
    }
}