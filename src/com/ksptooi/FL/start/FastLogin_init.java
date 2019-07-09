package com.ksptooi.FL.start;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Command.CommandManager;
import com.ksptooi.FL.Command.FastCommand_CHANGEPASSWORD;
import com.ksptooi.FL.Command.FastCommand_DELSPAWN;
import com.ksptooi.FL.Command.FastCommand_LOGIN;
import com.ksptooi.FL.Command.FastCommand_REGISTER;
import com.ksptooi.FL.Command.FastCommand_RELOAD;
import com.ksptooi.FL.Command.FastCommand_SETSPAWN;
import com.ksptooi.FL.Command.FastCommand_SPAWN;
import com.ksptooi.FL.Command.FastCommand_TC;
import com.ksptooi.FL.Data.Config.ConfigManager;

public class FastLogin_init {

	
	
	public static void init() {
		
		//���ü��
		ConfigManager.createConfig();
		ConfigManager.createLanguage();
		ConfigManager.createLocation();
		ConfigManager.createIPCount();
		
		
		//���ö�ȡ
		ConfigManager.readConfig();
		
		ConfigManager.readLanguage();
		
		ConfigManager.readLocation();
		
	
		//���ð汾���
		ConfigManager.configUpdate();
		
		
		FastLogin_init.regCommands();
		
	}
	
	
	//ע������
	public static void regCommands() {
		
		CommandManager cm = FastLogin.getCommandManager();
		
		
		cm.regCommand("tc", new FastCommand_TC());
		cm.regCommand("reload", new FastCommand_RELOAD());
		cm.regCommand("spawn", new FastCommand_SPAWN());
		cm.regCommand("setspawn", new FastCommand_SETSPAWN());
		cm.regCommand("delspawn", new FastCommand_DELSPAWN());
		cm.regCommand("changepassword", new FastCommand_CHANGEPASSWORD());
		cm.regCommand("editpwd", new FastCommand_CHANGEPASSWORD());
		cm.regCommand("l", new FastCommand_LOGIN());
		cm.regCommand("login", new FastCommand_LOGIN());
		cm.regCommand("reg", new FastCommand_REGISTER());
		cm.regCommand("register", new FastCommand_REGISTER());
		
		
	}
	
	
	
	
	
}
