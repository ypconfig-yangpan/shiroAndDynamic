package com.pan.active;



import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
/**
 * 消息生产者
 * @ClassName:  MessageProducer   
 * @Description:
 * @author: 杨攀
 */

@Service() 
public class MessageProducer {
	@Autowired
	private JmsTemplate JmsMessagingTemplate;
	
//	  @Autowired
//	  private ActiveMQQueue activeMQQueue; 

//	public void sendMessage( String string) {
//		System.out.println("发送成功....");
//		Destination destination = new ActiveMQQueue("mytest.queue"); 
//		JmsMessagingTemplate.convertAndSend(destination, string);
//		
//	}
	
//    @JmsListener(destination="return")  
//    public void consumerMessage(String text){  
//        System.out.println("从out.queue队列收到的回复报文为:"+text);  
//    } 
}
