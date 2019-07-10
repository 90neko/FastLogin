package com.ksptooi.FL.security;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Player.Async.PlayerAsyncProcess;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;

public class LoginSecurity {

	private ArrayList<String> opList=null;
	private ArrayList<String> creativeList=null;
	
	PlayerData_Interface PDBI=null;
	PlayerAsyncProcess asyncProcess = null;
	
	public LoginSecurity(){
		opList = new ArrayList<String>();
		creativeList = new ArrayList<String>();		
		PDBI=new PlayerDataManager();
		asyncProcess = new PlayerAsyncProcess();
	}
	
	
	
	public void joinServer_OpSecurityProcess(Player pl){
		
		PlayerEntity PD = PDBI.getPlayerData(pl);
		
		//�ж���û�п���OP��ȫ
		if(!ConfigManager.getConfig().isEnable_OPSecurity()){
			return;
		}
		
		
		//�жϴ�����Ƿ�ΪOP
		if(!pl.isOp()){
			return;
		}
		
		
		//������û��ע�����������OP
		if( ! PD.isRegister()){
			pl.sendMessage(ConfigManager.getLanguage().getOPHasBeenCleared());
			pl.setOp(false);
			return;
		}
			
		//������û�е�¼��ȡ������OP
		if( ! PD.isLogin()){
			pl.sendMessage(ConfigManager.getLanguage().getOPHasbeenCanceld());
			opList.add(pl.getName());
			pl.setOp(false);
			return;
		}
				
		
	}
	
	
	public void loginSuccess_OpSecurityProcess(Player pl){
		
		String name=pl.getName();
		
		for(int i=0;i<opList.size();i++){
			
			if(opList.get(i).equals(name)){
				
				asyncProcess.AsyncSetOP(pl, true);
				pl.sendMessage(ConfigManager.getLanguage().getOPRestore());
				opList.remove(i);
			}
			
		}
		
	}
	
	
	public void joinServer_CreativeSecurityProcess(Player pl){
		
		PlayerEntity PDE=PDBI.getPlayerData(pl);
		
		//�ж��Ƿ��� CreativeModeSecurity
		if(!ConfigManager.getConfig().isEnable_CreativeModeSecurity()){
			return;
		}
	
		//�ж�����Ƿ�ΪCREATIVEģʽ
		if(! (pl.getGameMode() == GameMode.CREATIVE)){
			return;
		}
		
		//�ж�����Ƿ�ע��
		if(! PDE.isRegister()){
			pl.sendMessage(ConfigManager.getLanguage().getCreativeHasBeenCleared());	
			asyncProcess.AsyncSetPlayerGameMode(pl, 0);
			pl.setGameMode(GameMode.SURVIVAL);
			return;
		}
		
		//�Ƿ��¼
		if(! PDE.isLogin()){
			pl.sendMessage(ConfigManager.getLanguage().getCreativeModeHasbeenCanceld());	
			asyncProcess.AsyncSetPlayerGameMode(pl, 0);
			creativeList.add(pl.getName());
			return;
		}
		
	}
	
	
	public void loginSuccess_CreativeSecurityProcess(Player pl){
		
		String name=pl.getName();
		
		for(int i=0;i<creativeList.size();i++){
			
			if(creativeList.get(i).equals(name)){
				
				asyncProcess.AsyncSetPlayerGameMode(pl, 1);
		
				pl.sendMessage(ConfigManager.getLanguage().getCreativeModeRestore());
				creativeList.remove(i);
			}
			
		}
		
		
	}
	
	
}
