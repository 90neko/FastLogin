package com.ksptooi.FL.Thread.Player;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.PlayerProcess.*;

public class PlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	PlayerData_Interface playerDataBLL=null;
	Logger lm=null;
	PlayerEffectManager PEP=null;
	PlayerPasswordProcess PlayerPasswordProcess = null;

	
	public PlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		lm=new Logger();
		playerDataBLL=new PlayerDataManager();
		PEP = new PlayerEffectManager();
		PlayerPasswordProcess = new PlayerPasswordProcess();
	}
	
	public void run() {
		
		PlayerEntity PDE = playerDataBLL.getPlayerData(pl);
		
		//�ѵ�¼��ر��߳�
		if (PDE.isLogin()) {		
			pl.sendMessage(ConfigManager.getLanguage().getRepeatLogin());
			return;
		}
		
		
		// δע����ر��߳�
		if(!PDE.isRegister()){	
			pl.sendMessage(ConfigManager.getLanguage().getNotRegister2());
			return;
		}
		
		
		// ��2��ͬ�����������ȫ���߳����ر��߳�
		if(FUtil.LGD.isGhostPlayer(pl)){			
			FUtil.LGD.kickPlayer(pl);			
			return;
		}
		
		

		try{
		
			
			// ���������ȷ��
			
			if(PlayerPasswordProcess.isRightPassword(PDE, args[0])){
				
				
				/**��¼�ɹ�*/
				
				PDE.setLogin(true);
				playerDataBLL.updatePlayerData(PDE);

				pl.sendMessage(ConfigManager.getLanguage().getLoginSuccess());

				//�����ҵ�¼ǰ��OP �������OPȨ��
				FUtil.LS.loginSuccess_OpSecurityProcess(pl);
				//�����ҵ�¼ǰ�Ǵ���ģʽ ������Ҵ���
				FUtil.LS.loginSuccess_CreativeSecurityProcess(pl);
				
				// �Զ����¼��Ϣ
				lm.ShowMessage(pl);				
				
				//Ϊ����������Ч��
				PEP.addLoginedEffect(pl);
				
				//�Ƴ���ҵ�ʧ��Ч��
				PEP.removePreLoginEffect(pl);
				
				//���isEnable_LoginSecurityΪTrue ����Ҵ��ͻ�������ߵĵط�
				if(ConfigManager.getConfig().isEnable_LoginSecurity()==true){
					pl.teleport(playerDataBLL.getPlayerData(pl).getLastQuitLocation());
				}
				
				
				return;
				
			}
			

			
			pl.sendMessage(ConfigManager.getLanguage().getPasswordError());
			return;

		}catch (Exception e) {
			pl.sendMessage(ConfigManager.getLanguage().getNullPassword());
			return;
		}
		
		
	}

}
