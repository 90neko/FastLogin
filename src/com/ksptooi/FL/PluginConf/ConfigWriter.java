package com.ksptooi.FL.PluginConf;

import com.ksptooi.FL.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;


public class ConfigWriter {

	LogManager logManager=null;
	IOController_V5 v5=new IOController_V5();
	
	public ConfigWriter(){
		this.logManager=new LogManager();
	}
	
	
	/**
	 * ��鲢���������ļ�
	 */
	public void createConfig(){
		
		logManager.writerInfo("���������");
		
		if(v5.createNewGdcFile(FUtil.fastLoginConfigFile) == true){
			
			logManager.writerInfo("д��Ĭ��������");
			
			v5.setTarget(FUtil.fastLoginConfigFile);
			
			//д�������ļ�����
			v5.addLine("#��������");
			v5.addLine("ConfigVersion="+FUtil.Version);
			v5.addLine("PlayerDataType=GeneralDataCore");
			v5.addLine("LoginTimeOut=60");
			v5.addLine("PasswordMaxLength=15");
			v5.addLine("PasswordMinLength=5");
			v5.addLine("PlayerNameMinLength=3");
			v5.addLine("MessageInterval=5");
			v5.addLine("LoginSecurityTime=3");
			v5.addLine("Enable_LoginSecurity=false");
			v5.addLine("Enable_UserNameStrictmode=true");
			v5.addLine("BanName=*;");
			v5.addLine("MaxRegisterIP=0");
			v5.addLine("RegexMatchForPlayerName=*");
			
			v5.addLine("#");
			v5.addLine("#��Ч���Զ�������");
			v5.addLine("PlayerLoginedMessage=false");
			v5.addLine("PlayerJoinedMessage=��e%Player% Joined the game.");
			v5.addLine("PlayerQuitMessage=��e%Player% Left the game.");
			v5.addLine("Enable_PlayerLoginedEffect=true");
			v5.addLine("Enable_PlayerPreLoginEffect=true");
			
			v5.addLine("#");
			v5.addLine("#��ȫ����");			
			v5.addLine("Enable_OPSecurity=true");
			v5.addLine("Enable_SecurityWarning=true");
			v5.addLine("Enable_HellGateSecurity=true");
			v5.addLine("Enable_CreativeModeSecurity=true");
			v5.addLine("Enable_passwordHash=MD5");
			v5.addLine("Enable_SupportOldPassword=false");
			v5.addLine("Enable_DebugPrint=false");
	
			
			v5.addLine("#");
			v5.addLine("GeneralDataCore - Mysql���ݿ�����");
			v5.addLine("MysqlAddress=127.0.0.1:3306");
			v5.addLine("DataBaseName=fastlogin");
			v5.addLine("MysqlUser=root");
			v5.addLine("MysqlPwd=root");
			v5.addLine("Param=?useSSL=false��characterEncoding=utf8��serverTimezone=UTC��autoReconnect=true");
			v5.addLine("#");
			v5.addLine("FastLogin - Mysql���ݿ����� #������ݱ�");
			v5.addLine("PlayerDataTable=playertable");
			v5.addLine("PlayerNameField=playername");
			v5.addLine("PlayerPwdField=playerpwd");
			v5.addLine("PlayerRegStatusField=register");
			v5.addLine("PlayerLoginStatusField=login");
			v5.addLine("#");
			v5.addLine("FastLogin - Mysql���ݿ����� #���λ�����ݱ�(!�������޸�)");
			v5.addLine("PlayerLocTable=playerloc");
			v5.addLine("PlayerNameField=Foreign Key");
			v5.addLine("PlayerLocworld=locworld");
			v5.addLine("PlayerLocx=locx");
			v5.addLine("PlayerLocy=locy");
			v5.addLine("PlayerLocz=locz");
			v5.addLine("PlayerLocpitch=locpitch");
			v5.addLine("PlayerLocyaw=locyaw");
			
		}
		
		
	}
	
	
	/**
	 * ��鲢����λ���ļ�
	 */
	public void createLocationConfig(){
		
		logManager.writerInfo("���Location������");
		
		if(v5.createNewGdcFile(FUtil.fastLoginLocationFile) == true){
			
			logManager.writerInfo("д��Ĭ��Location������");
			
			
			v5.setTarget(FUtil.fastLoginLocationFile);
			
			
			//д��Location�ļ�����
			v5.addLine("location.world=empty");
			v5.addLine("location.x=empty");
			v5.addLine("location.y=empty");
			v5.addLine("location.z=empty");
			v5.addLine("location.pitch=empty");
			v5.addLine("location.yaw=empty");

			
		}
		
	
		
	}
	
	/**
	 * ��鲢����IPCount�ļ�
	 */
	public void createIPCountConfig(){
		
		logManager.writerInfo("���IPCount������");
		
		if(v5.createNewGdcFile(FUtil.fastLoginIPCountFile) == true){
			
			logManager.writerInfo("д��Ĭ��IPCount������");
			
		}
		
		
		
	}
	
	/**
	 * ��鲢���������ļ�
	 */
	public void createLanguageConfig(){
		
		logManager.writerInfo("�������������");
		
		if(v5.createNewGdcFile(FUtil.fastLoginLanguageFile) == true){
			
			logManager.writerInfo("д��Ĭ������������");
			
			v5.setTarget(FUtil.fastLoginLanguageFile);
			
			v5.addLine("Notlogin=��e[FastLogin]��b����Ҫ��¼����ܲ���,ʹ��/login ���� ����¼.");
			v5.addLine("NotRegister=��e[FastLogin]��b����Ҫע�����ܲ���,ʹ��/register ���� ȷ������ ��ע��.");
			v5.addLine("LoginTimeOutKick=��¼��ʱ");
			v5.addLine("RepeatLogin=��e[FastLogin]��c���Ѿ���¼��!");
			v5.addLine("RepeatRegister=��e[FastLogin]��c���Ѿ�ע����!");
			v5.addLine("NotRegister2=��e[FastLogin]��c�㻹û��ע�ᣡ");
			v5.addLine("LoginSuccess=��e[FastLogin]��a��¼�ɹ���");
			v5.addLine("PasswordError=��e[FastLogin]��c�������");
			v5.addLine("RegisterSuccess=��e[FastLogin]��aע����ɣ�");
			v5.addLine("NullPassword=��e[FastLogin]��c���������룡");
			v5.addLine("PasswdTooLong=��e[FastLogin]��c����������볤�ȳ���������ƣ�");
			v5.addLine("PasswdTooShost=��e[FastLogin]��c�����������̫�̣�");
			v5.addLine("NoConfirmPasswd=��e[FastLogin]��c������ȷ������ ��:/register 12345 12345");
			v5.addLine("ConfirmPasswdError=��e[FastLogin]��c������������벻һ��");
			v5.addLine("JoinGameError1=��¼ʧ��:��ͬ�û���������Ѿ�����Ϸ��!");		
			v5.addLine("ChangePwUsage=��e[FastLogin]��b�޸����� - �÷�:/ChangePassword ������ ������ ȷ��������");			
			v5.addLine("ChangePw_OldpwErr=��e[FastLogin]��c���������.");		
			v5.addLine("ChangePw_ConfirmPwError=��e[FastLogin]��c���������ȷ�����벻һ��.");				
			v5.addLine("ReModifyPasswd=��e[FastLogin]��c�����벻�ܺ;�����һ��.");				
			v5.addLine("ChangePw_PasswdTooLong=��e[FastLogin]��c�����볬����������.");		
			v5.addLine("ChangePw_PasswdTooShost=��e[FastLogin]��c������̫��.");				
			v5.addLine("ChangePw_Success=��e[FastLogin]��a�޸����� - ���!");		
			v5.addLine("LoginOut=��e[FastLogin]��c����¼�Ѿ���ע��!");	
				
			
			//new V1
			
			v5.addLine("AdminSetPasswordError1=��e[FastLogin]��c�����δע��,�޸�����ʧ��!");
			
			v5.addLine("AdminSetPasswordUsage=��e[FastLogin]��bʹ�÷���:/Fast SetPassword ����� ������");
			
			v5.addLine("AdminSetPasswordSuccess=��e[FastLogin]��a�ɹ��޸Ĵ��������!");
			
			
			v5.addLine("AdminSetPasswordKick=���뱻����Ա�޸�!");
			
			v5.addLine("AdminCommandHelp1=��b[FastLogin]��OP�����÷�:");
			v5.addLine("AdminCommandHelp2=��6/Fast Reload��f: ���ز���������ļ�");
			v5.addLine("AdminCommandHelp3=��6/Fast setSpawn��f: ���ó�ʼ��¼�ص�");
			v5.addLine("AdminCommandHelp4=��6/Fast Spawn��f: ���͵���ʼ��¼�ص�");
			v5.addLine("AdminCommandHelp5=��6/Fast delSpawn��f: ɾ����ʼ��¼�ص�");
			v5.addLine("AdminCommandHelp6=��6/Fast setPassword��f: ������ҵ�����");
			
			v5.addLine("OPHasbeenCanceld=��e[FastLoginSecurity]��c��������δ��¼,���OPȨ���ѱ�ȡ��!");
			v5.addLine("CreativeModeHasbeenCanceld=��e[FastLoginSecurity]��c��������δ��¼,��Ĵ���ģʽ�ѱ�ȡ��!");
			
			v5.addLine("OPRestore=��e[FastLoginSecurity]��a�����OPȨ���Ѿ��ָ�!");
			
			v5.addLine("CreativeModeRestore=��e[FastLoginSecurity]��a����Ĵ���ģʽ�Ѿ��ָ�!");
			
			v5.addLine("setSpawnSuccess=��e[FastLogin]��a����ʼ��¼��������!");
			
			v5.addLine("DeleteSpawnSuccess=��e[FastLogin]��a����ʼ��¼����ɾ��!");
			
			v5.addLine("TPSpawnSuccess=��e[FastLogin]��6�����ڴ���...");
			
			v5.addLine("OPHasBeenCleared=��e[FastLoginSecurity]��c��������û��ע��,���OP�ѱ����..");
			
			v5.addLine("CreativeHasBeenCleared=��e[FastLoginSecurity]��c��������û��ע��,���OP�ѱ����..");
			
		}
		
		
		
	}
	
	/**
	 * ����λ���ļ�
	 */
	public void updateLocationConfig(DefaultLocationEntity DLE){
		
		v5.setTarget(FUtil.fastLoginLocationFile);

		
		v5.setKeyValue("location.world", DLE.getLocation_world());
		v5.setKeyValue("location.x", String.valueOf(DLE.getLocation_x()));
		v5.setKeyValue("location.y", String.valueOf(DLE.getLocation_y()));
		v5.setKeyValue("location.z", String.valueOf(DLE.getLocation_z()));
		v5.setKeyValue("location.pitch", String.valueOf(DLE.getLocation_pitch()));
		v5.setKeyValue("location.yaw", String.valueOf(DLE.getLocation_yaw()));

	}
	
	
	

	

	
}
