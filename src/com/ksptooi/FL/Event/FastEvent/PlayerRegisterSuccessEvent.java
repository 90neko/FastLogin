package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.security.AdvPasswordHash;

public class PlayerRegisterSuccessEvent implements LittleEvent{

	Player pl = null;
	
	String passWord = null;
	
	public PlayerRegisterSuccessEvent(Player pe,String passWord) {
		
		this.pl=pe;
		this.passWord=passWord;
		
	}

	@Override
	public void run() {
		
		try {

			
			PlayerDataManager pdm = DataManager.getPlayerDataManager();

			PlayerEffectManager playerEffectManager = FastLogin.getPlayerEffectManager();

			PlayerLocationProcess playerLocationProcess = new PlayerLocationProcess();

			PlayerEntity pe = pdm.getPlayerData(pl);

			AdvPasswordHash passwordHash = DataManager.getAdvPasswordHash();

			pe.setPassword(passwordHash.autoCompression(passWord));
			pe.setRegister(true);
			pe.setLogin(true);

			pdm.updatePlayerData(pe);

			pl.sendMessage(ConfigManager.getLanguage().getRegisterSuccess());

			FUtil.RIC.addIP(pl);

			// Ϊ����������Ч��
			playerEffectManager.addLoginedEffect(pl);
			// �Ƴ���ҵ�ʧ��Ч��
			playerEffectManager.removePreLoginEffect(pl);

			// �Զ����¼��Ϣ
			FastLogin.getLoggerr().ShowMessage(pl);

			// ����Ҵ��͵�Ĭ�ϵ�½λ��
			playerLocationProcess.TelePort_DefaultLoginLocation(pl);

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("ע�ᷢ������!,����ϵ����Ա.");
		}
		
	}
	
	
	
	
	
	
	
	
}
