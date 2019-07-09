package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.playerThread.PlayerLoginMessageSendThread;
import com.ksptooi.FL.security.AdvPasswordHash;


public class PlayerPasswordProcess {

	PlayerData_Interface playerDataBLL=null;
	AdvPasswordHash APH=null;
	Logger logManager = null;
	PlayerData_Interface PDBI=null;
	
	public PlayerPasswordProcess(){
		playerDataBLL=new PlayerDataManager();
		APH = new AdvPasswordHash();
		logManager = new Logger();
		PDBI = new PlayerDataManager();
	}
	
	
	/**
	 * �����ҵ������Ƿ���Ϲ���(�����Ϲ��򽫻���ʾ���)
	 * 
	 * @param Passwd Ҫ��������
	 */
	public boolean passWordLengthIsAccess(Player pl,String Passwd){
		
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
	 * @param PlayerEntity ���ʵ��
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
		
		
		PlayerEntity PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		
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
		
		
		new Thread(new PlayerLoginMessageSendThread(playerEntity)).start();
		
		/** �������� - ���� **/
	}
	
	
	
	/**
	 * ������ҵ����� - ��������
	 */
	
	public void ChangePasswdMD5(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		PlayerEntity PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
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
		
		
		new Thread(new PlayerLoginMessageSendThread(playerEntity)).start();
		
		/** �������� - ���� **/
	}
	
	
	
	
	
	
	
	
	/**�����ж�����ṩ�������Ƿ���ȷ**/
	public boolean isRightPassword(PlayerEntity PDE,String password){
		
		
		String Hash = ConfigManager.getConfig().getEnable_passwordHash();
		
		
		Boolean isSupportOldpwd = ConfigManager.getConfig().isEnable_SupportOldPassword();
		
		String SaltPassword = password;
		
		
		
		//ʹ��MD5
		if(Hash.equalsIgnoreCase("MD5")){
			
			logManager.DM("ʹ��MD5����");
			
			
			
			if(APH.autoCompression(SaltPassword).equals(PDE.getPassword())){
				logManager.DM("������ȷ");
				return true;
			}
			
			
			
			
			//�ж��Ƿ�֧�־�����
			if(! isSupportOldpwd){
				logManager.DM("�������");
				return false;
			}
			
			logManager.DM("֧��ʹ�þ�����");
			
			
			//���þ�����֧��
			if(password.equals(PDE.getPassword())){
				logManager.DM("��������ȷ");			
				this.updatePlayerPassword(PDE,SaltPassword);
				return true;
				
			}
			
			logManager.DM("���������");		
			return false;
		}

		
		
		//û�м����㷨 - false & Other	
		if(SaltPassword.equals(PDE.getPassword())){
			return true;
		}
		
		return false;	
		
		
		
	}
	
	
	/**����������ҵľ�����**/
	public void updatePlayerPassword(PlayerEntity PDE,String Password){
		
		
	
		PDE.setPassword(APH.autoCompression(Password));
		PDBI.updatePlayerData(PDE);
		


	}
	
	
	
}
