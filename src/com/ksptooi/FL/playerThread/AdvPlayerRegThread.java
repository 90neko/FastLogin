package com.ksptooi.FL.playerThread;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.security.AdvPasswordHash;


public class AdvPlayerRegThread implements Runnable{

	
	Player pl=null;	
	String[] args=null;	
	PlayerDataBLL_Interface playerDataBLL=null;
	PlayerPasswordProcess pwdProcess=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerEffectProcess PEP=null;
	LogManager lm=null;
	AdvPasswordHash APH=null;
	

	
	public AdvPlayerRegThread(Player PlayerEntity,String[] args){
		
		playerDataBLL=new PlayerDataBLLimpl();
		this.pl=PlayerEntity;		
		this.args=args;		
		pwdProcess=new PlayerPasswordProcess();
		playerLocationProcess=new PlayerLocationProcess();
		PEP = new PlayerEffectProcess();
		lm=new LogManager();
		APH = new AdvPasswordHash();
		
	}
	
	
	public void run() {
		
			
		try{		
			
			
			String Passwd=null;
			String ConfirmPasswd=null;
				
			//���ע������Ƿ�Ϸ�
			if(args.length < 3){
				pl.sendMessage(FUtil.language.getNoConfirmPasswd());
				return ;
			}
			
			Passwd=args[1];
			ConfirmPasswd=args[2];


			//�ж��Ƿ���ע��
			PlayerDataEntity PDE = playerDataBLL.getPlayerData(pl);
			
			if(PDE.isRegister()){
				pl.sendMessage(FUtil.language.getRepeatRegister());
				return;
			}
			
			
			
			//�ж�ip�Ƿ��Ѿ��ﵽ�޶�	
			if(FUtil.RIC.playerIp_isMaxReg(pl)){
				pl.sendMessage("ע��ʧ��,ÿ��IP���ֻ��ע��"+FUtil.config.getMaxRegisterIP()+"���˺�,���ѳ����޶");
				return;
			}
				
			
			//������볤��
			if(!pwdProcess.passWordLengthIsAccess(pl, ConfirmPasswd)){
				
				return;
			}
			
			
			//���������ȷ�������Ƿ�һ��
			if(!(Passwd.equals(ConfirmPasswd))){
				pl.sendMessage(FUtil.language.getConfirmPasswdError());
				return ;
			}
			
			
			PDE.setPassword(APH.autoCompression(ConfirmPasswd));
			PDE.setRegister(true);
			PDE.setLogin(true);
			
			playerDataBLL.updatePlayerData(PDE);
			
			pl.sendMessage(FUtil.language.getRegisterSuccess());
			
			FUtil.RIC.addIP(pl);
			
			//Ϊ����������Ч��
			PEP.addLoginedEffect(pl);			
			//�Ƴ���ҵ�ʧ��Ч��
			PEP.removePreLoginEffect(pl);
			
			// �Զ����¼��Ϣ
			lm.ShowMessage(pl);

			
			//����Ҵ��͵�Ĭ�ϵ�½λ��
			playerLocationProcess.TelePort_DefaultLoginLocation(pl);
			
			
			return;
						
			
		} catch (Exception e){
			
			pl.sendMessage(FUtil.language.getNullPassword());
			return;
			
		}
		
			
		
	}

	
	
}
