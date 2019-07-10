package com.ksptooi.FL.Event.FastEvent;

import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Player.Check.PlayerPasswordRuleCheck;
import com.ksptooi.FL.Util.FUtil;

public class PlayerRegisterEvent implements FastEvent{

	FastPlayer pl = null;
	String[] args = null;
	
	public PlayerRegisterEvent(Player pl,String[] args) {
		this.pl = new FastPlayer(pl);
		this.args = args;
	}
	
	
	@Override
	public void run() {
		
		try {

			PlayerPasswordRuleCheck playerPwdProcess = new PlayerPasswordRuleCheck();

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
			pl.reload();

			if (pl.isRegister()) {
				pl.sendMessage(ConfigManager.getLanguage().getRepeatRegister());
				return;
			}

			// �ж�ip�Ƿ��Ѿ��ﵽ�޶�
			if (FUtil.RIC.playerIp_isMaxReg(pl)) {
				pl.sendMessage("ע��ʧ��,ÿ��IP���ֻ��ע��" + ConfigManager.getConfig().getMaxRegisterIP() + "���˺�,���ѳ����޶");
				return;
			}

			// ������볤��
			if (!playerPwdProcess.pwdIsValid(pl, ConfirmPasswd)) {

				return;
			}

			// ���������ȷ�������Ƿ�һ��
			if (!(Passwd.equals(ConfirmPasswd))) {
				pl.sendMessage(ConfigManager.getLanguage().getConfirmPasswdError());
				return;
			}

			// ע��ɹ� - �����¼�
			PlayerRegisterSuccessEvent prse = new PlayerRegisterSuccessEvent(pl, ConfirmPasswd);
			FastLogin.getEventManager().runFastEvent(prse);

			
		} catch (Exception e) {
			e.printStackTrace();
			pl.sendMessage("ע�ᷢ������!,����ϵ����Ա.");
		}
		
		
	}
	
	

}
