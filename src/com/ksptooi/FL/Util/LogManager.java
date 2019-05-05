package com.ksptooi.FL.Util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class LogManager{
	
	public void writerInfo(String info){
		System.out.println("[FastLogin]:"+info);
	}
	
	public void writerWarning(String warning){
		System.out.println("[FastLogin]����:"+warning);
	}
	
	public void writerError(String error){
		System.out.println("[FastLogin]����:"+error);
	}
	
	
	/**
	 * ���ڻ�ȡ��Ҽ�����Ϣ
	 */
	public String GenJoinedMessage(Player pl) {

		String str=FUtil.config.getPlayerJoinedMessage();
		
		//������Ϣ
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "��");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}


	/**
	 * ���ڻ�ȡ����˳���Ϣ
	 */
	public String GenQuitMessage(Player pl) {

		String str=FUtil.config.getPlayerQuitMessage();
		
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
		
		sender.sendMessage(FUtil.language.getAdminCommandHelp1());
		sender.sendMessage(FUtil.language.getAdminCommandHelp2());
		sender.sendMessage(FUtil.language.getAdminCommandHelp3());
		sender.sendMessage(FUtil.language.getAdminCommandHelp4());
		sender.sendMessage(FUtil.language.getAdminCommandHelp5());
		sender.sendMessage(FUtil.language.getAdminCommandHelp6());
		
	}
	
	/**
	 * ������ҵ�¼�����Ϣ����
	 */
	public void ShowMessage(Player pl) {
		
		
		String ShowMessage="";
		
		//�ж��Ƿ������˵�¼��Ϣ
		if(FUtil.config.getPlayerLoginedMessage().equalsIgnoreCase("false")){
			return;
		}
		
		//���ɵ�¼��Ϣ
		ShowMessage=FUtil.config.getPlayerLoginedMessage();
		
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
		
		if(! FUtil.config.isEnable_DebugPrint()){
			return;
		}
		
		System.out.println("[FastLogin]������:"+Message);
			
	}
	
}
