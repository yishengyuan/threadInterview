package com.shuosen.监听某个值变化;
 
public class Main {
 
	public static void main(String[] args) {
		EventProducer producer = new EventProducer();
		producer.addListener(new EventConsumer());
		producer.setValue(2);
 
	}
 
}