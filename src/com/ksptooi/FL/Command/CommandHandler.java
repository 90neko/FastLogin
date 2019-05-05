package com.ksptooi.FL.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Entity.FastLoginConfigEntity;
import com.ksptooi.FL.Entity.FastLoginLanguageEntity;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.PlayerProcess.PlayerPasswordProcess;
import com.ksptooi.FL.PluginConf.ConfigReader;
import com.ksptooi.FL.PluginConf.ConfigWriter;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.Performance.PerformanceMonitorManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.security.AdvPasswordHash;


public class CommandHandler{

	
	Player pl=null;
	LogManager lm=null;
	PlayerDataBLL_Interface playerDataBLL=null;
	ConfigReader FLCR=null;
	ConfigWriter FLCW=null;
	PlayerPasswordProcess pwdProcess=null;
	AdvPasswordHash APH=null;
	
	public CommandHandler(){
		lm=new LogManager();
		playerDataBLL=new PlayerDataBLLimpl();
		FLCR=new ConfigReader();
		FLCW=new ConfigWriter();
		pwdProcess=new PlayerPasswordProcess();
		APH = new AdvPasswordHash();
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		
		if(sender instanceof Player){			
			pl=(Player)sender;		
		}
		

//		//��¼����
//		if(cmd.getName().equalsIgnoreCase("login")|cmd.getName().equalsIgnoreCase("l")){
//			
//			
//			if(!(sender instanceof Player)){
//				lm.writerWarning("������̨����ʹ�ô�������!");
//				return false;
//			}
//			
//			new Thread(new PlayerLoginThread(pl,args)).start();
//															
//		}
//		
//		//ע������
//		if(cmd.getName().equalsIgnoreCase("register")|cmd.getName().equalsIgnoreCase("reg")){
//			
//			if(!(sender instanceof Player)){
//				lm.writerWarning("������̨����ʹ�ô�������!");
//				return false;
//			}		
//			
//			new Thread(new PlayerRegThread(pl,args)).start();
//			
//		}
		
		if(cmd.getName().equalsIgnoreCase("TC")){
			
			if(sender instanceof Player){
				sender.sendMessage("[FastLogin]����Ҳ���ʹ�ô�����!");
				return false;
			}	
			
			lm.writerInfo("----FastLogin ���ܼ��----");
			
			lm.writerInfo("�ۼ�IO:"+PerformanceMonitorManager.getPFPC());
			
			lm.writerInfo("�ѻ���:"+PerformanceMonitorManager.getPDECCount());
			
			lm.writerInfo("��߳�:"+PerformanceMonitorManager.getPATC());
			
			lm.writerInfo("-------------------------");
			
		}
		
		
		//OP����(Reload=�����ļ�,set=����Ĭ�ϵ�½λ��,go=���͵���½λ��,del=ɾ����½λ��,reset=�����������)
		if(cmd.getName().equalsIgnoreCase("fast")){
			
			
			if(sender instanceof Player){	
				
				if(!(pl.isOp())){
					
					pl.sendMessage("��c[FastLogin]����ҪOP�ſ���ʹ�ô�����");
					return true;
					
				}			
			}
			
				
		try{
				
			/**�����ļ����� - ��ʼ**/
			if(args[0].equalsIgnoreCase("reload")){
						
				FastLoginConfigEntity FLCE = FLCR.readerConfig();
				FastLoginLanguageEntity FLLE = FLCR.readerLanguageConfig();
				DefaultLocationEntity DLE = FLCR.readerLocationConfig();
				
				FUtil.config=FLCE;
				FUtil.language=FLLE;
				FUtil.defaultLocationEntity=DLE;
				
				
				sender.sendMessage("��c[FastLogin]���������������ļ�");										
				return true;
			}
			
			
			/**����Ĭ�ϵ�½λ�� - ��ʼ**/
			if(args[0].equalsIgnoreCase("setSpawn")){
				
				if(!(sender instanceof Player)){
					lm.writerWarning("������̨����ʹ�ô�������!");
					return false;
				}
				
				DefaultLocationEntity DLE = FUtil.defaultLocationEntity;
				
				DLE.setLoginLocation(pl.getLocation());
				
				FLCW.updateLocationConfig(DLE);
				
				FUtil.defaultLocationEntity=DLE;
				
				sender.sendMessage(FUtil.language.getSetSpawnSuccess());		
				return true;
				
			}
			
			
			//���͵���¼λ��
			if(args[0].equalsIgnoreCase("Spawn")){
				
				if(!(sender instanceof Player)){
					lm.writerWarning("������̨����ʹ�ô�������!");
					return false;
				}
				
				
				if(FUtil.defaultLocationEntity.getLocation_world().equalsIgnoreCase("empty")){
					sender.sendMessage("��cû���ҵ�һ����¼λ��,������Ѿ�����,�볢�����ز��.");
					return true;
				}			
				
				sender.sendMessage(FUtil.language.getTPSpawnSuccess());
				pl.teleport(FUtil.defaultLocationEntity.getLoginLocation());
				return true;
			}
			
			
			/**ɾ��Ĭ�ϵ�½λ�� - ��ʼ**/
			if(args[0].equalsIgnoreCase("delSpawn")){
				
				if(!(sender instanceof Player)){
					lm.writerWarning("������̨����ʹ�ô�������!");
					return false;
				}
				
				
				if(FUtil.defaultLocationEntity.getLocation_world().equalsIgnoreCase("empty")){
					sender.sendMessage("��cû���ҵ�һ����¼λ��,������Ѿ�����,�볢�����ز��.");
					return true;
				}	
				
				FUtil.defaultLocationEntity.removeLoc();
				
				FLCW.updateLocationConfig(FUtil.defaultLocationEntity);
				
				
				
				sender.sendMessage(FUtil.language.getDeleteSpawnSuccess());
				return true;
					
			}

			
			
			/**����������� - ��ʼ**/
			if(args[0].equalsIgnoreCase("setPassword")){
				
				if(!(sender.isOp())){
				    sender.sendMessage("ֻ��OP����ʹ�ô�����");
				    return true;
				}
				
				if(args.length<3){
					sender.sendMessage(FUtil.language.getAdminSetPasswordUsage());
					return true;
				}
				
				PlayerDataEntity PDE = playerDataBLL.getPlayerData(args[1]);
				
				if(!PDE.isRegister()){
					sender.sendMessage(FUtil.language.getAdminSetPasswordError1());
					return false;
				}
				
				//OP�޸ĵ�������� ���ܲ���ĳ���Լ��
				PDE.setPassword(APH.autoCompression(args[2]));
				
				playerDataBLL.updatePlayerData(PDE);
				
				sender.sendMessage(FUtil.language.getAdminSetPasswordSuccess());
				
				
				try{
					Bukkit.getPlayer(PDE.getPlayername()).kickPlayer(FUtil.language.getAdminSetPasswordKick());
				}catch(Exception e){	
				}

				
				return true;
				
			}
			
			/**����������� - ����**/
		
			lm.SendOPHelp(sender);

			}catch(Exception e){
				
				lm.SendOPHelp(sender);
				
			}
			
			
		}
		
		//�޸���������
		if(cmd.getName().equalsIgnoreCase("changepassword")||cmd.getName().equalsIgnoreCase("editpwd")){
			
			if(!(sender instanceof Player)){
				lm.writerWarning("������̨����ʹ�ô�������!");
				return false;
			}
			
			
			//�����������ʱ���Ͱ����ĵ�
			if(args.length < 3){
				pl.sendMessage(FUtil.language.getChangePwUsage());
				return true;
			}
			
			
			pwdProcess.ChangePasswd(pl, args[0], args[1], args[2]);
				
		}
		
		
		return false;		
	}
	

	
	
}
