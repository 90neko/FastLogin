package com.ksptooi.security;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;

public class LoginGhostDefense {

	LogManager lm=null;
	
	public LoginGhostDefense(){
		lm=new LogManager();
	}
	
	public boolean isGhostPlayer(Player pl){
		
		int i=0;

		
		for(Player p:Bukkit.getServer().getOnlinePlayers()){
			
			if(p.getName().equalsIgnoreCase(pl.getName())){
				i++;
			}
			
		}
		
		
		if(i>1){
			return true;
		}
		
		return false;
		
	}
	
	public void kickPlayer(final Player pl){
		
		
		if(FUtil.config.isEnable_SecurityWarning()){
			
			
			for(Player p:Bukkit.getServer().getOnlinePlayers()){
				p.sendMessage("��e[FastLoginSecurity]��c�����"+pl.getName()+"��Ϊ������Ӱ����Bug�����߳�������.");
			}
			
			lm.writerError("���"+pl.getName()+"��Ϊ������Ӱ����Bug�����߳�������");
			
		}
		
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			@Override
			public void run() {
				
				for(Player p:Bukkit.getServer().getOnlinePlayers()){
					
					if(p.getName().equalsIgnoreCase(pl.getName())){
						p.kickPlayer("������������ͬ���������!");
					}
				
				}
				
			}
			

		});
		
	}
	
	
	
}
