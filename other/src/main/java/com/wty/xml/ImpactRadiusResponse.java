package com.wty.xml;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author peter
 * @date 2022/5/6 12:55
 */
@XmlRootElement(name = "ImpactRadiusResponse")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ImpactRadiusResponse implements Serializable {

    private String status;
    private String queuedUri;
    private APISubmissionXml apiSubmissionXml;

    public String getStatus() {
        return status;
    }
    @XmlElement(name = "Status")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getQueuedUri() {
        return queuedUri;
    }
    @XmlElement(name = "QueuedUri")
    public void setQueuedUri(String queuedUri) {
        this.queuedUri = queuedUri;
    }

    @XmlElement(name = "APISubmission")
    public APISubmissionXml getApiSubmissionXml() {
        return apiSubmissionXml;
    }

    public void setApiSubmissionXml(APISubmissionXml apiSubmissionXml) {
        this.apiSubmissionXml = apiSubmissionXml;
    }
}
