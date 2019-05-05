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
				
				Var.V3.OutputToFile(FastUtil.Mconf, "Version=" + FastUtil.FastLoginVersion, "UTF-8");
				Var.V3.AddtoFile("\r\n"+"PlayerDataType=GeneralDataCore");
				Var.V3.AddtoFile("\r\n"+"LoginTimeOut=60");
				Var.V3.AddtoFile("\r\n"+"PasswordMaxLength=15");
				Var.V3.AddtoFile("\r\n"+"PasswordMinLength=5");
				Var.V3.AddtoFile("\r\n"+"PlayerNameMinLength=3");
				Var.V3.AddtoFile("\r\n"+"location.world=empty");
				Var.V3.AddtoFile("\r\n"+"location.x=empty");
				Var.V3.AddtoFile("\r\n"+"location.y=empty");
				Var.V3.AddtoFile("\r\n"+"location.z=empty");
				Var.V3.AddtoFile("\r\n"+"location.yaw=empty");
				Var.V3.AddtoFile("\r\n"+"location.pitch=empty");
				Var.V3.AddtoFile("\r\n"+"MessageInterval=5");
				Var.V3.AddtoFile("\r\n"+"NoDamageTime=0");
				Var.V3.AddtoFile("\r\n"+"Enable_LoginSecurity=false");
				Var.V3.AddtoFile("\r\n"+"Enable_UserNameStrictmode=true");
				Var.V3.AddtoFile("\r\n"+"BanName=*;");
				Var.V3.AddtoFile("\r\n"+"MaxRegisterIP=3");
				Var.V3.AddtoFile("\r\n"+"RegexMatchForPlayerName=*");
				Var.V3.AddtoFile("\r\n"+"PlayerLoginedMessage=false");
				Var.V3.AddtoFile("\r\n"+"PlayerJoinedMessage=&e%Player% Joined the game.");
				Var.V3.AddtoFile("\r\n"+"PlayerQuitMessage=&e%Player% Left the game.");
				Var.V3.AddtoFile("\r\n"+"Enable_OPSecurity=true");
				Var.V3.AddtoFile("\r\n"+"Enable_SecurityWarning=true");
				
				Var.V3.AddtoFile("\r\n\r\n"+"GeneralDataCore - Mysql���ݿ�����");
				Var.V3.AddtoFile("\r\n"+"MysqlAddress=127.0.0.1:3306");
				Var.V3.AddtoFile("\r\n"+"DataBaseName=FastLogin");
				Var.V3.AddtoFile("\r\n"+"MysqlUser=root");
				Var.V3.AddtoFile("\r\n"+"MysqlPwd=root");
				Var.V3.AddtoFile("\r\n"+"Param=?useSSL=false&characterEncoding=utf8");
				
				Var.V3.AddtoFile("\r\n\r\n"+"FastLogin - Mysql���ݿ����� #������ݱ�");
				Var.V3.AddtoFile("\r\n"+"PlayerDataTable=playertable");
				Var.V3.AddtoFile("\r\n"+"PlayerNameField=playername");
				Var.V3.AddtoFile("\r\n"+"PlayerPwdField=playerpwd");
				Var.V3.AddtoFile("\r\n"+"PlayerRegStatusField=register");
				Var.V3.AddtoFile("\r\n"+"PlayerLoginStatusField=login");
				
				Var.V3.AddtoFile("\r\n\r\n"+"FastLogin - Mysql���ݿ����� #���λ�����ݱ�(!�������޸�)");
				Var.V3.AddtoFile("\r\n"+"PlayerLocTable=playerloc");
				Var.V3.AddtoFile("\r\n"+"PlayerNameField=Foreign Key");
				Var.V3.AddtoFile("\r\n"+"PlayerLocworld=locworld");
				Var.V3.AddtoFile("\r\n"+"PlayerLocx=locx");
				Var.V3.AddtoFile("\r\n"+"PlayerLocy=locy");
				Var.V3.AddtoFile("\r\n"+"PlayerLocz=locz");
				Var.V3.AddtoFile("\r\n"+"PlayerLocpitch=locpitch");
				Var.V3.AddtoFile("\r\n"+"PlayerLocyaw=locyaw");
				

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
			
			Var.PlayerDataType=Var.V3.getKeyValue("PlayerDataType=");
			
			Var.LoginTimeOut=new Integer(Var.V3.getKeyValue("LoginTimeOut="));
			
			Var.PasswordMaxLength=new Integer(Var.V3.getKeyValue("PasswordMaxLength="));
			
			Var.PasswordMinLength=new Integer(Var.V3.getKeyValue("PasswordMinLength="));		
			
			Var.PlayerNameMinLength=new Integer(Var.V3.getKeyValue("PlayerNameMinLength="));			
		
			Var.MessageInterval=new Integer(Var.V3.getKeyValue("MessageInterval="));
			
			Var.NoDamageTime=new Integer(Var.V3.getKeyValue("NoDamageTime="));
			
			Var.Enable_LoginSecurity=new Boolean(Var.V3.getKeyValue("Enable_LoginSecurity="));	
			
			Var.Enable_UserNameStrictmode=new Boolean(Var.V3.getKeyValue("Enable_UserNameStrictmode="));	
			
			Var.BanName=Var.V3.getKeyValue("BanName=").split(";");
			
			Var.MaxRegisterIP=new Integer(Var.V3.getKeyValue("MaxRegisterIP="));
			
			Var.RegexMatchForPlayerName=Var.V3.getKeyValue("RegexMatchForPlayerName=");
			
			Var.PlayerLoginedMessage=Var.V3.getKeyValue("PlayerLoginedMessage=");
			
			Var.PlayerJoinedMessage=Var.V3.getKeyValue("PlayerJoinedMessage=");
			
			Var.PlayerQuitMessage=Var.V3.getKeyValue("PlayerQuitMessage=");
			
			Var.Enable_OPSecurity=new Boolean(Var.V3.getKeyValue("Enable_OPSecurity="));
			
			Var.Enable_SecurityWarning=new Boolean(Var.V3.getKeyValue("Enable_SecurityWarning="));
			
			//#FastLogin - Mysql���ݿ����� #������ݱ�
			Var.PlayerDataTable=Var.V3.getKeyValue("PlayerDataTable=");
			
			Var.PlayerNameField=Var.V3.getKeyValue("PlayerNameField=");
			
			Var.PlayerPwdField=Var.V3.getKeyValue("PlayerPwdField=");
			
			Var.PlayerRegStatusField=Var.V3.getKeyValue("PlayerRegStatusField=");
			
			Var.PlayerLoginStatusField=Var.V3.getKeyValue("PlayerLoginStatusField=");
			
			//#FastLogin - Mysql���ݿ����� #���λ�����ݱ�
			Var.PlayerLocTable=Var.V3.getKeyValue("PlayerLocTable=");
			
			Var.PlayerLocworld=Var.V3.getKeyValue("PlayerLocworld=");
			
			Var.PlayerLocx=Var.V3.getKeyValue("PlayerLocx=");
			
			Var.PlayerLocy=Var.V3.getKeyValue("PlayerLocy=");
			
			Var.PlayerLocz=Var.V3.getKeyValue("PlayerLocz=");
			
			Var.PlayerLocpitch=Var.V3.getKeyValue("PlayerLocpitch=");
			
			Var.PlayerLocyaw=Var.V3.getKeyValue("PlayerLocyaw=");
			
			

			//�����ַ�ļ�
			
			Var.Location_world=Var.V3.getKeyValue("location.world=");
			
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
