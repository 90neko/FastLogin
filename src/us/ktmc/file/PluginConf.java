package us.ktmc.file;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;
import us.ktmc.util.Language;



public class PluginConf {

	FastUtil Util=new GeneralUtil();
	
	public void IPtablesCheck(){
		
		if(!(Var.IO.CheckFile("plugins/ksptooi/fastlogin/", "iptables.gd"))){
			
			System.out.println("[FastLogin]������IPtables");
			try {
				
				Var.IO.OutputToFile(Var.IPtables, "Iptables:", "UTF-8");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	public void ConfigCheck(){
		
		if(!FastUtil.Mconf.exists()){
			
			System.out.println("[FastLogin]��д��Ĭ��������");
					
			Var.V3.setTargetFile(FastUtil.Mconf);
			
			
			try {
				
				new File("plugins/ksptooi/fastlogin/").mkdirs();
				
				FastUtil.Mconf.createNewFile();
				
				Var.V3.OutputToFile(FastUtil.Mconf, "version=" + FastUtil.FastLoginVersion, "UTF-8");
				Var.V3.AddtoFile("\r\nplayerdatatype=generaldata");
				Var.V3.AddtoFile("\r\nlogintimeout=60");
				Var.V3.AddtoFile("\r\npasswordlenmaxlength=15");
				Var.V3.AddtoFile("\r\npasswordlenminlength=5");
				Var.V3.AddtoFile("\r\nupdatatomysql=false");
				Var.V3.AddtoFile("\r\nminnamelength=3");
				Var.V3.AddtoFile("\r\nlocation.world=empty");
				Var.V3.AddtoFile("\r\nlocation.x=empty");
				Var.V3.AddtoFile("\r\nlocation.y=empty");
				Var.V3.AddtoFile("\r\nlocation.z=empty");
				Var.V3.AddtoFile("\r\nlocation.yaw=empty");
				Var.V3.AddtoFile("\r\nlocation.pitch=empty");
				Var.V3.AddtoFile("\r\nmessageinterval=5");
				Var.V3.AddtoFile("\r\nnodamagetime=0");
				Var.V3.AddtoFile("\r\nlocationprotection=false");
				Var.V3.AddtoFile("\r\nnamestrictmode=true");
				Var.V3.AddtoFile("\r\nbanname=*;");
				Var.V3.AddtoFile("\r\nipmaxreg=3");
				Var.V3.AddtoFile("\r\nregex=*");
				Var.V3.AddtoFile("\r\nplayerloginedmessage=false");
				Var.V3.AddtoFile("\r\nplayerjoinedmessage=&e%Player% Joined the game.");
				

			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println();
				
			}
			
			
		}
	
	}
	

	public void LanguageCheck(){
		
		if(!FastUtil.LanguageFile.exists()){
			
			
			System.out.println("[FastLogin}�����������ļ�");

			Var.V3.setTargetFile(FastUtil.LanguageFile);
					    		    	
			try{
				
				FastUtil.LanguageFile.createNewFile();
				
				Var.V3.OutputToFile(FastUtil.LanguageFile, "notlogin=��e[fastlogin]��b����Ҫ��¼����ܲ���,ʹ��/login ���� ����¼", "UTF-8");
		    	
		    	Var.V3.AddtoFile( "\r\nnotregister=��e[fastlogin]��b����Ҫע�����ܲ���,ʹ��/register ���� ȷ������ ��ע��");
		    	
		    	Var.V3.AddtoFile( "\r\nlogintimeoutkick=��¼��ʱ");
		    	
		    	Var.V3.AddtoFile( "\r\nrepeatlogin=��e[fastlogin]��c���Ѿ���¼��!");
		    	
		    	Var.V3.AddtoFile( "\r\nrepeatregister=��e[fastlogin]��c���Ѿ�ע����!");
		    	
		    	Var.V3.AddtoFile( "\r\nnotregister2=��e[fastlogin]��c�㻹û��ע�ᣡ");
		    	
		    	Var.V3.AddtoFile( "\r\nloginsuccessful=��e[fastlogin]��a��¼�ɹ���");
		    	
		    	Var.V3.AddtoFile( "\r\npassworderror=��e[fastlogin]��c�������");
		    	
		    	Var.V3.AddtoFile( "\r\nregistersuccessful=��e[fastlogin]��aע����ɣ�");
		    	
		    	Var.V3.AddtoFile( "\r\nnullpassword=��e[fastlogin]��c���������룡");
		    	
		    	Var.V3.AddtoFile( "\r\npasswdmax=��e[fastlogin]��c���볤�ȳ���������ƣ�");
		    	
		    	Var.V3.AddtoFile( "\r\npasswdmin=��e[fastlogin]��c����̫�̣�");
		    	
		    	Var.V3.AddtoFile( "\r\nconfirmpasswd=��e[fastlogin]��c������ȷ������ ��:/register 12345 12345");
		    	
		    	Var.V3.AddtoFile( "\r\nconfirmpasswderror=��e[fastlogin]��c������������벻һ��");   
				
				Var.V3.AddtoFile( "\r\njoingameerror1=��¼ʧ��:��ͬ�û���������Ѿ�����Ϸ��!");   
			
				Var.V3.AddtoFile( "\r\nmodifypwusage=��e[fastlogin]��c�޸����� - �÷�:/ModifyPasswd ������ ������ ȷ��������");
				
				Var.V3.AddtoFile( "\r\nmodifyoldpwerr=��e[fastlogin]��c�޸����� - ʧ��:���������.");
				
				Var.V3.AddtoFile( "\r\nmodifyconfirmerror=��e[fastlogin]��c�޸����� - ʧ��:���������ȷ�����벻һ��.");
				
				Var.V3.AddtoFile( "\r\nremodifypasswd=��e[fastlogin]��c�޸����� - ʧ��:�����벻�ܺ;�����һ��.");
				
				Var.V3.AddtoFile( "\r\nmodifypasswdlengthmax=��e[fastlogin]��c�޸����� - ʧ��:�����볬����������.");
				
				Var.V3.AddtoFile( "\r\nmodifypasswdlengthmin=��e[fastlogin]��c�޸����� - ʧ��:������̫��.");
				
				Var.V3.AddtoFile( "\r\nmodifysuccessful=��e[fastlogin]��c�޸����� - ��3�ɹ�:�����Ѹ��ģ�.");
				
				Var.V3.AddtoFile( "\r\nloginout=��e[fastlogin]��c����¼�Ѿ���ע��");
			
			
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("[����]���ļ�ϵͳ����,��ɾ��FastLogin�����ļ���������������");
			}	
		    			    	 						
		}		
		
	}
	
	
	
	public void ReaderLanuage(){
													
		Var.V3.setTargetFile(FastUtil.LanguageFile);
															
		try{
			

			Language.Notlogin=Var.V3.getKeyValue("notlogin=");
			
			Language.NotRegister=Var.V3.getKeyValue("notregister=");
					
			Language.LoginTimeOutKick=Var.V3.getKeyValue("logintimeoutkick=");
			
			Language.RepeatLogin=Var.V3.getKeyValue("repeatlogin=");
			
			Language.RepeatRegister=Var.V3.getKeyValue("repeatregister=");
			
			Language.NotRegister2=Var.V3.getKeyValue("notregister2=");
			
			Language.LoginSuccessful=Var.V3.getKeyValue("loginsuccessful=");
			
			Language.PasswordError=Var.V3.getKeyValue("passworderror=");
			
			Language.RegisterSuccessful=Var.V3.getKeyValue("registersuccessful=");
			
			Language.NullPassword=Var.V3.getKeyValue("nullpassword=");
			
			Language.PassMax=Var.V3.getKeyValue("passwdmax=");
			
			Language.PassMin=Var.V3.getKeyValue("passwdmin=");
			
			Language.NoConfirmPasswd=Var.V3.getKeyValue("confirmpasswd=");
			
			Language.ConfirmPasswdError=Var.V3.getKeyValue("confirmpasswderror=");
					
			Language.JoinGameError1=Var.V3.getKeyValue("joingameerror1=");
		
			Language.ModifyPwUsage=Var.V3.getKeyValue("modifypwusage=");
			
			Language.ModifyOldpwErr=Var.V3.getKeyValue("modifyoldpwerr=");
			
			Language.ModifyConfirmError=Var.V3.getKeyValue("modifyconfirmerror=");
			
			Language.ReModifyPasswd=Var.V3.getKeyValue("remodifypasswd=");
			
			Language.ModifyPasswdLengthMax=Var.V3.getKeyValue("modifypasswdlengthmax=");
			
			Language.ModifyPasswdLengthMin=Var.V3.getKeyValue("modifypasswdlengthmin=");
			
			Language.ModifySuccessful=Var.V3.getKeyValue("modifysuccessful=");
			
			Language.LoginOut=Var.V3.getKeyValue("loginout=");
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[����]���ļ�ϵͳ����,��ɾ��FastLogin�����ļ���������������");
		}						
		
	}
	
	
	public void ReaderConfig(){
		
		Var.V3.setTargetFile(FastUtil.Mconf);
			
		try{
			
			Var.PasswordMaxlength=new Integer(Var.V3.getKeyValue("passwordlenmaxlength="));
			
			Var.PasswordMinlength=new Integer(Var.V3.getKeyValue("passwordlenminlength="));		
			
			Var.LoginTimeOut=new Integer(Var.V3.getKeyValue("logintimeout="));
			
			Var.MinNameLength=new Integer(Var.V3.getKeyValue("minnamelength="));			
			
			Var.MessageInterval=new Integer(Var.V3.getKeyValue("messageinterval="));
			
			Var.LocationProtection=new Boolean(Var.V3.getKeyValue("locationprotection="));		
			
			Var.Location_world=Var.V3.getKeyValue("location.world=");
			
			Var.isStrictmode=new Boolean(Var.V3.getKeyValue("namestrictmode="));	
			
			Var.BanName=Var.V3.getKeyValue("banname=").split(";");
			
			Var.ipmaxreg=new Integer(Var.V3.getKeyValue("ipmaxreg="));
			
			Var.NoDamageTime=new Integer(Var.V3.getKeyValue("nodamagetime="));
			
			Var.Regex=Var.V3.getKeyValue("regex=");
			
			Var.PlayerLoginedMessage=Var.V3.getKeyValue("playerloginedmessage=");
			
			Var.PlayerJoinedMessage=Var.V3.getKeyValue("playerjoinedmessage=");
			
			if( ! (Var.Location_world.equalsIgnoreCase("empty"))){		
				
				Var.Location_x=new Integer(Var.V3.getKeyValue("location.x="));
				Var.Location_y=new Integer(Var.V3.getKeyValue("location.y="));
				Var.Location_z=new Integer(Var.V3.getKeyValue("location.z="));
				Var.Location_pitch=new Integer(Var.V3.getKeyValue("location.pitch="));
				Var.Location_yaw=new Integer(Var.V3.getKeyValue("location.yaw="));
				Var.LoginLocation.setWorld(Bukkit.getWorld(Var.Location_world));
				Var.LoginLocation.setX(Var.Location_x);
				Var.LoginLocation.setY(Var.Location_y);
				Var.LoginLocation.setZ(Var.Location_z);
				Var.LoginLocation.setPitch(Var.Location_pitch);
				Var.LoginLocation.setYaw(Var.Location_yaw);
			}
			
			
		
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("[����]���ļ�ϵͳ����,��ɾ��FastLogin�����ļ���������������");
		}
		
	
		
	}
	
	
	

	
	
	
}
