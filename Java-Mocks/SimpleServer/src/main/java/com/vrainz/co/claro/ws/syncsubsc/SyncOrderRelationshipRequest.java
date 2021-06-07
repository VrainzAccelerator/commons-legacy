package com.vrainz.co.claro.ws.syncsubsc;

import javax.xml.bind.annotation.XmlRootElement;

import com.vrainz.co.claro.ws.syncsubsc.generated.NamedParameterList;
import com.vrainz.co.claro.ws.syncsubsc.generated.UserID;

@XmlRootElement(name="syncOrderRelationship")
public class SyncOrderRelationshipRequest {
	public static enum OPERATION{SUBSCRIBE, UNSUBSCRIBE};
	
	UserID userID;
	String spID;
	String productID;
	String serviceID;
	int updateType;
	String creatTime;
	String effectiveTime;
	String expireTime;
	String notifyAddress;
	int rentResult;
	NamedParameterList extensionInfo;
	
	private OPERATION operation;
	public boolean isToSubscribe(){
		return OPERATION.SUBSCRIBE.equals(this.getOperation());
	}
	public boolean isToUnSubscribe(){
		return OPERATION.UNSUBSCRIBE.equals(this.getOperation());
	}
	public UserID getUserID() {
		return userID;
	}
	public void setUserID(UserID userID) {
		this.userID = userID;
	}
	public String getSpID() {
		return spID;
	}
	public void setSpID(String spID) {
		this.spID = spID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public int getUpdateType() {
		return updateType;
	}
	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getNotifyAddress() {
		return notifyAddress;
	}
	public void setNotifyAddress(String notifyAddress) {
		this.notifyAddress = notifyAddress;
	}
	public int getRentResult() {
		return rentResult;
	}
	public void setRentResult(int rentResult) {
		this.rentResult = rentResult;
	}
	public NamedParameterList getExtensionInfo() {
		return extensionInfo;
	}
	public void setExtensionInfo(NamedParameterList extensionInfo) {
		this.extensionInfo = extensionInfo;
	}
	
	
	public OPERATION getOperation() {
		return operation;
	}
	public void setOperation(OPERATION operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("SyncOrderRelationshipRequest [userID=");
		builder.append(userID);
		builder.append(", spID=");
		builder.append(spID);
		builder.append(", productID=");
		builder.append(productID);
		builder.append(", serviceID=");
		builder.append(serviceID);
		builder.append(", updateType=");
		builder.append(updateType);
		builder.append(", creatTime=");
		builder.append(creatTime);
		builder.append(", effectiveTime=");
		builder.append(effectiveTime);
		builder.append(", expireTime=");
		builder.append(expireTime);
		builder.append(", notifyAddress=");
		builder.append(notifyAddress);
		builder.append(", rentResult=");
		builder.append(rentResult);
		builder.append(", extensionInfo=");
		builder.append(extensionInfo);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
