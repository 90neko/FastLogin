package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.playerThread.PlayerRegThread;

public class FastCommand_REGISTER implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("register")|cmd.getName().equalsIgnoreCase("reg")){
			
			if(!(sender instanceof Player)){
				FastLogin.getLoggerr().logWarning("������̨����ʹ�ô�������!");
				return;
			}		
			
			Player pl =(Player)sender;
			
			new Thread(new PlayerRegThread(pl,args)).start();
			
		}
		
	}

}
