package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;

public class FastCommand_SPAWN implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {

		//���͵���¼λ��
			
		if (!(sender instanceof Player)) {
			FastLogin.getLoggerr().logWarning("������̨����ʹ�ô�������!");
			return;
		}

		Player pl=(Player)sender;
		
		if (ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")) {
			sender.sendMessage("��cû���ҵ�һ����¼λ��,������Ѿ�����,�볢�����ز��.");
			return;
		}

		sender.sendMessage(ConfigManager.getLanguage().getTPSpawnSuccess());
		
		pl.teleport(ConfigManager.getLocation().getLoginLocation());
		
		
	}

}
