package com.gunj.basickafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gunj.basickafka.model.Account;

@RestController
@RequestMapping("kafka")
public class KafkaProducerController {
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	@Autowired
	private KafkaTemplate<String, Account> accTemplate;
	private final String myTopic = "Simple_Topic";
	private final String myJsonTopic = "Simple_Topic_Json";
	
	
	@GetMapping(value = "/publish/{msg}")
	public String publishMessages(@PathVariable("msg") final String msg)
	{
		template.send(myTopic, msg);
		return "Message Published to Simple_Topic";
	}
	
	@GetMapping(value="/publishJson/{accName}")
	public String publishJson(@PathVariable("accName") final String accName) {
		
		Message<Account> message = MessageBuilder
				.withPayload(new Account("1111111",accName,'C'))
				.setHeader(KafkaHeaders.TOPIC, myJsonTopic)
				.build();
 		accTemplate.send(message);
		
		return "JSON published to Simple_Topic_Json";
	}
	
}
