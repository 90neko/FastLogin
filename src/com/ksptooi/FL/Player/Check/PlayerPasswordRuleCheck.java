package com.ksptooi.FL.Player.Check;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;


public class PlayerPasswordRuleCheck {


	private PasswordHash passwordHash = null;
	private Language lang = null;
	
	public PlayerPasswordRuleCheck() {
		
		this.passwordHash = DataManager.getAdvPasswordHash();
		
		this.lang = ConfigManager.getLanguage();
		
	}
	
	
	
	/**
	 * �����ҵ������Ƿ���Ϲ���(�����Ϲ��򽫻���ʾ���)
	 * 
	 * @param Passwd Ҫ��������
	 */
	public boolean pwdIsValid(FastPlayer pl,String Passwd){
		
		if(Passwd.length()>ConfigManager.getConfig().getPasswordMaxLength()){
			pl.sendMessage(lang.getPasswdTooLong());
			return false;
		}
		
		if(Passwd.length()<ConfigManager.getConfig().getPasswordMinLength()){
			pl.sendMessage(lang.getPasswdTooShost());
			return false;
		}
		
		return true;
		
	}
	
	
	//�����ҵĸ������������Ƿ���Ч(�����Զ���ʾ���)
	public boolean isValid(FastPlayer pl,String OldPasswd,String NewPasswd,String ConfirmNewPasswd) {
		
		
		//���볤�ȼ��
		
		if( ! this.pwdIsValid(pl, NewPasswd)) {
			return false;
		}	
		
		
		//�ж�����ľ������Ƿ���ȷ
		if( ! pl.getPassword().equals(OldPasswd)){
			pl.sendMessage(lang.getChangePw_OldpwErr());
			return false;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			pl.sendMessage(lang.getChangePw_ConfirmPwError());
			return false;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			pl.sendMessage(lang.getReModifyPasswd());
			return false;
		}
		
		
		return true;
		
	}
	
	
	//�����ҵĸ������������Ƿ���Ч(�����Զ���ʾ���) ֧��MD5
	public boolean isValidMD5(FastPlayer pl,String OldPasswd,String NewPasswd,String ConfirmNewPasswd) {
		
		
		//���볤�ȼ��
		
		if( ! this.pwdIsValid(pl, NewPasswd)) {
			return false;
		}	
		
		
		//�ж�����ľ������Ƿ���ȷ
		if( ! pl.getPassword().equals(passwordHash.autoCompression(OldPasswd))){
			pl.sendMessage(lang.getChangePw_OldpwErr());
			return false;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			pl.sendMessage(lang.getChangePw_ConfirmPwError());
			return false;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			pl.sendMessage(lang.getReModifyPasswd());
			return false;
		}
		
		
		return true;
		
	}
	
	
}
