package com.sondeosglobal.simpleserver.handlers;

import com.sun.net.httpserver.HttpExchange;

public interface IMesssageListener {
	
	public void notify(HttpExchange t);

}
