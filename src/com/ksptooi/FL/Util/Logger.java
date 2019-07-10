package com.ksptooi.FL.Util;

import org.bukkit.command.CommandSender;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;


public class Logger{
	
	public void logInfo(String info){
		System.out.println("[FastLogin]:"+info);
	}
	
	public void logWarning(String warning){
		System.out.println("[FastLogin]����:"+warning);
	}
	
	public void logError(String error){
		System.out.println("[FastLogin]����:"+error);
	}
	
	
	/**
	 * ���ڻ�ȡ��Ҽ�����Ϣ
	 */
	public String GenJoinedMessage(FastPlayer pl) {

		String str=ConfigManager.getConfig().getPlayerJoinedMessage();
		
		//������Ϣ
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "��");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}


	/**
	 * ���ڻ�ȡ����˳���Ϣ
	 */
	public String GenQuitMessage(FastPlayer pl) {

		String str=ConfigManager.getConfig().getPlayerQuitMessage();
		
		//������Ϣ
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "��");
		
		str=str.replace("#", "\n");
		
		return str;
	}
	
	
	/**
	 * ����һ��OP�������������̨
	 * @param ����̨ʵ��
	 */
	public void SendOPHelp(CommandSender sender){
		
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp1());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp2());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp3());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp4());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp5());
		sender.sendMessage(ConfigManager.getLanguage().getAdminCommandHelp6());
		
	}
	
	/**
	 * ������ҵ�¼�����Ϣ����
	 */
	public void ShowMessage(FastPlayer pl) {
		
		
		String ShowMessage="";
		
		//�ж��Ƿ������˵�¼��Ϣ
		if(ConfigManager.getConfig().getPlayerLoginedMessage().equalsIgnoreCase("false")){
			return;
		}
		
		//���ɵ�¼��Ϣ
		ShowMessage=ConfigManager.getConfig().getPlayerLoginedMessage();
		
		ShowMessage=ShowMessage.replace("%Player%", pl.getName());
		
		ShowMessage=ShowMessage.replace("&", "��");
		
		ShowMessage=ShowMessage.replace("#", "\n");
		
		
		//���͵�¼��Ϣ
		pl.sendMessage(ShowMessage);
		
	}
	
	/**
	 * �������������Ϣ
	 */
	public void DM(String Message){
		debugMessage(Message);
	}
	
	public void debugMessage(String Message){
		
		if(! ConfigManager.getConfig().isEnable_DebugPrint()){
			return;
		}
		
		System.out.println("[FastLogin]������:"+Message);
			
	}
	
}
