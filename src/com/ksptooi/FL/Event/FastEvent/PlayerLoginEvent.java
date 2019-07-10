package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;

public class PlayerLoginEvent implements LittleEvent{

	
	Player pl = null;
	String[] param = null;
	PlayerDataManager pdm = null;
	PlayerPasswordProcess PlayerPasswordProcess = null;
	Language lang = null;
	PlayerEffectManager playerEffectManager = null;
	
	
	public PlayerLoginEvent(Player pl,String[] args) {
		
		this.pl=pl;
		this.param=args;
		this.pdm = DataManager.getPlayerDataManager();
		this.PlayerPasswordProcess = new PlayerPasswordProcess();	
		this.lang = ConfigManager.getLanguage();
		this.playerEffectManager = FastLogin.getPlayerEffectManager();
	}
	
	
	
	
	@Override
	public void run() {
		
		PlayerEntity pe = pdm.getPlayerData(pl);
		
		
		//�ѵ�¼��ر��߳�
		if (pe.isLogin()) {		
			pl.sendMessage(ConfigManager.getLanguage().getRepeatLogin());
			return;
		}
		
		
		// δע����ر��߳�
		if(!pe.isRegister()){	
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister2());
			return;
		}
		
		
		// ��2��ͬ�����������ȫ���߳����ر��߳�(Ӱ����)
		if(FUtil.LGD.isGhostPlayer(pl)){		
			FastLogin.getAsyncProcess().AsyncKickPlayer(pl, "������������ͬ���������!");			
			return;
		}
		
		//����������ر��߳�
		if(param.length<1) {
			pl.sendMessage(lang.getNullPassword());
			return;
		}
		
		
		try {
			
			
			//������������ر��߳�
			if(!PlayerPasswordProcess.isRightPassword(pe, param[0])) {
				pl.sendMessage(lang.getPasswordError());
				return ;
			}
			
			
			//��½�ɹ� �����¼�
			PlayerLoginSuccessEvent pls=new PlayerLoginSuccessEvent(pe,pl);
			
			FastLogin.getEventManager().runEvent(pls);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			pl.sendMessage("��½��������!,����ϵ����Ա.");
		}
		
		
		
	}
	

	
	
}
