package com.ksptooi.FL.FastEvent;

public class EventManager {

	
	EventHandler handler=new EventHandler();
	
	
	//��ʼ��ҵ�½�¼�
	public void startPlayerLoginEvent(PlayerLoginEvent event) {
		
		//ִ���Դ����¼�����
		handler.onPlayerLoginEvent(event);
		
	}
	
	
	
	
	
	
	
}
