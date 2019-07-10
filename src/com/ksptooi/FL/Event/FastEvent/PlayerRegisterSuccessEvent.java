package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Util.FUtil;

public class PlayerRegisterSuccessEvent implements FastEvent{

	FastPlayer pl = null;
	
	String passWord = null;
	
	public PlayerRegisterSuccessEvent(FastPlayer pe,String passWord) {
		
		this.pl=pe;
		this.passWord=passWord;
		
	}

	@Override
	public void run() {
		
		try {
		


			pl.reload();

			PasswordHash passwordHash = DataManager.getAdvPasswordHash();

			pl.setPassword(passwordHash.autoCompression(passWord));
			pl.setRegister(true);
			pl.setLogin(true);

			pl.save();

			pl.sendMessage(ConfigManager.getLanguage().getRegisterSuccess());

			FUtil.RIC.addIP(pl);

			// Ϊ����������Ч��
			pl.addLoginedEffect();
			// �Ƴ���ҵ�ʧ��Ч��
			pl.removePreLoginEffect();

			// �Զ����¼��Ϣ
			FastLogin.getLoggerr().ShowMessage(pl);

			// ����Ҵ��͵�Ĭ�ϵ�½λ��
			

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("ע�ᷢ������!,����ϵ����Ա.");
		}
		
	}
	
	
	
	
	
	
	
	
}
