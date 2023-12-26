package com.shuosen.监听某个值变化;
 
public class EventProducer {
	ListenerRegister register = new ListenerRegister();
	private int value;
 
	public int getValue() {
		return value;
	}
 
	public void setValue(int newValue) {

		// 监听哪些变化的字段
		if (value != newValue) {
			value = newValue;
			ValueChangeEvent event = new ValueChangeEvent(this, value);
			fireAEvent(event);
		}
	}
 
	public void addListener(ValueChangeListener a) {
		register.addListener(a);
	}
 
	public void removeListener(ValueChangeListener a) {
		register.removeListener(a);
	}
 
	public void fireAEvent(ValueChangeEvent event) {
		register.fireAEvent(event);
	}
 
}