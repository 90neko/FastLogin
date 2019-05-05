package us.ktmc.main;

import java.io.IOException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.Exception.PlayerNotRegisterException;
import us.ktmc.player.PlayerLoginThread;
import us.ktmc.player.PlayerRegThread;
import us.ktmc.util.Var;
import us.ktmc.util.Language;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;

public class CommandHandler{

	FastUtil Util=new GeneralUtil();
	Player pl=null;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
			
		
		if(sender instanceof Player){
			
			pl=(Player)sender;
			
		}

		/**��ҵ�½(!ConsoleExecute)**/
		if(cmd.getName().equalsIgnoreCase("login")|cmd.getName().equalsIgnoreCase("l")){
			
			
			if(Util.PlayerCommandExecuteAtConsole(sender))
				return false;
			
			
			new Thread(new PlayerLoginThread(pl,args)).start();
															
		}
		
		/**���ע�� - ��ʼ**/
		if(cmd.getName().equalsIgnoreCase("register")|cmd.getName().equalsIgnoreCase("reg")){
			
			if(Util.PlayerCommandExecuteAtConsole(sender))
				return false;			
			
			new Thread(new PlayerRegThread(pl,args)).start();
			
			
		}
		/**���ע�� - ����**/
		
		
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
						
				Var.conf.ConfigCheck();
				Var.conf.ReaderConfig(); 			
				Var.conf.LanguageCheck();
				Var.conf.IPtablesCheck();
				try{
					
					Var.conf.ReaderLanuage();
					
				}catch(NullPointerException ec){
					
					ec.printStackTrace();
					System.out.println("[FastLogin]����ȡ�����ļ�ʱ���ִ���,��ʹ��Ԥ������");
					
				}
				
				sender.sendMessage("��c[FastLogin]���������������ļ�");										
				return true;
			}
			/**�����ļ����� - ����**/
			
			
			/**����Ĭ�ϵ�½λ�� - ��ʼ**/
			if(args[0].equalsIgnoreCase("set")){
				
				if(Util.PlayerCommandExecuteAtConsole(sender)){
					return true;
				}
				
				Util.SetDefaultLoginLocation(pl.getLocation().getWorld().getName(),(int)pl.getLocation().getX(), (int)pl.getLocation().getY(), (int)pl.getLocation().getZ(),(int)pl.getLocation().getPitch(),(int)pl.getLocation().getYaw());
				
				Var.conf.ConfigCheck();
				Var.conf.ReaderConfig(); 	
				sender.sendMessage("��e[FastLogin]����¼λ�������ã�");		
				return true;
				
			}
			/**����Ĭ�ϵ�½λ�� - ����**/
			
			
			/**T.P - Start**/
			if(args[0].equalsIgnoreCase("go")){
				
				if(Util.PlayerCommandExecuteAtConsole(sender))			
					return false;
				
				
				if(Var.Location_world.equalsIgnoreCase("empty")){
					sender.sendMessage("��cû���ҵ�һ����¼λ��,������Ѿ�����,�볢�����ز��.");
					return true;
				}			
				
				pl.teleport(Var.LoginLocation);
				return true;
			}
			/**T.P - End**/
			
			/**ɾ��Ĭ�ϵ�½λ�� - ��ʼ**/
			if(args[0].equalsIgnoreCase("del")){
				
				if(Util.PlayerCommandExecuteAtConsole(sender))			
					return false;
				
				if(Var.Location_world.equalsIgnoreCase("empty")){
					sender.sendMessage("��cû���ҵ�һ����¼λ��,������Ѿ�����,�볢�����ز��.");
					return false;
				}		
				
				if(Util.DeleteDefaultLoginLocation()){
					
					Var.conf.ConfigCheck();
					Var.conf.ReaderConfig(); 	
					sender.sendMessage("��c[FastLogin]����¼λ����ɾ����");
					return true;
				}
				
				sender.sendMessage(Util.getFileErrorText());
				return false;
					
			}
			/**ɾ��Ĭ�ϵ�½λ�� - ����**/
			
			
			/**����������� - ��ʼ**/
			if(args[0].equalsIgnoreCase("reset")){
				
				if(!(sender.isOp())){
				    sender.sendMessage("ֻ��OP����ʹ�ô�����");
				    return true;
				}
				
				if(args.length<3){
					sender.sendMessage("ʹ�÷���:/Fast Reset ����� ������");
					return true;
				}
				
				
				try{
				
					Util.ReSetPasswd(args[1], args[2]);
					
				}catch(PlayerNotRegisterException e){
				
					sender.sendMessage("��c[FastLogin]�������δע��,�޸�����ʧ�ܣ�");
					return false;
					
				}catch(PasswdRulesException e){
					
					if(e.isTooLong()){
						sender.sendMessage(Language.PassMax);
					}
					
					if(e.isTooShort()){
						sender.sendMessage(Language.PassMin);
					}
					
					return false;
					
				}catch(IOException e){
					
					e.printStackTrace();
					sender.sendMessage(Util.getFileErrorText());
					return false;
					
				}

				
				sender.sendMessage("��6�����޸ĳɹ���");
				return true;
				
			}
			
			/**����������� - ����**/
		
			
			Util.SendOPHelp(sender);

			}catch(Exception e){
				
				Util.SendOPHelp(sender);
				
			}
			
			
		}
		
		//�޸���������
		if(cmd.getName().equalsIgnoreCase("ModifyPasswd")){
			
			if(Util.PlayerCommandExecuteAtConsole(sender)){
				return false;
			}
			
			//�����������ʱ���Ͱ����ĵ�
			if(args.length < 3){
				pl.sendMessage(Language.ModifyPwUsage);
				return true;
			}
			
			Util.ChangePasswd(pl, args[0], args[1], args[2]);
		
				
		}
		//�޸����� - ����
		
		
		return false;		
	}
	

	
	
}
