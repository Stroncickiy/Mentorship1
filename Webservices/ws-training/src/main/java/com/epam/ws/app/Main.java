package com.epam.ws.app;

import javax.xml.ws.Endpoint;

import com.epam.ws.soap.services.impl.BankingWebServiceImpl;

public class Main {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/banking", new BankingWebServiceImpl());

	}

}
