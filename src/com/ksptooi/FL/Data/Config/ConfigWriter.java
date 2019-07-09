package com.ksptooi.FL.Data.Config;

import java.io.File;

import com.ksptooi.FL.Data.Config.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v6.Factory.DataSessionFactory;
import com.ksptooi.gdc.v6.Session.dataSession;


public class ConfigWriter {

	Logger logManager=null;
	
	DataSessionFactory dataSessionFactory = DataManager.getDataSessionFactory();
	
	public ConfigWriter(){
		this.logManager=new Logger();
	}
	
	
	/**
	 * ��鲢���������ļ�
	 */
	public boolean createConfig(){
			
		File config = ConfigManager.fastLoginConfigFile;
		
		if(dataSessionFactory.createdata(config) == true) {
			
			logManager.logInfo("д��Ĭ��������");
			
			dataSession ds = dataSessionFactory.openSession(config);
			
			ds.addline(" ");			
			ds.addline("#��������\r\n" + 
					"ConfigVersion=0.43-D-RE\r\n" + 
					"PlayerDataType=GeneralDataCore\r\n" + 
					"LoginTimeOut=60\r\n" + 
					"PasswordMaxLength=15\r\n" + 
					"PasswordMinLength=5\r\n" + 
					"PlayerNameMinLength=3\r\n" + 
					"MessageInterval=5\r\n" + 
					"LoginSecurityTime=3\r\n" + 
					"Enable_LoginSecurity=false\r\n" + 
					"Enable_UserNameStrictmode=true\r\n" + 
					"BanName=*;\r\n" + 
					"MaxRegisterIP=0\r\n" + 
					"RegexMatchForPlayerName=*\r\n" + 
					"\r\n" + 
					"#��Ч���Զ�������\r\n" + 
					"PlayerLoginedMessage=false\r\n" + 
					"PlayerJoinedMessage=��e%Player% Joined the game.\r\n" + 
					"PlayerQuitMessage=��e%Player% Left the game.\r\n" + 
					"Enable_PlayerLoginedEffect=true\r\n" + 
					"Enable_PlayerPreLoginEffect=true\r\n" + 
					"\r\n" + 
					"#��ȫ����\r\n" + 
					"Enable_OPSecurity=true\r\n" + 
					"Enable_SecurityWarning=true\r\n" + 
					"Enable_HellGateSecurity=true\r\n" + 
					"Enable_CreativeModeSecurity=true\r\n" + 
					"Enable_passwordHash=MD5\r\n" + 
					"Enable_SupportOldPassword=false\r\n" + 
					"Enable_DebugPrint=false\r\n" + 
					"\r\n" + 
					"GeneralDataCore - Mysql���ݿ�����\r\n" + 
					"address=127.0.0.1:3306\r\n" + 
					"database=asmc\r\n" + 
					"username=root\r\n" + 
					"password=h14R4g5Rf6H5h7f\r\n" + 
					"poolinitSize=32\r\n" + 
					"param=?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&autoReconnect=true\r\n" + 
					"\r\n" + 
					"FastLogin - Mysql���ݿ����� #������ݱ�\r\n" + 
					"PlayerDataTable=playertable\r\n" + 
					"PlayerNameField=playername\r\n" + 
					"PlayerPwdField=playerpwd\r\n" + 
					"PlayerRegStatusField=register\r\n" + 
					"PlayerLoginStatusField=login\r\n" + 
					"\r\n" + 
					"FastLogin - Mysql���ݿ����� #���λ�����ݱ�(!�������޸�)\r\n" + 
					"PlayerLocTable=playerloc\r\n" + 
					"PlayerNameField=Foreign Key\r\n" + 
					"PlayerLocworld=locworld\r\n" + 
					"PlayerLocx=locx\r\n" + 
					"PlayerLocy=locy\r\n" + 
					"PlayerLocz=locz\r\n" + 
					"PlayerLocpitch=locpitch\r\n" + 
					"PlayerLocyaw=locyaw");
			
			
			ds.release();
			
			return true;
			
		}
		
		
		return false;
		
		
		
	}
	
	
	/**
	 * ��鲢����λ���ļ�
	 */
	public boolean createLocationConfig(){
		
		File location = ConfigManager.fastLoginLocationFile;

		
		if(dataSessionFactory.createdata(location)==true) {
			
			logManager.logInfo("д��Ĭ��Location������");
			
			dataSession ds = dataSessionFactory.openSession(location);
			
			ds.addline("location.world=empty");
			ds.addline("location.x=empty");
			ds.addline("location.y=empty");
			ds.addline("location.z=empty");
			ds.addline("location.pitch=empty");
			ds.addline("location.yaw=empty");
			
			ds.release();
			
			return true;
			
		}
		
		
		return false;
		
		
		
	
		
	}
	
	/**
	 * ��鲢����IPCount�ļ�
	 */
	public void createIPCountConfig(){
		
		File ipcount = ConfigManager.fastLoginIPCountFile;
		
		if(dataSessionFactory.createdata(ipcount)==true) {
			
			logManager.logInfo("д��Ĭ��IPCount������");
			
		}
		
		
		
	}
	
	/**
	 * ��鲢���������ļ�
	 */
	public void createLanguageConfig(){
		
		File lang = ConfigManager.fastLoginLanguageFile;
		
		if(dataSessionFactory.createdata(lang) == true){
			
			dataSession ds = dataSessionFactory.openSession(lang);
			
			logManager.logInfo("д��Ĭ������������");
			
			
			ds.addline("Notlogin=��e[FastLogin]��b����Ҫ��¼����ܲ���,ʹ��/login ���� ����¼.");
			ds.addline("NotRegister=��e[FastLogin]��b����Ҫע�����ܲ���,ʹ��/register ���� ȷ������ ��ע��.");
			ds.addline("LoginTimeOutKick=��¼��ʱ");
			ds.addline("RepeatLogin=��e[FastLogin]��c���Ѿ���¼��!");
			ds.addline("RepeatRegister=��e[FastLogin]��c���Ѿ�ע����!");
			ds.addline("NotRegister2=��e[FastLogin]��c�㻹û��ע�ᣡ");
			ds.addline("LoginSuccess=��e[FastLogin]��a��¼�ɹ���");
			ds.addline("PasswordError=��e[FastLogin]��c�������");
			ds.addline("RegisterSuccess=��e[FastLogin]��aע����ɣ�");
			ds.addline("NullPassword=��e[FastLogin]��c���������룡");
			ds.addline("PasswdTooLong=��e[FastLogin]��c����������볤�ȳ���������ƣ�");
			ds.addline("PasswdTooShost=��e[FastLogin]��c�����������̫�̣�");
			ds.addline("NoConfirmPasswd=��e[FastLogin]��c������ȷ������ ��:/register 12345 12345");
			ds.addline("ConfirmPasswdError=��e[FastLogin]��c������������벻һ��");
			ds.addline("JoinGameError1=��¼ʧ��:��ͬ�û���������Ѿ�����Ϸ��!");		
			ds.addline("ChangePwUsage=��e[FastLogin]��b�޸����� - �÷�:/ChangePassword ������ ������ ȷ��������");			
			ds.addline("ChangePw_OldpwErr=��e[FastLogin]��c���������.");		
			ds.addline("ChangePw_ConfirmPwError=��e[FastLogin]��c���������ȷ�����벻һ��.");				
			ds.addline("ReModifyPasswd=��e[FastLogin]��c�����벻�ܺ;�����һ��.");				
			ds.addline("ChangePw_PasswdTooLong=��e[FastLogin]��c�����볬����������.");		
			ds.addline("ChangePw_PasswdTooShost=��e[FastLogin]��c������̫��.");				
			ds.addline("ChangePw_Success=��e[FastLogin]��a�޸����� - ���!");		
			ds.addline("LoginOut=��e[FastLogin]��c����¼�Ѿ���ע��!");	
				
			
			//new V1
			
			ds.addline("AdminSetPasswordError1=��e[FastLogin]��c�����δע��,�޸�����ʧ��!");
			
			ds.addline("AdminSetPasswordUsage=��e[FastLogin]��bʹ�÷���:/Fast SetPassword ����� ������");
			
			ds.addline("AdminSetPasswordSuccess=��e[FastLogin]��a�ɹ��޸Ĵ��������!");
			
			
			ds.addline("AdminSetPasswordKick=���뱻����Ա�޸�!");
			
			ds.addline("AdminCommandHelp1=��b[FastLogin]��OP�����÷�:");
			ds.addline("AdminCommandHelp2=��6/Fast Reload��f: ���ز���������ļ�");
			ds.addline("AdminCommandHelp3=��6/Fast setSpawn��f: ���ó�ʼ��¼�ص�");
			ds.addline("AdminCommandHelp4=��6/Fast Spawn��f: ���͵���ʼ��¼�ص�");
			ds.addline("AdminCommandHelp5=��6/Fast delSpawn��f: ɾ����ʼ��¼�ص�");
			ds.addline("AdminCommandHelp6=��6/Fast setPassword��f: ������ҵ�����");
			
			ds.addline("OPHasbeenCanceld=��e[FastLoginSecurity]��c��������δ��¼,���OPȨ���ѱ�ȡ��!");
			ds.addline("CreativeModeHasbeenCanceld=��e[FastLoginSecurity]��c��������δ��¼,��Ĵ���ģʽ�ѱ�ȡ��!");
			
			ds.addline("OPRestore=��e[FastLoginSecurity]��a�����OPȨ���Ѿ��ָ�!");
			
			ds.addline("CreativeModeRestore=��e[FastLoginSecurity]��a����Ĵ���ģʽ�Ѿ��ָ�!");
			
			ds.addline("setSpawnSuccess=��e[FastLogin]��a����ʼ��¼��������!");
			
			ds.addline("DeleteSpawnSuccess=��e[FastLogin]��a����ʼ��¼����ɾ��!");
			
			ds.addline("TPSpawnSuccess=��e[FastLogin]��6�����ڴ���...");
			
			ds.addline("OPHasBeenCleared=��e[FastLoginSecurity]��c��������û��ע��,���OP�ѱ����..");
			
			ds.addline("CreativeHasBeenCleared=��e[FastLoginSecurity]��c��������û��ע��,���OP�ѱ����..");
			
			ds.release();
			
		}
		
		
		
	}
	
	/**
	 * ����λ���ļ�
	 */
	public void updateLocationConfig(DefaultLocationEntity dle){
		
		
		File location = ConfigManager.fastLoginLocationFile;
		
		
		dataSession ds = dataSessionFactory.openSession(location);
		
		ds.set("location.world", dle.getLocation_world());
		ds.set("location.x", dle.getLocation_x());
		ds.set("location.y", dle.getLocation_y());
		ds.set("location.z", dle.getLocation_z());
		ds.set("location.pitch", dle.getLocation_pitch());
		ds.set("location.yaw", dle.getLocation_yaw());
		
		ds.release();

	}
	
	
	

	

	
}
