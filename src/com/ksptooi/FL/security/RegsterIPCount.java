package com.ksptooi.FL.security;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v5.Manager.IOController_V5;

public class RegsterIPCount {

	IOController_V5 v5=null; 
	Logger logManager=null;
	
	public RegsterIPCount(){
		v5=new IOController_V5();
		logManager = new Logger();
	}
	
	public boolean playerIp_isMaxReg(Player pl){
		
		String PlayerIP=pl.getAddress().getHostName();
		v5.setTarget(ConfigManager.fastLoginIPCountFile);
		
		logManager.debugMessage("���:"+pl.getName()+"IP��ַ:"+PlayerIP);
		
		//���MaxRegisterIP��0��ر��������
		if(ConfigManager.getConfig().getMaxRegisterIP()==0){			
			logManager.debugMessage("ip�����ѹر�!");		
			return false;
		}
		
		logManager.debugMessage("���:"+pl.getName()+"��IP����:"+v5.getRepeatLineCount(PlayerIP));
		
		//�ж�IP��û�г����޶�
		if(v5.getRepeatLineCount(PlayerIP) > ConfigManager.getConfig().getMaxRegisterIP()){
			logManager.debugMessage("���:"+pl.getName()+"������ip�޶�");
			return true;
		}
		
		//û�г����򷵻�һ��false
		logManager.debugMessage("���:"+pl.getName()+"û�г���ip�޶�");
		
		return false;
		
	}

	
	public void addIP(Player pl){
		
		//���MaxRegisterIP��0��ر��������
		if(ConfigManager.getConfig().getMaxRegisterIP()==0){			
			logManager.debugMessage("ip�����ѹر�!");		
			return;
		}
		
		logManager.debugMessage("���IP����:"+pl.getName()+","+pl.getAddress().getHostName());	
		
		String PlayerIP=pl.getAddress().getHostName();
		
		v5.setTarget(ConfigManager.fastLoginIPCountFile);
		
		v5.addLine(PlayerIP);
	}
	
	
	
}
