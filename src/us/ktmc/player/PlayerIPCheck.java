package us.ktmc.player;

import org.bukkit.entity.Player;
import us.ktmc.util.Var;

public class PlayerIPCheck {

	
	public boolean isMaxRegIP(Player pl){
		
		String PlayerIP=pl.getAddress().getHostName();
			
		try {
			
			//���MaxRegisterIP��0��ر��������
			if(Var.MaxRegisterIP==0){
				return false;
			}
			
			
			//�жϸ����IP�Ƿ񳬳�����
			if(Var.V2.FileRepeatFind(Var.IPtables, PlayerIP) >= Var.MaxRegisterIP){
				return true;
			}
			

		
		//���û�г�����������IPTABLES�����һ����¼
		Var.V2.AddtoFile(Var.IPtables, "\r\n"+PlayerIP);
		return false;
		
		
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		
		}

		
			
	}
	
	
	
	
	
	
	
	
	
	
}
