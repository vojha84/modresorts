package com.acme.modres;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acme.modres.messaging.ChatProducer;
import com.acme.modres.messaging.ChatReceiver;

import javax.servlet.annotation.WebServlet;

@Stateless
@WebServlet({"/resorts/chat"})
public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;  

    private static final Logger logger = Logger.getLogger(WeatherServlet.class.getName());

    @Inject
  	ChatProducer producer;

	@Inject
	ChatReceiver listener;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
		// create HTML response
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		try {
			String text = listener.receive();
			writer.append(text);
		} catch (Exception e) {
			return;
		}
    }

	/**
	 * Posts a chat to MQ
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = extractPostRequestBody(request);
		response.setContentType("text/plain");
		// create HTML response
        PrintWriter writer = response.getWriter();
		writer.append(message);
		
		try {
			producer.sendMessage(message);
		} catch (Exception e) {	
			return;
		}
	}
	
	private static String extractPostRequestBody(HttpServletRequest request) throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
		return "";
	}
}