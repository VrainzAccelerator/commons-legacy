package com.vrainz.mcpmid.dbill.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ocsResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class DBillResponse {

	@XmlElement
	private String sequenceNo;

	@XmlElement
	private String scpTransactionId;

	@XmlElement
	private String callingParty;

	@XmlElement
	private String serviceType;
	
	@XmlElement
	private String serviceId;
	
	@XmlElement
	private String subsType;
	
	@XmlElement
	private String chargeAmount;	
	
	@XmlElement
	private String result;
	
	@XmlElement
	private String errorCode;
	
	@XmlElement
	private String serviceUsage;	
	
	@XmlElement
	private String accountBalance;
	
	@XmlElement
	private String requestedPlan;	

	@XmlElement
	private String appliedPlan;	

	@XmlElement
	private String discountPlan;

	@Override
	public String toString() {
		return "DBillResponse [sequenceNo=" + sequenceNo + ", scpTransactionId=" + scpTransactionId + ", callingParty="
				+ callingParty + ", serviceType=" + serviceType + ", serviceId=" + serviceId + ", subsType=" + subsType
				+ ", chargeAmount=" + chargeAmount + ", result=" + result + ", errorCode=" + errorCode
				+ ", serviceUsage=" + serviceUsage + ", accountBalance=" + accountBalance + ", requestedPlan="
				+ requestedPlan + ", appliedPlan=" + appliedPlan + ", discountPlan=" + discountPlan + "]";
	}		

}
