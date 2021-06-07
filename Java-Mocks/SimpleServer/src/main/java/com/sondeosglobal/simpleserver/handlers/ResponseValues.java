package com.sondeosglobal.simpleserver.handlers;

import java.util.HashMap;
import java.util.Map;


/**
 * result code y result message para claro colombia, según el http status recibido de hermes.
 * 
 * 
 * @author Luis Esteche
 *
 */
public class ResponseValues {
	//TODO Se podría poner en un archivo de propiedades, xml, ws, etc .
	private String code;
	private String message;
	private static Map<Integer, ResponseValues> values = new HashMap <Integer, ResponseValues>();
	static {
		//TODO Los key negativos representan los casos sin asociación entre el http status y el response 
		values.put(200,  new ResponseValues("00000000","success"));
		values.put(1000, new ResponseValues("10001211","malformed or illegal field values"));
		values.put(2409, new ResponseValues("10002030","order relationship already exists"));
		values.put(3404, new ResponseValues("10002031","order relationship does not exist"));
		values.put(2404, new ResponseValues("10002032","service does not exist"));
		values.put(-5,   new ResponseValues("10002033","service unavailable"));
		values.put(-6,   new ResponseValues("10002034","service can not be ordered"));
		values.put(-7,   new ResponseValues("10002035","synchronize order relations to SP failed"));
		values.put(500,  new ResponseValues("10002500","system internal error"));
		
	}

	public static ResponseValues get(Integer httpStatus){
		if(httpStatus == null){
			return null;
		}
		return values.get(httpStatus);
		
	}
	private ResponseValues(String code, String message){
		this.code = code;
		this.message = message;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
