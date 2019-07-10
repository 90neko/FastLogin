package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;


public class PlayerLocationProcess {

	
	/**
	 * ����Ҵ��͵�Ĭ�ϵ�½λ��
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return �ɹ����ͷ���True Ĭ�ϵ�½λ�ò����ڷ���False
	 */
	public boolean TelePort_DefaultLoginLocation(Player playerEntity){
		
		
		if(! ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")){
			
//			playerEntity.teleport(ConfigManager.getLocation().getLoginLocation());
			
			FastLogin.getAsyncProcess().AsyncTP(playerEntity, ConfigManager.getLocation().getLoginLocation());
			
			
			return true;
			
		}	
		
		return false;
		
	}
	
	
}
