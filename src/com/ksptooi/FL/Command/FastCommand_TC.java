package com.ksptooi.FL.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.ksptooi.FL.Performance.PerformanceMonitorManager;

public class FastCommand_TC implements FastCommand{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		
		sender.sendMessage("----FastLogin ���ܼ��----");
		sender.sendMessage("�ۼ�IO:"+PerformanceMonitorManager.getPFPC());
		sender.sendMessage("�ѻ���:"+PerformanceMonitorManager.getPDECCount());
		sender.sendMessage("��߳�:"+PerformanceMonitorManager.getPATC());
		sender.sendMessage("-------------------------");
		
		
	}

}
