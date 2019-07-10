package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Player.Check.PlayerNameRuleCheck;
import com.ksptooi.FL.Util.FUtil;

public class PlayerJo1nEvent implements FastEvent{

	FastPlayer pl = null;
	
	public PlayerJo1nEvent(FastPlayer pl) {
		this.pl = pl;
	}
	
	
	
	@Override
	public void run() {
		
		PlayerNameRuleCheck nameRuleCheck = DataManager.getPlayerNameRuleCheck();
		
		
		//��֤�Ƿ�ΪrealPlayer
		if(!pl.isRealPlayer()){
			return;
		}
		
		//���������ݻ���
		PlayerDataCache.removePlayerData(pl.getName());
		
		//��ʼ���������
		pl.createData();
			
		pl.setLogin(false);
		
		pl.save();
			
		
		pl.tpFastSpawn();
		
		
		//OP��ȫ���
		FUtil.LS.joinServer_OpSecurityProcess(pl);
		//���찲ȫ���
		FUtil.LS.joinServer_CreativeSecurityProcess(pl);
		
		
		//������Ƽ��
		if(! nameRuleCheck.nameValid(pl)){
			return;
		}
		
		//Ϊ������ʧ��Ч��
		pl.addPreLoginEffect();
		
		//���Online�б�
		FastLogin.addOnlinePlayer(pl.getName(), pl);	
		
		//ȫ��ͨ������һ����ҵ�¼����߳�
		PlayerTelemetryEvent eve=new PlayerTelemetryEvent(pl);
		FastLogin.getEventManager().runFastEvent(eve);
			
	}

}
