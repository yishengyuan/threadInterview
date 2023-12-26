package com.shuosen.监听某个值变化;
 
public interface ValueChangeListener extends java.util.EventListener {
 
	public abstract void performed(ValueChangeEvent e);
}