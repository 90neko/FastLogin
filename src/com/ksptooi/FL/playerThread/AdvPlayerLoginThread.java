package com.ksptooi.FL.playerThread;

import org.bukkit.entity.Player;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.FL.PlayerProcess.*;

public class AdvPlayerLoginThread implements Runnable{

	Player pl=null;
	String[] args=null;
	PlayerDataBLL_Interface playerDataBLL=null;
	LogManager lm=null;
	PlayerEffectProcess PEP=null;
	PlayerPasswordProcess PlayerPasswordProcess = null;

	
	public AdvPlayerLoginThread(Player pl,String[] args){
		
		this.args=args;
		this.pl=pl;
		lm=new LogManager();
		playerDataBLL=new PlayerDataBLLimpl();
		PEP = new PlayerEffectProcess();
		PlayerPasswordProcess = new PlayerPasswordProcess();
	}
	
	public void run() {
		
		PlayerDataEntity PDE = playerDataBLL.getPlayerData(pl);
		
		//�ѵ�¼��ر��߳�
		if (PDE.isLogin()) {		
			pl.sendMessage(FUtil.language.getRepeatLogin());
			return;
		}
		
		
		// δע����ر��߳�
		if(!PDE.isRegister()){	
			pl.sendMessage(FUtil.language.getNotRegister2());
			return;
		}
		
		
		// ��2��ͬ�����������ȫ���߳����ر��߳�
		if(FUtil.LGD.isGhostPlayer(pl)){
			
			FUtil.LGD.kickPlayer(pl);
			
			return;
		}
		
		

		try{
		
			
			// ���������ȷ��
			
			if(PlayerPasswordProcess.isRightPassword(PDE, args[1])){
				
				
				/**��¼�ɹ�*/
				
				PDE.setLogin(true);
				playerDataBLL.updatePlayerData(PDE);

				pl.sendMessage(FUtil.language.getLoginSuccess());

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
				if(FUtil.config.isEnable_LoginSecurity()==true){
					pl.teleport(playerDataBLL.getPlayerData(pl).getLastQuitLocation());
				}
				
				
				return;
				
			}
			

			
			pl.sendMessage(FUtil.language.getPasswordError());
			return;

		}catch (Exception e) {
			pl.sendMessage(FUtil.language.getNullPassword());
			return;
		}
		
		
	}

}
