package us.ktmc.player;


import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;

public class PlayerProcessThread implements Runnable{
	
	
	Player pl=null;
	int LoginTime=0;
	int sendtime=0;
	FastUtil Util=new GeneralUtil();
	
	public PlayerProcessThread(Player pl) {
		this.pl=pl;			
	}
	
	
	public void run(){

		
		
		if(Var.isMysql){
			
			if(! Var.SQL.SQL_isReg(pl)){
				pl.sendMessage(Language.NotRegister);
			}else{
				pl.sendMessage(Language.Notlogin);	
			}
			
		}else{
			
			if(! Util.isReg(pl)){
				pl.sendMessage(Language.NotRegister);
			}else{
				pl.sendMessage(Language.Notlogin);	
			}
				
		}
		
		

		
		
					 
		while(true){
			
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendtime++;
			LoginTime++;
			
			//��������ر��߳�
			if(!pl.isOnline()){
				break;
			}
			
			//��½��ʱ��ر��߳�
			if(LoginTime > Var.LoginTimeOut){
						
				Var.FS.KickPlayerForBukkit(pl, Language.LoginTimeOutKick);	
				
				break;
			}
			
			
			
			if(Var.isMysql){
				
				//�ѵ�¼��ر��߳�
				if (Var.SQL.SQL_isLogin(pl)) {
					
					Var.NoDamagePlayer.remove(pl.getName());
					break;

				}
				
				
				
			}else{
				
				//�ѵ�¼��ر��߳�
				if (Util.isLogin(pl)) {
					
					Var.NoDamagePlayer.remove(pl.getName());
					break;

				}
				
			}
			
			

			
					
			if(!(sendtime >= Var.MessageInterval)){
				continue;
			}
					
			sendtime=0;	
			
			
			
			
			if(Var.isMysql){
				
				if(! Var.SQL.SQL_isReg(pl)){
					pl.sendMessage(Language.NotRegister);
					continue;
				}	
				
				
			}else{
				
				if(! Util.isReg(pl)){
					pl.sendMessage(Language.NotRegister);
					continue;
				}	
				
			}
			

			
			if(Var.isMysql){
				
				if(! Var.SQL.SQL_isLogin(pl)){
					pl.sendMessage(Language.Notlogin);
					continue;
				}	
				
				
			}else{
				
				if(! Util.isLogin(pl)){
					pl.sendMessage(Language.Notlogin);
					continue;
				}	
				
			}
			
			break;
			
		}
		
		
		
	}
	

}
