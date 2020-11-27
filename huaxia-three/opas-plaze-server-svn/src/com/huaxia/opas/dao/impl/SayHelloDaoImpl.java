package com.huaxia.opas.dao.impl;

import org.springframework.stereotype.Repository;

import com.huaxia.opas.dao.SayHelloDao;

@Repository
public class SayHelloDaoImpl implements SayHelloDao {

	@Override
	public String say(String name) {
		return "Hello, " + name;
	}

}
