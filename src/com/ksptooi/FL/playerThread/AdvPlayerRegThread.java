package com.ksptooi.FL.playerThread;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.security.AdvPasswordHash;


public class AdvPlayerRegThread implements Runnable{

	
	Player pl=null;	
	String[] args=null;	
	PlayerData_Interface playerDataBLL=null;
	PlayerPasswordProcess pwdProcess=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerEffectProcess PEP=null;
	Logger lm=null;
	AdvPasswordHash APH=null;
	

	
	public AdvPlayerRegThread(Player PlayerEntity,String[] args){
		
		playerDataBLL=new PlayerDataManager();
		this.pl=PlayerEntity;		
		this.args=args;		
		pwdProcess=new PlayerPasswordProcess();
		playerLocationProcess=new PlayerLocationProcess();
		PEP = new PlayerEffectProcess();
		lm=new Logger();
		APH = new AdvPasswordHash();
		
	}
	
	
	public void run() {
		
		//����߳����ܼ���
		PerformanceMonitorManager.addPATC();
			
		try{		
			
			
			String Passwd=null;
			String ConfirmPasswd=null;
				
			//���ע������Ƿ�Ϸ�
			if(args.length < 3){
				pl.sendMessage(ConfigManager.getLanguage().getNoConfirmPasswd());
				PerformanceMonitorManager.removePATC();
				return ;
			}
			
			Passwd=args[1];
			ConfirmPasswd=args[2];


			//�ж��Ƿ���ע��
			PlayerEntity PDE = playerDataBLL.getPlayerData(pl);
			
			if(PDE.isRegister()){
				pl.sendMessage(ConfigManager.getLanguage().getRepeatRegister());
				PerformanceMonitorManager.removePATC();
				return;
			}
			
			
			
			//�ж�ip�Ƿ��Ѿ��ﵽ�޶�	
			if(FUtil.RIC.playerIp_isMaxReg(pl)){
				pl.sendMessage("ע��ʧ��,ÿ��IP���ֻ��ע��"+ConfigManager.getConfig().getMaxRegisterIP()+"���˺�,���ѳ����޶");
				PerformanceMonitorManager.removePATC();
				return;
			}
				
			
			//������볤��
			if(!pwdProcess.passWordLengthIsAccess(pl, ConfirmPasswd)){
				PerformanceMonitorManager.removePATC();
				return;
			}
			
			
			//���������ȷ�������Ƿ�һ��
			if(!(Passwd.equals(ConfirmPasswd))){
				pl.sendMessage(ConfigManager.getLanguage().getConfirmPasswdError());
				PerformanceMonitorManager.removePATC();
				return ;
			}
			
			
			PDE.setPassword(APH.autoCompression(ConfirmPasswd));
			PDE.setRegister(true);
			PDE.setLogin(true);
			
			playerDataBLL.updatePlayerData(PDE);
			
			pl.sendMessage(ConfigManager.getLanguage().getRegisterSuccess());
			
			FUtil.RIC.addIP(pl);
			
			//Ϊ����������Ч��
			PEP.addLoginedEffect(pl);			
			//�Ƴ���ҵ�ʧ��Ч��
			PEP.removePreLoginEffect(pl);
			
			// �Զ����¼��Ϣ
			lm.ShowMessage(pl);

			
			//����Ҵ��͵�Ĭ�ϵ�½λ��
			playerLocationProcess.TelePort_DefaultLoginLocation(pl);
		
			PerformanceMonitorManager.removePATC();
			return;
						
			
		} catch (Exception e){
			
			pl.sendMessage(ConfigManager.getLanguage().getNullPassword());
			PerformanceMonitorManager.removePATC();
			return;
			
		}
		
			
		
	}

	
	
}
