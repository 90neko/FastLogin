package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigReader;
import com.ksptooi.FL.Data.Config.ConfigWriter;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.security.AdvPasswordHash;


public class BukkitCommandHandler{

	
	Player pl=null;
	Logger lm=null;
	PlayerData_Interface playerDataBLL=null;
	ConfigReader FLCR=null;
	ConfigWriter FLCW=null;
	PlayerPasswordProcess pwdProcess=null;
	AdvPasswordHash APH=null;
	
	public BukkitCommandHandler(){
		lm=new Logger();
		playerDataBLL=new PlayerDataManager();
		FLCR=new ConfigReader();
		FLCW=new ConfigWriter();
		pwdProcess=new PlayerPasswordProcess();
		APH = new AdvPasswordHash();
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		
		if(sender instanceof Player){			
			pl=(Player)sender;		
		}
		


		
		//ע������

		
		
		
		//OP����(Reload=�����ļ�,set=����Ĭ�ϵ�½λ��,go=���͵���½λ��,del=ɾ����½λ��,reset=�����������)
		if(cmd.getName().equalsIgnoreCase("fast")){
			
			
			
			boolean flag = FastLogin.getCommandManager().execute(args[0], sender, cmd, args);
			
			if( !flag) {
				sender.sendMessage("�������");
			}
			

//			
//			/**ɾ��Ĭ�ϵ�½λ�� - ��ʼ**/

//
//			
//			
//			/**����������� - ��ʼ**/
//			if(args[0].equalsIgnoreCase("setPassword")){
//				
//				if(!(sender.isOp())){
//				    sender.sendMessage("ֻ��OP����ʹ�ô�����");
//				    return true;
//				}
//				
//				if(args.length<3){
//					sender.sendMessage(FUtil.language.getAdminSetPasswordUsage());
//					return true;
//				}
//				
//				PlayerEntity PDE = playerDataBLL.getPlayerData(args[1]);
//				
//				if(!PDE.isRegister()){
//					sender.sendMessage(FUtil.language.getAdminSetPasswordError1());
//					return false;
//				}
//				
//				//OP�޸ĵ�������� ���ܲ���ĳ���Լ��
//				PDE.setPassword(APH.autoCompression(args[2]));
//				
//				playerDataBLL.updatePlayerData(PDE);
//				
//				sender.sendMessage(FUtil.language.getAdminSetPasswordSuccess());
//				
//				
//				try{
//					Bukkit.getPlayer(PDE.getPlayername()).kickPlayer(FUtil.language.getAdminSetPasswordKick());
//				}catch(Exception e){	
//				}
//
//				
//				return true;
//				
//			}
//			
//			/**����������� - ����**/
//		
//			lm.SendOPHelp(sender);
//
//			}catch(Exception e){
//				
//				lm.SendOPHelp(sender);
//				
//			}
			
			
		}
		
		
		return false;		
	}
	

	
	
}
