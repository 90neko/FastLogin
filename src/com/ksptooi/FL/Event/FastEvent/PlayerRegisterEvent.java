package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.FUtil;

public class PlayerRegisterEvent implements LittleEvent{

	Player pl = null;
	String[] args = null;
	
	public PlayerRegisterEvent(Player pl,String[] args) {
		this.pl = pl;
		this.args = args;
	}
	
	
	@Override
	public void run() {
		
		try {

			
			PlayerDataManager playerDataManager = DataManager.getPlayerDataManager();
			PlayerPasswordProcess playerPwdProcess = new PlayerPasswordProcess();

			String Passwd = null;
			String ConfirmPasswd = null;

			// ���ע������Ƿ�Ϸ�
			if (args.length < 2) {
				pl.sendMessage(ConfigManager.getLanguage().getNoConfirmPasswd());
				return;
			}

			Passwd = args[0];
			ConfirmPasswd = args[1];

			// �ж��Ƿ���ע��
			PlayerEntity PDE = playerDataManager.getPlayerData(pl);

			if (PDE.isRegister()) {
				pl.sendMessage(ConfigManager.getLanguage().getRepeatRegister());
				return;
			}

			// �ж�ip�Ƿ��Ѿ��ﵽ�޶�
			if (FUtil.RIC.playerIp_isMaxReg(pl)) {
				pl.sendMessage("ע��ʧ��,ÿ��IP���ֻ��ע��" + ConfigManager.getConfig().getMaxRegisterIP() + "���˺�,���ѳ����޶");
				return;
			}

			// ������볤��
			if (!playerPwdProcess.passWordLengthIsAccess(pl, ConfirmPasswd)) {

				return;
			}

			// ���������ȷ�������Ƿ�һ��
			if (!(Passwd.equals(ConfirmPasswd))) {
				pl.sendMessage(ConfigManager.getLanguage().getConfirmPasswdError());
				return;
			}

			// ע��ɹ� - �����¼�
			PlayerRegisterSuccessEvent prse = new PlayerRegisterSuccessEvent(pl, ConfirmPasswd);
			FastLogin.getEventManager().runEvent(prse);

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("ע�ᷢ������!,����ϵ����Ա.");
		}
		
		
	}
	
	

}
