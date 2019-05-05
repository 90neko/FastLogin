package com.ksptooi.security;

import java.io.File;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Util.FUtil;

public class PlayerFilter {

	
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
	
	//玩家名大小写过滤
	public boolean playerNameisAllow(String playerName){
		
		
		//是否使用数据库
		if(FUtil.config.getPlayerDataType().equalsIgnoreCase("mysql")){
			return true;
		}
		
		//是否开启本功能
		
		
		File PDF = new File(FUtil.fastLoginPlayerDataFolder);
		
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
	
	
	//寻找玩家名
	public String findPlayerName(String playerName){
		
		
		File PDF = new File(FUtil.fastLoginPlayerDataFolder);
		
		File[] PD = PDF.listFiles();
		
		
		for(File f:PD){
			
			if(f.getName().replace(".gd", "").equalsIgnoreCase(playerName)){
				return f.getName().replace(".gd", "");
			}
				
		}	
		
		return "empty";
		
	}
	
	
}

