package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.ksptooi.FL.Data.Config.ConfigManager;

public class FastCommand_RELOAD implements FastCommand{


	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
		/**�����ļ����� - ��ʼ**/	
		ConfigManager.readConfig();
		ConfigManager.readLanguage();
		ConfigManager.readLocation();

		sender.sendMessage("��c[FastLogin]���������������ļ�");								
		
		
	}
	
	
	

}
