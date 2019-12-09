package com.boxfuse.samples.tomee;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class MovieQueueSender {
	
	private QueueConnection connection;
	private QueueSession sessoin;
	private Queue queue;
	
	public MovieQueueSender() throws Exception {
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");

		//Create JMS Connection, session and queue objects
		//InitialContext initCtx = new InitialContext();
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory)envContext.lookup("jms/movieManagemenCF");
		
		System.out.println("QueueConnectionFactory: " + connectionFactory.getClass().getTypeName() + "  " + connectionFactory.getClass().getCanonicalName());
		
		connection = connectionFactory.createQueueConnection();
		connection.start();
		sessoin = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue)envContext.lookup("jms/movieManagementQueue");

		System.out.println("Queue: " + queue.getQueueName() + "  " + queue.getClass().getCanonicalName());
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}
	
	public void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendAddMovieMessage (Movie movie) throws Exception {
		//Send Movie object to JMS Queue
		QueueSender sender = sessoin.createSender(queue);
		//ObjectMessage objMessage = sessoin.createObjectMessage(movie);
		//sender.send(objMessage);
		TextMessage textMessage = sessoin.createTextMessage(movie.toString());
		sender.send(textMessage);
		System.out.println("###########Message sent to Queue##############" + movie.toString());
	}
}
