package com.pan.active;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
/**
 * 消息消费者
 * @ClassName:  MessageConsumer   
 * @Description:
 * @author: 杨攀
 */
@Component
public class MessageConsumer {
             // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
//		@SendTo("return")
//         @JmsListener(destination = "mytest.queue")
//         public String receiveQueue(String text) {  
//	            System.out.println("Consumer收到的报文为:"+text); 
//				return "反回的消息";
//	     }  
}
