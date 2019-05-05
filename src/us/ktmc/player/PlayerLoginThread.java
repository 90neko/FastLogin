package us.ktmc.player;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class PlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	File PlConf=null;
	FastUtil Util=Var.Util;
	
	public PlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		PlConf=Util.getPlDataFile(pl);
	}
	
	public void run() {
		
		//�ѵ�¼��ر��߳�
		if (Util.isLogin(pl)) {		
			pl.sendMessage(Language.RepeatLogin);
			return;
			
		}

		// δע����ر��߳�
		if (!Util.isReg(pl)) {		
			pl.sendMessage(Language.NotRegister2);
			return;
			
		}
		
		// ��2��ͬ�����������ȫ���߳� �� �ر��߳�
		
		int PlayerCount=0;
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
			
            if(pl.getName().toLowerCase().equalsIgnoreCase(this.pl.getName().toLowerCase())){
            	PlayerCount++;
            }                                   
		}
		
		if(PlayerCount>1){
			
			for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
				
	            if(pl.getName().toLowerCase().equalsIgnoreCase(this.pl.getName().toLowerCase())){
	            	Var.Util.KickPlayerForBukkit(pl, "[FastLogin]��¼�쳣!����������ͬ�����.");
	            	
	            }                                   
			}
			
			return;
			
		}
		
		//����
		
		
		try {

			//������ȷ
			if (Util.isPassword(pl, args[0])) {

				Util.setPlayerLogin(pl, true);
				
				pl.sendMessage(Language.LoginSuccessful);

				//�������ڵ�¼ǰ��OP ��OP�������
				for (int i=0;i<Var.opTables.size();i++) {

					if (pl.getName().equals(Var.opTables.get(i))){		
						pl.setOp(true);
						Var.opTables.remove(i);
					}

				}
				
				//���õ�¼����
				Var.AH.LoginSuccess(pl);

				//��½��������ʱ����Ҵ��ͻ�������ߵص�
				if (Var.LocationProtection) {
					
					if (Var.Location_world.equalsIgnoreCase("empty")){
						return;
					}
					
					if (Util.getPlayerQuitLocation(pl) != null) {
						pl.teleport(Util.getPlayerQuitLocation(pl));
					}
					
					return;
				}

				//��¼��������������Ҵ�����Ԥ���Ĭ�ϵ�½��
				Util.TelePort_DefaultLoginLocation(pl);
				
				
				
				return;

			}

			
			pl.sendMessage(Language.PasswordError);
			return;

		} catch (Exception e) {
			pl.sendMessage(Language.NullPassword);
			return;
		}
		
		
	}

}
