package com.acme.modres;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

@WebServlet({"/resorts/chat/connect"})
public class JmsConnectionCheckerServlet extends HttpServlet {

	private ConnectionFactory jmsConnectionFactory;
  
	private Queue queue;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

		Connection jmsConnection = null;
		try {
			InitialContext context = new InitialContext();
			jmsConnectionFactory = (ConnectionFactory) context.lookup("jms/mmxCf");
			queue = (Queue) context.lookup("jms/mmxQueue");
			jmsConnection = jmsConnectionFactory.createConnection();
			Session session = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        	session.createProducer(queue);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			if (jmsConnection != null) {
				try {
					jmsConnection.close();
				} catch (JMSException e) {
					return;
				}
			}
		}
    }
}