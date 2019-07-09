package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;

public class FastCommand_SETSPAWN implements FastCommand{

	
	
	
	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
		/**����Ĭ�ϵ�½λ�� - ��ʼ**/
			
		if (!(sender instanceof Player)) {
			FastLogin.getLoggerr().logWarning("������̨����ʹ�ô�������!");
			return;
		}

		Player pl = (Player) sender;

		DefaultLocationEntity dle = ConfigManager.getLocation();

		dle.setLoginLocation(pl.getLocation());

		ConfigManager.updateLocation(dle);

		sender.sendMessage(ConfigManager.getLanguage().getSetSpawnSuccess());

		
		
		
	}

}
