package com.sondeosglobal.simpleserver.monitor;

import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class MessageMonitor {
	
	private static MessageMonitor instance;
	
	public static MessageMonitor getInstance() {
		if (instance == null) {
			instance = new MessageMonitor();
		}
		
		return instance;
	}
	
	private List<AbstractMessageObserver>	observers	= new ArrayList<AbstractMessageObserver>();

	public void add(AbstractMessageObserver observer) {
		observers.add(observer);
	}

	public void notifyAllObservers(HttpExchange httpExchange) {
		for (AbstractMessageObserver observer : observers) {
			observer.messageArrived(httpExchange);
		}
	}
}
