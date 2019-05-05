package us.ktmc.player;

import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class PlayerRegThread implements Runnable{

	
	Player PlayerEntity=null;	
	String[] args=null;	

	
	public PlayerRegThread(Player PlayerEntity,String[] args){
		
		this.PlayerEntity=PlayerEntity;		
		this.args=args;			

		
	}
	
	public void run() {
		
			
		try{		
			
			FastUtil Util=Var.Util;
			String Passwd=null;
			String ConfirmPasswd=null;
			
			
			//���ע������Ƿ�Ϸ�
			if(args.length < 2){
				PlayerEntity.sendMessage(Language.NoConfirmPasswd);
				return ;
			}
			
			Passwd=args[0];
			ConfirmPasswd=args[1];


			//�ж��Ƿ���ע��
			
			
			if(Var.isMysql){
				
				if(Var.SQL.SQL_isReg(PlayerEntity)){		
					PlayerEntity.sendMessage(Language.RepeatRegister);
					return;
				}
				
				
			}else{
				
				if(Util.isReg(PlayerEntity)){		
					PlayerEntity.sendMessage(Language.RepeatRegister);
					return;
				}	
				
			}
			
			
			
			
			//�ж�ip�Ƿ��Ѿ��ﵽ�޶�
			if(Var.PIP.isMaxRegIP(PlayerEntity)){
				PlayerEntity.sendMessage("ÿ��IP���ֻ��ע��"+Var.MaxRegisterIP+"���˺�,���ѳ����޶");
				return ;
			}
			
			//������볤��
			try {
				
				Util.PasswdIsAvailable(Passwd);
				
			} catch (PasswdRulesException e) {
				
				if(e.isTooLong()){
					PlayerEntity.sendMessage(Language.PassMax);
					return;
				}
				
				if(e.isTooShort()){			
					PlayerEntity.sendMessage(Language.PassMin);
					return;				
				}
			}
			
			//���������ȷ�������Ƿ�һ��
			if(!(Passwd.equals(ConfirmPasswd))){
				PlayerEntity.sendMessage(Language.ConfirmPasswdError);
				return ;
			}
			
			
			/**
			 * 1.���ע������Ƿ�Ϸ�
			 * 2.�ж��Ƿ��Ѿ�ע��
			 * 3.�ж�IP�Ƿ�ﵽ�޶�
			 * 4.������볤��
			 * 5.���������ȷ�������Ƿ�һ��
			 * 6.����ע��������
			 */
			
			
			//����ע��������
			Var.AH.RegisterSuccess(PlayerEntity,Passwd);
			
	
			
			return;
			
			
			
		} catch (Exception e){
			
			PlayerEntity.sendMessage(Language.NullPassword);
			return;
			
		}
		
		
		
		
	}

	
	
}
