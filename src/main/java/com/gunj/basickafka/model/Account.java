package com.gunj.basickafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
	String accountId;
	String name;
	char accType;
	
}
