package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;

public class FastCommand_CHANGEPASSWORD implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
			
			
			if(!(sender instanceof Player)){
				FastLogin.getLoggerr().logWarning("������̨����ʹ�ô�������!");
				return;
			}
			
			
			FastPlayer pl =new FastPlayer((Player) sender);
			
			
			//�����������ʱ���Ͱ����ĵ�
			if(args.length < 3){
				pl.sendMessage(ConfigManager.getLanguage().getChangePwUsage());
				return;
			}
						
			
			pl.ChangePasswd(args[0], args[1], args[2]);
			
			pl.save();
		
	}

}
