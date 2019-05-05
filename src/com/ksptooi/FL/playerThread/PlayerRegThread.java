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


public class PlayerRegThread implements Runnable{

	
	Player pl=null;	
	String[] args=null;	
	PlayerDataBLL_Interface playerDataBLL=null;
	PlayerPasswordProcess pwdProcess=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerEffectProcess PEP=null;
	LogManager lm=null;
	AdvPasswordHash APH=null;
	

	
	public PlayerRegThread(Player PlayerEntity,String[] args){
		
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
				
			//检查注册参数是否合法
			if(args.length < 2){
				pl.sendMessage(FUtil.language.getNoConfirmPasswd());
				return ;
			}
			
			Passwd=args[0];
			ConfirmPasswd=args[1];


			//判断是否已注册
			PlayerDataEntity PDE = playerDataBLL.getPlayerData(pl);
			
			if(PDE.isRegister()){
				pl.sendMessage(FUtil.language.getRepeatRegister());
				return;
			}
			
			
			
			//判断ip是否已经达到限额
			if(FUtil.RIC.playerIp_isMaxReg(pl)){
				pl.sendMessage("注册失败,每个IP最多只能注册"+FUtil.config.getMaxRegisterIP()+"个账号,你已超出限额！");
				return;
			}
				
			
			//检查密码长度
			if(!pwdProcess.passWordLengthIsAccess(pl, ConfirmPasswd)){
				
				return;
			}
			
			
			//检查密码与确认密码是否一致
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
			
			//为玩家添加粒子效果
			PEP.addLoginedEffect(pl);			
			//移除玩家的失明效果
			PEP.removePreLoginEffect(pl);
			
			// 自定义登录消息
			lm.ShowMessage(pl);

			
			//将玩家传送到默认登陆位置
			playerLocationProcess.TelePort_DefaultLoginLocation(pl);
			
			
			return;
						
			
		} catch (Exception e){
			
			pl.sendMessage(FUtil.language.getNullPassword());
			return;
			
		}
		
			
		
	}

	
	
}
