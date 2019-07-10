package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Thread.Player.PlayerLoginMessageSendThread;
import com.ksptooi.FL.Util.Logger;


public class PlayerPasswordProcess {

	PlayerData_Interface playerDataBLL=null;
	PasswordHash APH=null;
	Logger logManager = null;
	PlayerData_Interface PDBI=null;
	
	public PlayerPasswordProcess(){
		playerDataBLL=new PlayerDataManager();
		APH = new PasswordHash();
		logManager = new Logger();
		PDBI = new PlayerDataManager();
	}
	
	
	/**
	 * �����ҵ������Ƿ���Ϲ���(�����Ϲ��򽫻���ʾ���)
	 * 
	 * @param Passwd Ҫ��������
	 */
	public boolean passWordLengthIsAccess(FastPlayer pl,String Passwd){
		
		if(Passwd.length()>ConfigManager.getConfig().getPasswordMaxLength()){
			pl.sendMessage(ConfigManager.getLanguage().getPasswdTooLong());
			return false;
		}
		
		if(Passwd.length()<ConfigManager.getConfig().getPasswordMinLength()){
			pl.sendMessage(ConfigManager.getLanguage().getPasswdTooShost());
			return false;
		}
		
		return true;
		
	}
	
	
	/**
	 * ������ҵ�����
	 * 
	 * @param PlayerData ���ʵ��
	 * @param OldPasswd ԭ����
	 * @param NewPasswd ������
	 * @param ConfirmNewPasswd ȷ��������
	 */
	public void ChangePasswd(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		//�ж����޼���
		if(ConfigManager.getConfig().getEnable_passwordHash().equalsIgnoreCase("md5")){
			this.ChangePasswdMD5(playerEntity, OldPasswd, NewPasswd, ConfirmNewPasswd);
			return;
		}	
		
		
		PlayerData PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		
		//�ж�����ľ������Ƿ���ȷ
		if( ! PDE.getPassword().equals(OldPasswd)){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_OldpwErr());
			return;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_ConfirmPwError());
			return;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(ConfigManager.getLanguage().getReModifyPasswd());
			return;
		}
		
		/** �������� - ��ʼ **/
		
		PDE.setPassword(NewPasswd);
		PDE.setLogin(false);
		
		playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		playerEntity.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//ͬ�������ݿ�
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(new FastPlayer(playerEntity))).start();
		
		/** �������� - ���� **/
	}
	
	
	
	/**
	 * ������ҵ����� - ��������
	 */
	
	public void ChangePasswdMD5(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		PlayerData PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		//�ж�����ľ������Ƿ���ȷ
		if( ! PDE.getPassword().equals(APH.autoCompression(OldPasswd))){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_OldpwErr());
			return;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_ConfirmPwError());
			return;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(ConfigManager.getLanguage().getReModifyPasswd());
			return;
		}
		
		/** �������� - ��ʼ **/
		
		PDE.setPassword(APH.autoCompression(NewPasswd));
		PDE.setLogin(false);
		
		playerEntity.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		playerEntity.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//ͬ�������ݿ�
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(new FastPlayer(playerEntity))).start();
		
		/** �������� - ���� **/
	}
	
	
	
	
	
	
	
}
