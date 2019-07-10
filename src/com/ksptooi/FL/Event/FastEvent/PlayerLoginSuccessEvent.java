package com.ksptooi.FL.Event.FastEvent;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Util.FUtil;

public class PlayerLoginSuccessEvent implements FastEvent{

	FastPlayer pl = null;
	Language lang = null;

	
	public PlayerLoginSuccessEvent(FastPlayer pl) {
		this.lang = ConfigManager.getLanguage();
		this.pl=pl;
		
	}
	
	@Override
	public void run() {
		
		
		pl.setLogin(true);
		pl.save();
		
		pl.sendMessage(ConfigManager.getLanguage().getLoginSuccess());
		
		
		//�����ҵ�¼ǰ��OP �������OPȨ��
		FUtil.LS.loginSuccess_OpSecurityProcess(pl);
		
		//�����ҵ�¼ǰ�Ǵ���ģʽ ������Ҵ���
		FUtil.LS.loginSuccess_CreativeSecurityProcess(pl);
		
		// �Զ����¼��Ϣ
		FastLogin.getLoggerr().ShowMessage(pl);
		
		//Ϊ����������Ч��
		pl.addLoginedEffect();
		
		//�Ƴ���ҵ�ʧ��Ч��
		pl.removePreLoginEffect();
		
		//���isEnable_LoginSecurityΪTrue ����Ҵ��ͻ�������ߵĵط�
		if(ConfigManager.getConfig().isEnable_LoginSecurity()==true){
			pl.tpLastQuitLocation();
		}
		
		
	}
	
	
	
	
}
