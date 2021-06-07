package com.vrainz.mcpmid.callback.parameters;

import javax.ws.rs.QueryParam;

public class RedirectCallbackParameters {

	@QueryParam("MSISDN")
	String msisdn;
	@QueryParam("Result")
	String result;
	@QueryParam("Reason")
	String reason;
	@QueryParam("productId")
	String productId;
	@QueryParam("transID")
	String transId;
	@QueryParam("TPCGID")
	String tpcgid;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTpcgid() {
		return tpcgid;
	}
	public void setTpcgid(String tpcgid) {
		this.tpcgid = tpcgid;
	}
	
	@Override
	public String toString() {
		return "RedirectCallbackParameters [msisdn=" + msisdn + ", result=" + result + ", reason=" + reason
				+ ", productId=" + productId + ", transId=" + transId + ", tpcgid=" + tpcgid + "]";
	}
	
}
