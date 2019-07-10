package com.ksptooi.FL.Thread.Player;


import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.General.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.PAsync.Task.AsyncTask;
import com.ksptooi.FL.Util.FUtil;


public class PlayerLoginMessageSendThread implements Runnable{
	
	
	FastPlayer pl=null;
	int LoginTime=0;
	int sendtime=0;
	AsyncTask AMVAP = null;

	
	PlayerData_Interface playerDataBLL=null;
	
	public PlayerLoginMessageSendThread(FastPlayer pl) {
		this.pl=pl;		
		playerDataBLL=new PlayerDataManager();
		AMVAP = new AsyncTask();

	}
	
	
	public void run(){

		//����߳����ܼ���
		PerformanceMonitorManager.addPATC();
		
		
		//�ѵ�¼��ر��߳�
		if(pl.isLogin()){
			FUtil.NoDamagePlayer.remove(pl.getName());
			PerformanceMonitorManager.removePATC();
			return;
		}
		
		
		
		if(pl.isRegister()){
			
			pl.sendMessage(ConfigManager.getLanguage().getNotlogin());
			
		}else{
			
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister());
			
		}
		
					 
		while(true){
			
			//ˢ�����״̬
			pl.reload();
			
			try {	
				Thread.sleep(1000);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendtime++;
			LoginTime++;
			
			//��������ر��߳�
			if(!pl.isOnline()){
				break;
			}
			
			
			//��½��ʱ��ر��߳� && �߳����
			if(LoginTime > ConfigManager.getConfig().getLoginTimeOut()){
				
				pl.kickPlayer(ConfigManager.getLanguage().getLoginTimeOutKick());			
							
				break;
			}
			
			pl.reload();
			//�ѵ�¼��ر��߳�
			if(pl.isLogin()){
				FUtil.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
			
			if(!(sendtime >= ConfigManager.getConfig().getMessageInterval())){
				continue;
			}
					
			sendtime=0;	
				
			
			//���͵�¼/ע����Ϣ
			if(pl.isRegister()){
				
				pl.sendMessage(ConfigManager.getLanguage().getNotlogin());
				continue;
				
			}else{
				
				pl.sendMessage(ConfigManager.getLanguage().getNotRegister());
				continue;
			}
			
			
			
		}
		
		//����߳����ܼ���
		PerformanceMonitorManager.removePATC();
		
		
	}
	

}
