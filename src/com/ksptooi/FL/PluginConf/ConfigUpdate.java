package com.ksptooi.FL.PluginConf;

import java.io.File;

import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;

public class ConfigUpdate {

	
	LogManager logManager=null;
	IOController_V5 v5=null; 
	ConfigWriter PCCW=null;
	ConfigReader PCCR=null;
	
	public ConfigUpdate(){
		PCCW = new ConfigWriter();
		logManager = new LogManager();
		v5=new IOController_V5();
		PCCR = new ConfigReader();
	}
	
	
	
	
	public void checkAndUpdateOfConfig(){
		
		String FastLoginVersion = FUtil.Version;
		
		String ConfigVersion = FUtil.config.getVersion();
		
		
		logManager.writerInfo("������ð汾");
		
		
		try{
			
			//�ж������ļ��汾
			if(!ConfigVersion.equals(FastLoginVersion)){
				
				
				File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_"+ConfigVersion+".conf");
				
				File Config = FUtil.fastLoginConfigFile;
				
				logManager.writerWarning("�����ļ��汾����");
				
				
				Config.renameTo(OldConfig);				
				
				logManager.writerInfo("����������");
				
				PCCW.createConfig();
				
				FUtil.config=PCCR.readerConfig();
				FUtil.defaultLocationEntity=PCCR.readerLocationConfig();
				FUtil.language=PCCR.readerLanguageConfig();
				
			}
			
			
		}catch(Exception e){
			
			
			
			logManager.writerWarning("�����ļ��汾����");
			
			File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_FastLogin.conf");
			File Config = FUtil.fastLoginConfigFile;
				
			Config.renameTo(OldConfig);
			
			
			logManager.writerInfo("����������");
			
			PCCW.createConfig();
			
			FUtil.config=PCCR.readerConfig();
			FUtil.defaultLocationEntity=PCCR.readerLocationConfig();
			FUtil.language=PCCR.readerLanguageConfig();
			
			
			
		}
		

		
		
		
	}
	
	
	
	
}
