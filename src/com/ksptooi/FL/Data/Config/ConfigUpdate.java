package com.ksptooi.FL.Data.Config;

import java.io.File;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Util.Logger;

public class ConfigUpdate {

	
	Logger logManager=null;

	
	public ConfigUpdate(){
		logManager = new Logger();
	}
	
	
	
	public void updateConfig(){
		
		String FastLoginVersion = FastLogin.getVersion();
			
		
		logManager.logInfo("������ð汾");
		
		try{
			
			String ConfigVersion = ConfigManager.getConfig().getConfigVersion();
			
			//�ж������ļ��汾
			if(!ConfigVersion.equals(FastLoginVersion)){
				
				
				File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_"+ConfigVersion+".conf");
				
				File Config = ConfigManager.fastLoginConfigFile;
				
				logManager.logWarning("�����ļ��汾����");
				
				Config.renameTo(OldConfig);			
				
				logManager.logInfo("����������");
				
				
				ConfigManager.createConfig();
				
				ConfigManager.readConfig();				
				ConfigManager.readLanguage();
				ConfigManager.readLocation();

				
			}
			
			
		}catch(Exception e){
			
					
			logManager.logWarning("�����ļ��汾����");
			
			File OldConfig=new File("plugins/ksptooi/fastlogin/OldConfig_FastLogin.conf");
			File Config = ConfigManager.fastLoginConfigFile;
				
			Config.renameTo(OldConfig);
			
			
			logManager.logInfo("����������");
			
			ConfigManager.createConfig();
			
			ConfigManager.readConfig();				
			ConfigManager.readLanguage();
			ConfigManager.readLocation();
				
			
		}
		

		
		
		
	}
	
	
	
	
}
