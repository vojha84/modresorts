package com.acme.modres.messaging;

import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.websocket.Decoder.Text;

@Stateless
public class ChatProducer {

public static String APP_IDENTIFIER = null;

@Inject
@JMSConnectionFactory("jms/mmxCf")
JMSContext context;

@Resource(lookup = "jms/mmxQueue")
Queue queue;

public ChatProducer() {
}

public void sendMessage(String message) throws Exception {
    if (APP_IDENTIFIER == null) {
      APP_IDENTIFIER = createAppIdentifier();
    }

    JMSProducer producer = context.createProducer();

    TextMessage m = context.createTextMessage();
    m.setText(message);
    m.setStringProperty("appId", APP_IDENTIFIER);

    producer.send(queue, m);
    System.out.println("Message enqueued: " + message);
  }

  // creates random ID of max 8 characters
  private static String createAppIdentifier() {
    String randomUUID = UUID.randomUUID().toString();
    return randomUUID.toString()
          .replaceAll("_", "")
          .substring(0,Math.min(randomUUID.length(), 8));  
  }

}