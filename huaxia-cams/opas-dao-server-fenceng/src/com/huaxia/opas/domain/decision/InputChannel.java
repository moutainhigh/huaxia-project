package com.huaxia.opas.domain.decision;

import java.io.Serializable;
import java.sql.Date;


//opas_input_crontab
public class InputChannel implements Serializable{

	private static final long serialVersionUID = -1256505641077093520L;

	private String inputChannel;
	private String turn;
	private String time1;
	public String getInputChannel() {
		return inputChannel;
	}
	public void setInputChannel(String inputChannel) {
		this.inputChannel = inputChannel;
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	
}
