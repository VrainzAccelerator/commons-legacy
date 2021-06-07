package com.sondeosglobal.simpleserver.monitor;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public abstract class AbstractMessageObserver {
	
	   protected MessageMonitor monitor;
	   public abstract void messageArrived(HttpExchange httpExchange);

}
