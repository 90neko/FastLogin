package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.playerThread.PlayerLoginThread;

public class FastCommand_LOGIN implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
			
			if(!(sender instanceof Player)){
				FastLogin.getLoggerr().logWarning("������̨����ʹ�ô�������!");
				return ;
			}
			
			Player pl=(Player) sender;
			
			new Thread(new PlayerLoginThread(pl,args)).start();
			
	}
	
	
	

}
