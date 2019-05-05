package com.ksptooi.FL.playerThread;


import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.MultiVersion.AdvMultiVersionAsyncProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.Performance.PerformanceMonitorManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;


public class PlayerLoginMessageSendThread implements Runnable{
	
	
	Player pl=null;
	int LoginTime=0;
	int sendtime=0;
	AdvMultiVersionAsyncProcess AMVAP = null;

	
	PlayerDataBLL_Interface playerDataBLL=null;
	
	public PlayerLoginMessageSendThread(Player pl) {
		this.pl=pl;		
		playerDataBLL=new PlayerDataBLLimpl();
		AMVAP = new AdvMultiVersionAsyncProcess();

	}
	
	
	public void run(){

		//����߳����ܼ���
		PerformanceMonitorManager.addPATC();
		
		PlayerDataEntity PDE=playerDataBLL.getPlayerData(pl);
		
		
		//�ѵ�¼��ر��߳�
		if(PDE.isLogin()){
			FUtil.NoDamagePlayer.remove(pl.getName());
			PerformanceMonitorManager.removePATC();
			return;
		}
		
		
		
		if(PDE.isRegister()){
			
			pl.sendMessage(FUtil.language.getNotlogin());
			
		}else{
			
			pl.sendMessage(FUtil.language.getNotRegister());
			
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
			if(LoginTime > FUtil.config.getLoginTimeOut()){
				
				AMVAP.AsyncKickPlayer(pl,FUtil.language.getLoginTimeOutKick());
							
				break;
			}
			
			
			PDE=playerDataBLL.getPlayerData(pl);
			//�ѵ�¼��ر��߳�
			if(PDE.isLogin()){
				FUtil.NoDamagePlayer.remove(pl.getName());
				break;
			}
			
					
			if(!(sendtime >= FUtil.config.getMessageInterval())){
				continue;
			}
					
			sendtime=0;	
				
			
			//���͵�¼/ע����Ϣ
			if(PDE.isRegister()){
				
				pl.sendMessage(FUtil.language.getNotlogin());
				continue;
				
			}else{
				
				pl.sendMessage(FUtil.language.getNotRegister());
				continue;
			}
			
			
			
		}
		
		//����߳����ܼ���
		PerformanceMonitorManager.removePATC();
		
		
	}
	

}
