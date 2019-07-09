package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;

public class FastCommand_DELSPAWN implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
			
		if (!(sender instanceof Player)) {
			FastLogin.getLoggerr().logWarning("������̨����ʹ�ô�������!");
			return;
		}
		
		
		if (ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")) {
			sender.sendMessage("��cû���ҵ�һ����¼λ��,������Ѿ�����,�볢�����ز��.");
			return;
		}
		
		
		DefaultLocationEntity location = ConfigManager.getLocation();
		
		location.removeLoc();
		
		ConfigManager.updateLocation(location);


		sender.sendMessage(ConfigManager.getLanguage().getDeleteSpawnSuccess());
		
	}

}
