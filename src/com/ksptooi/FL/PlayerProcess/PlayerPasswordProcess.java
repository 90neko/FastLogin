package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.FL.playerThread.PlayerLoginMessageSendThread;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.security.AdvPasswordHash;


public class PlayerPasswordProcess {

	PlayerDataBLL_Interface playerDataBLL=null;
	AdvPasswordHash APH=null;
	LogManager logManager = null;
	PlayerDataBLL_Interface PDBI=null;
	
	public PlayerPasswordProcess(){
		playerDataBLL=new PlayerDataBLLimpl();
		APH = new AdvPasswordHash();
		logManager = new LogManager();
		PDBI = new PlayerDataBLLimpl();
	}
	
	
	/**
	 * �����ҵ������Ƿ���Ϲ���(�����Ϲ��򽫻���ʾ���)
	 * 
	 * @param Passwd Ҫ��������
	 */
	public boolean passWordLengthIsAccess(Player pl,String Passwd){
		
		if(Passwd.length()>FUtil.config.getPasswordMaxLength()){
			pl.sendMessage(FUtil.language.getPasswdTooLong());
			return false;
		}
		
		if(Passwd.length()<FUtil.config.getPasswordMinLength()){
			pl.sendMessage(FUtil.language.getPasswdTooShost());
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
		if(FUtil.config.getEnable_passwordHash().equalsIgnoreCase("md5")){
			this.ChangePasswdMD5(playerEntity, OldPasswd, NewPasswd, ConfirmNewPasswd);
			return;
		}	
		
		
		PlayerDataEntity PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		
		//�ж�����ľ������Ƿ���ȷ
		if( ! PDE.getPassword().equals(OldPasswd)){
			playerEntity.sendMessage(FUtil.language.getChangePw_OldpwErr());
			return;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(FUtil.language.getChangePw_ConfirmPwError());
			return;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(FUtil.language.getReModifyPasswd());
			return;
		}
		
		/** �������� - ��ʼ **/
		
		PDE.setPassword(NewPasswd);
		PDE.setLogin(false);
		
		playerEntity.sendMessage(FUtil.language.getChangePw_Success());
		playerEntity.sendMessage(FUtil.language.getLoginOut());
		
		//ͬ�������ݿ�
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(playerEntity)).start();
		
		/** �������� - ���� **/
	}
	
	
	
	/**
	 * ������ҵ����� - ��������
	 */
	
	public void ChangePasswdMD5(Player playerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		
		PlayerDataEntity PDE = playerDataBLL.getPlayerData(playerEntity);
		
		
		//�ж�����ľ������Ƿ���ȷ
		if( ! PDE.getPassword().equals(APH.autoCompression(OldPasswd))){
			playerEntity.sendMessage(FUtil.language.getChangePw_OldpwErr());
			return;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			playerEntity.sendMessage(FUtil.language.getChangePw_ConfirmPwError());
			return;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			playerEntity.sendMessage(FUtil.language.getReModifyPasswd());
			return;
		}
		
		/** �������� - ��ʼ **/
		
		PDE.setPassword(APH.autoCompression(NewPasswd));
		PDE.setLogin(false);
		
		playerEntity.sendMessage(FUtil.language.getChangePw_Success());
		playerEntity.sendMessage(FUtil.language.getLoginOut());
		
		//ͬ�������ݿ�
		playerDataBLL.updatePlayerData(PDE);
		
		
		new Thread(new PlayerLoginMessageSendThread(playerEntity)).start();
		
		/** �������� - ���� **/
	}
	
	
	
	
	
	
	
	
	/**�����ж�����ṩ�������Ƿ���ȷ**/
	public boolean isRightPassword(PlayerDataEntity PDE,String password){
		
		
		String Hash = FUtil.config.getEnable_passwordHash();
		
		
		Boolean isSupportOldpwd = FUtil.config.isEnable_SupportOldPassword();
		
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
	public void updatePlayerPassword(PlayerDataEntity PDE,String Password){
		
		
	
		PDE.setPassword(APH.autoCompression(Password));
		PDBI.updatePlayerData(PDE);
		


	}
	
	
	
}
