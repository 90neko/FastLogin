package com.ksptooi.FL.security;

import java.io.File;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;

public class PlayerFilter {

	
	@SuppressWarnings("unused")
	public boolean isRealPlayer(FastPlayer pl){
		
		String Address=null;
	
		
		try{
			
			Address=pl.getAddress().getHostName();
			return true;
			
			
		}catch(Exception exc){
			return false;
		}
			
	}
	
	
	@SuppressWarnings("unused")
	public boolean isRealPlayer(Player pl){
		
		String Address=null;
	
		
		try{
			
			Address=pl.getAddress().getHostName();
			return true;
			
			
		}catch(Exception exc){
			return false;
		}
			
	}
	
	//�������Сд����
	public boolean playerNameisAllow(String playerName){
		
		
		//�Ƿ�ʹ�����ݿ�
		if(ConfigManager.getConfig().getPlayerDataType().equalsIgnoreCase("mysql")){
			return true;
		}
		
		//�Ƿ���������
		
		
		File PDF = new File(ConfigManager.fastLoginPlayerDataFolder);
		
		File[] PD = PDF.listFiles();
		
		
		for(File f:PD){
			
			if(f.getName().replace(".gd", "").equalsIgnoreCase(playerName)){
				
				if(f.getName().replace(".gd", "").equals(playerName)){
					return true;
				}
								
			}
				
		}	
		
		return false;
		
	}
	
	
	//Ѱ�������
	public String findPlayerName(String playerName){
		
		
		File PDF = new File(ConfigManager.fastLoginPlayerDataFolder);
		
		File[] PD = PDF.listFiles();
		
		
		for(File f:PD){
			
			if(f.getName().replace(".gd", "").equalsIgnoreCase(playerName)){
				return f.getName().replace(".gd", "");
			}
				
		}	
		
		return "empty";
		
	}
	
	
}

