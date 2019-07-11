package com.ksptooi.FL.General.Init;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Command.CommandManager;
import com.ksptooi.FL.Command.FastCommand_CHANGEPASSWORD;
import com.ksptooi.FL.Command.FastCommand_DELSPAWN;
import com.ksptooi.FL.Command.FastCommand_LOGIN;
import com.ksptooi.FL.Command.FastCommand_REGISTER;
import com.ksptooi.FL.Command.FastCommand_RELOAD;
import com.ksptooi.FL.Command.FastCommand_SETPASSWORD;
import com.ksptooi.FL.Command.FastCommand_SETSPAWN;
import com.ksptooi.FL.Command.FastCommand_SPAWN;
import com.ksptooi.FL.Command.FastCommand_TC;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;

public class FastLogin_init {

	
	
	public static void init() {
		
		DataManager.PreInitDataManager();
		
		//���ü��
		ConfigManager.createConfig();
		ConfigManager.createLanguage();
		ConfigManager.createLocation();
		ConfigManager.createIPCount();		
			
		try {
					
			//���ö�ȡ
			
			ConfigManager.readLanguage();
			
			ConfigManager.readLocation();
			
			ConfigManager.readConfig();
			
			
		}catch(Exception e){
			ConfigManager.configUpdate();
		}

		
		ConfigManager.configUpdate();
		
		FastLogin_init.regCommands();
		
		
		DataManager.initDataManager();
		
		
	}
	
	
	//ע������
	public static void regCommands() {
		
		CommandManager cm = FastLogin.getCommandManager();
				
		cm.regCommand("fast tc", new FastCommand_TC());
		cm.regCommand("fast reload", new FastCommand_RELOAD());
		cm.regCommand("fast spawn", new FastCommand_SPAWN());
		cm.regCommand("fast setspawn", new FastCommand_SETSPAWN());
		cm.regCommand("fast delspawn", new FastCommand_DELSPAWN());
		cm.regCommand("fast setpassword", new FastCommand_SETPASSWORD());
		
		cm.regCommand("changepassword", new FastCommand_CHANGEPASSWORD());
		cm.regCommand("editpwd", new FastCommand_CHANGEPASSWORD());
		cm.regCommand("l", new FastCommand_LOGIN());
		cm.regCommand("login", new FastCommand_LOGIN());
		cm.regCommand("reg", new FastCommand_REGISTER());
		cm.regCommand("register", new FastCommand_REGISTER());		
		
	}
	
	
	
	
	
}
