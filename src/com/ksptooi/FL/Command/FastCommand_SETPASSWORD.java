package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Config.Entity.Language;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.security.AdvPasswordHash;

public class FastCommand_SETPASSWORD implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
	
		PlayerDataManager pdm = DataManager.getPlayerDataManager();
		
		AdvPasswordHash advPasswordHash = DataManager.getAdvPasswordHash();
		
		Language lang = ConfigManager.getLanguage();
		
		
			
			if(!(sender.isOp())){
			    sender.sendMessage("ֻ��OP����ʹ�ô�����");
			    return;
			}
			
			
			if(args.length<3){
				sender.sendMessage(lang.getAdminSetPasswordUsage());
				return;
			}
			
			PlayerEntity pde = pdm.getPlayerData(args[1]);
			
			if(!pde.isRegister()){
				sender.sendMessage(lang.getAdminSetPasswordError1());
				return;
			}
			
			//OP�޸ĵ�������� ���ܲ���ĳ���Լ��
			pde.setPassword(advPasswordHash.autoCompression(args[2]));
			
			pdm.updatePlayerData(pde);
			
			sender.sendMessage(lang.getAdminSetPasswordSuccess());
			
			
			FastLogin.getAsyncProcess().AsyncKickPlayer(FastLogin.getPlayer(pde.getPlayername()), lang.getAdminSetPasswordKick());	

		
	}

}
