package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;

public class PlayerLoginEvent implements FastEvent{

	
	FastPlayer pl = null;
	String[] param = null;
	PlayerDataManager pdm = null;
	PlayerPasswordProcess PlayerPasswordProcess = null;
	Language lang = null;
	PlayerEffectManager playerEffectManager = null;
	
	
	public PlayerLoginEvent(FastPlayer pl,String[] args) {
		
		this.pl=pl;
		this.param=args;
		this.pdm = DataManager.getPlayerDataManager();
		this.PlayerPasswordProcess = new PlayerPasswordProcess();	
		this.lang = ConfigManager.getLanguage();
		this.playerEffectManager = FastLogin.getPlayerEffectManager();
	}
	
	
	
	
	@Override
	public void run() {
		
		pl.reload();
		
		
		//�ѵ�¼��ر��߳�
		if (pl.isLogin()) {		
			pl.sendMessage(ConfigManager.getLanguage().getRepeatLogin());
			return;
		}
		
		
		// δע����ر��߳�
		if(!pl.isRegister()){	
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister2());
			return;
		}
		
		
		// ��2��ͬ�����������ȫ���߳����ر��߳�(Ӱ����)
		if(pl.isGhostPlayer()){		
			pl.kickPlayer("������������ͬ���������!");	
			return;
		}
		
		//����������ر��߳�
		if(param.length<1) {
			pl.sendMessage(lang.getNullPassword());
			return;
		}
		
		
		try {
			
			
			//������������ر��߳�
			if(!pl.isRightPassword(param[0])) {
				pl.sendMessage(lang.getPasswordError());
				return ;
			}
			
			
			//��½�ɹ� �����¼�
			PlayerLoginSuccessEvent pls=new PlayerLoginSuccessEvent(pl);
			
			FastLogin.getEventManager().runFastEvent(pls);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			pl.sendMessage("��½��������!,����ϵ����Ա.");
		}
		
		
		
	}
	

	
	
}
