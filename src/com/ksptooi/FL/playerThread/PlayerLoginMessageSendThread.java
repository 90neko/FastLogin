package com.ksptooi.FL.playerThread;


import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.Process.Player.PlayerAsyncProcess;
import com.ksptooi.FL.Util.FUtil;


public class PlayerLoginMessageSendThread implements Runnable{
	
	
	Player pl=null;
	int LoginTime=0;
	int sendtime=0;
	PlayerAsyncProcess AMVAP = null;

	
	PlayerData_Interface playerDataBLL=null;
	
	public PlayerLoginMessageSendThread(Player pl) {
		this.pl=pl;		
		playerDataBLL=new PlayerDataManager();
		AMVAP = new PlayerAsyncProcess();

	}
	
	
	public void run(){

		//����߳����ܼ���
		PerformanceMonitorManager.addPATC();
		
		PlayerEntity PDE=playerDataBLL.getPlayerData(pl);
		
		
		//�ѵ�¼��ر��߳�
		if(PDE.isLogin()){
			FUtil.NoDamagePlayer.remove(pl.getName());
			PerformanceMonitorManager.removePATC();
			return;
		}
		
		
		
		if(PDE.isRegister()){
			
			pl.sendMessage(ConfigManager.getLanguage().getNotlogin());
			
		}else{
			
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister());
			
		}
		
					 
		while(true){
			
			//ˢ�����״̬
			PDE=playerDataBLL.getPlayerData(pl);
			
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
				
				AMVAP.AsyncKickPlayer(pl,ConfigManager.getLanguage().getLoginTimeOutKick());
							
				break;
			}
			
			
			PDE=playerDataBLL.getPlayerData(pl);
			//�ѵ�¼��ر��߳�
			if(PDE.isLogin()){
				FUtil.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
					
			if(!(sendtime >= ConfigManager.getConfig().getMessageInterval())){
				continue;
			}
					
			sendtime=0;	
				
			
			//���͵�¼/ע����Ϣ
			if(PDE.isRegister()){
				
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
