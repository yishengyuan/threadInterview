package com.shuosen.监听某个值变化;
 
public class EventConsumer implements ValueChangeListener {
 
	@Override
	public void performed(ValueChangeEvent e) {
		System.out.println("value changed, new value = " + e.getValue());
	}
}
 