package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Util.FUtil;

public class PlayerLoginSuccessEvent implements LittleEvent{

	PlayerEntity pe=null;
	Player pl = null;
	Language lang = null;

	
	public PlayerLoginSuccessEvent(PlayerEntity pe,Player pl) {
		this.pe=pe;
		this.lang = ConfigManager.getLanguage();
		this.pl=pl;
		
	}
	
	@Override
	public void run() {
		
		PlayerEffectManager playerEffectManager = FastLogin.getPlayerEffectManager();
		PlayerDataManager pdm = DataManager.getPlayerDataManager();
		
		pe.setLogin(true);
		pdm.updatePlayerData(pe);
		
		pl.sendMessage(ConfigManager.getLanguage().getLoginSuccess());
		
		
		//�����ҵ�¼ǰ��OP �������OPȨ��
		FUtil.LS.loginSuccess_OpSecurityProcess(pl);
		
		//�����ҵ�¼ǰ�Ǵ���ģʽ ������Ҵ���
		FUtil.LS.loginSuccess_CreativeSecurityProcess(pl);
		
		// �Զ����¼��Ϣ
		FastLogin.getLoggerr().ShowMessage(pl);
		
		//Ϊ����������Ч��
		playerEffectManager.addLoginedEffect(pl);
		
		//�Ƴ���ҵ�ʧ��Ч��
		playerEffectManager.removePreLoginEffect(pl);
		
		//���isEnable_LoginSecurityΪTrue ����Ҵ��ͻ�������ߵĵط�
		if(ConfigManager.getConfig().isEnable_LoginSecurity()==true){
			pl.teleport(pe.getLastQuitLocation());
		}
		
		
	}
	
	
	
	
}
