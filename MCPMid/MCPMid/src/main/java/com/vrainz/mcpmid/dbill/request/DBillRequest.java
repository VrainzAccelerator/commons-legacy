package com.vrainz.mcpmid.dbill.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ocsRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class DBillRequest {
	
	@XmlElement
	private String requestType;

	@XmlElement
	private String serviceNode;

	@XmlElement
	private String sequenceNo;

	@XmlElement
	private String callingParty;
	
	@XmlElement
	private String serviceType;

	@XmlElement
	private String serviceId;
	
	@XmlElement
	private String bearerId;
	
	@XmlElement
	private String chargeAmount;	
	
	@XmlElement
	private String planId;
	
	@XmlElement
	private String asyncFlag;	
	
	@XmlElement
	private String renewalFlag;
	
	@XmlElement
	private String bundleType;	

	@XmlElement
	private String serviceUsage;	

	@XmlElement
	private String promoId;
	
	@XmlElement
	private String subscriptionFlag;

	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getServiceNode() {
		return serviceNode;
	}

	public void setServiceNode(String serviceNode) {
		this.serviceNode = serviceNode;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getCallingParty() {
		return callingParty;
	}

	public void setCallingParty(String callingParty) {
		this.callingParty = callingParty;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getBearerId() {
		return bearerId;
	}

	public void setBearerId(String bearerId) {
		this.bearerId = bearerId;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getAsyncFlag() {
		return asyncFlag;
	}

	public void setAsyncFlag(String asyncFlag) {
		this.asyncFlag = asyncFlag;
	}

	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	public String getBundleType() {
		return bundleType;
	}

	public void setBundleType(String bundleType) {
		this.bundleType = bundleType;
	}

	public String getServiceUsage() {
		return serviceUsage;
	}

	public void setServiceUsage(String serviceUsage) {
		this.serviceUsage = serviceUsage;
	}

	public String getPromoId() {
		return promoId;
	}

	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	public String getSubscriptionFlag() {
		return subscriptionFlag;
	}

	public void setSubscriptionFlag(String subscriptionFlag) {
		this.subscriptionFlag = subscriptionFlag;
	}

	@Override
	public String toString() {
		return "DBillRequest [requestType=" + requestType + ", serviceNode=" + serviceNode + ", sequenceNo="
				+ sequenceNo + ", callingParty=" + callingParty + ", serviceType=" + serviceType + ", serviceId="
				+ serviceId + ", bearerId=" + bearerId + ", chargeAmount=" + chargeAmount + ", planId=" + planId
				+ ", asyncFlag=" + asyncFlag + ", renewalFlag=" + renewalFlag + ", bundleType=" + bundleType
				+ ", serviceUsage=" + serviceUsage + ", promoId=" + promoId + ", subscriptionFlag=" + subscriptionFlag
				+ "]";
	}
	
}
