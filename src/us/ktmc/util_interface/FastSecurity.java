package us.ktmc.util_interface;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.ktmc.util.Var;

public class FastSecurity {
	
	
	/**
	 * �����жϷ��������Ƿ���2��ͬ��������� (Ӱ����)
	 * @return �����2��ͬ��������߷���True ���� False
	 */
	public boolean isShadowGhost(Player CheckPlayer){
		
		int PlayerCount=0;
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
			
            if(pl.getName().equalsIgnoreCase(CheckPlayer.getName())){
            	PlayerCount++;
            }                                   
		}
		
		if(PlayerCount>1){
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * �����߳�ʹ����Ӱ����BUG��������(ȫ��)
	 * @param CheckPlayer ʹ����Ӱ��������
	 */
	public void KickShadowGhost(Player CheckPlayer){
		
		if(Var.Enable_SecurityWarning){
			
			for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
				pl.sendMessage("��e[FastLogin]��c!��b��ҡ�c"+CheckPlayer.getName()+"��b����ʹ��BUGʱ����");                             
			}
			System.out.println("[FastLogin]�����"+CheckPlayer.getName()+"����ʹ��BUGʱ����");
			
		}
		
		
		//��ѯ�߳������
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
			
            if(pl.getName().equalsIgnoreCase(CheckPlayer.getName())){
            	
            	Var.FS.KickPlayerForBukkit(pl, "[FastLogin]����¼�쳣!����������2��ͬ�����.");
            	
            }                                   
		}	
		
	}
	
	/**
	 * �첽תͬ���߳����
	 * @param pl
	 */
	public void KickPlayerForBukkit(Player pl,String KickText){
		
		Bukkit.getScheduler().runTask(Var.MainClass, new Runnable(){
			
			public void run(){
				pl.kickPlayer(KickText);
			}	
			
		});	
		
	}
	
	
	

}
