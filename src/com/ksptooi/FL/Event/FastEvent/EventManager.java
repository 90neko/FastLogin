package com.ksptooi.FL.Event.FastEvent;

import java.util.ArrayList;

import com.ksptooi.FL.Event.EventHandler.LittleEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerLoginEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerLoginSuccessEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerRegisterEvent;
import com.ksptooi.FL.Event.EventHandler.LittlePlayerRegisterSuccessEvent;
import com.ksptooi.FL.Thread.Pool.PlayerTaskThreadPool;

public class EventManager {

	
	private PlayerTaskThreadPool taskThreadPool = null;
	
	private ArrayList<LittleEvent> listLittleEvent = null;
	
	public EventManager() {
		taskThreadPool = new PlayerTaskThreadPool();
		listLittleEvent = new ArrayList<LittleEvent>();
	}
	
	
	//��ʼFast�¼�
	public void runFastEvent(FastEvent event) {
			
		taskThreadPool.runTask(event);			
		
	}
	
	
	//ִ��Little�¼�(��ҵ�½)
	public void startPlayerLoginEvent(LittlePlayerLoginEvent event) {
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerLogin(event);
				}
				
			}
			
		});		

		
	}
	
	//ִ��Little�¼�(��ҵ�½���)
	public void startPlayerLoginSuccessEvent(LittlePlayerLoginSuccessEvent event) {
		
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerLoginSuccess(event);
				}
				
			}	
			
		});	
		
	
		
	}
	
	//ִ��Little�¼�(���ע��)
	public void startPlayerRegisterEvent(LittlePlayerRegisterEvent event) {
		
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerRegister(event);
				}
				
			}	
			
		});	
		

		
	}
	
	//ִ��Little�¼�(��ҵ�½)
	public void startPlayerRegisterSuccessEvent(LittlePlayerRegisterSuccessEvent event) {
		
		
		taskThreadPool.runTask(new Runnable(){
			
			public void run() {
				
				for(LittleEvent le:listLittleEvent){
					le.onPlayerRegisterSuccess(event);
				}
				
			}	
			
		});	
		
		
	}
	
	
	
	//ע���¼�
	public void regLittleEvent(LittleEvent event) {
		listLittleEvent.add(event);	
	}
	
	
	
	
	
	
}
