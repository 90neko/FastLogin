package us.ktmc.player;

import java.io.File;
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
		if(Var.isMysql){
			
			if (!Var.SQL.SQL_isReg(pl)) {		
				pl.sendMessage(Language.NotRegister2);
				return;
			}
			
		}else{
			
			if (!Util.isReg(pl)) {		
				pl.sendMessage(Language.NotRegister2);
				return;
			}
			
		}
		

		
		// ��2��ͬ�����������ȫ���߳����ر��߳�
		if(Var.FS.isShadowGhost(pl)){
			
			Var.FS.KickShadowGhost(pl);
			return;
		}
		
		//END - ��¼������
		
		
		try {

			if(Var.isMysql){
				
				//������ȷ
				if (Var.SQL.SQL_isPassword(pl, args[0])) {
					
					//���õ�¼�������
					Var.AH.LoginSuccess(pl);				
					
					return;

				}
					
			}else{
				
				
				//������ȷ
				if (Util.isPassword(pl, args[0])) {
					
					//���õ�¼�������
					Var.AH.LoginSuccess(pl);				
					
					return;

				}
					
			}


			pl.sendMessage(Language.PasswordError);
			return;

		} catch (Exception e) {
			pl.sendMessage(Language.NullPassword);
			return;
		}
		
		
	}

}
