package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Util.FUtil;


public class PlayerLocationProcess {

	
	/**
	 * ����Ҵ��͵�Ĭ�ϵ�½λ��
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return �ɹ����ͷ���True Ĭ�ϵ�½λ�ò����ڷ���False
	 */
	public boolean TelePort_DefaultLoginLocation(Player playerEntity){
		
		
		if(! FUtil.defaultLocationEntity.getLocation_world().equalsIgnoreCase("empty")){
			
			playerEntity.teleport(FUtil.defaultLocationEntity.getLoginLocation());
			return true;
			
		}	
		
		return false;
		
	}
	
	
}
