package com.wty.xml;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "APISubmission")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class APISubmissionXml implements Serializable {

    private String batchId;
    private String accountId;
    private String submissionDate;
    private String campaignId;
    private String actionTrackerId;
    private String mediaPartnerId;
    private String eventCode;
    private String orderId;
    private String payload;
    private String type;
    private String status;
    private String completionDate;
    private String errorType;
    private String errorReason;
    private String uri;

    public String getBatchId() {
        return batchId;
    }

    @XmlElement(name = "BatchId")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getAccountId() {
        return accountId;
    }
    @XmlElement(name = "AccountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    @XmlElement(name = "SubmissionDate")
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getCampaignId() {
        return campaignId;
    }

    @XmlElement(name = "CampaignId")
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getActionTrackerId() {
        return actionTrackerId;
    }

    @XmlElement(name = "ActionTrackerId")
    public void setActionTrackerId(String actionTrackerId) {
        this.actionTrackerId = actionTrackerId;
    }

    public String getMediaPartnerId() {
        return mediaPartnerId;
    }

    @XmlElement(name = "MediaPartnerId")
    public void setMediaPartnerId(String mediaPartnerId) {
        this.mediaPartnerId = mediaPartnerId;
    }

    public String getEventCode() {
        return eventCode;
    }

    @XmlElement(name = "EventCode")
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getOrderId() {
        return orderId;
    }

    @XmlElement(name = "OrderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayload() {
        return payload;
    }

    @XmlElement(name = "Payload")
    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "Type")
    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    @XmlElement(name = "Status")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    @XmlElement(name = "CompletionDate")
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getErrorType() {
        return errorType;
    }

    @XmlElement(name = "ErrorType")
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorReason() {
        return errorReason;
    }

    @XmlElement(name = "ErrorReason")
    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public String getUri() {
        return uri;
    }

    @XmlElement(name = "Uri")
    public void setUri(String uri) {
        this.uri = uri;
    }
}