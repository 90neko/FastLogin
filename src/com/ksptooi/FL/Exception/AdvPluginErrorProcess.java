package com.ksptooi.FL.Exception;

import org.bukkit.Bukkit;

import com.ksptooi.FL.Util.LogManager;

public class AdvPluginErrorProcess {

	LogManager lm=null;
	
	
	public AdvPluginErrorProcess(){
		lm = new LogManager();
		
	}
	
	
	public void ErrorOfGeneralDataCoreNoFound(){
		
		
		lm.writerError("��������!");
		lm.writerError("û���ҵ�ǰ�ò��-GeneralDataCoreV5");
		Bukkit.shutdown();
	}
	
	
	public void ErrorOfGeneralDataCoreVersionInvaild(){
		
		lm.writerError("��������!");
		lm.writerError("ǰ�ò��GeneralDataCore�İ汾��ƥ��!");
		Bukkit.shutdown();
		
	}
	
	
}
