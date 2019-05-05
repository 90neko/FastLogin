package us.ktmc.process;

import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Player;
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class ActionHandler {
	
	
	FastUtil Util=Var.Util;
	
	/**
	 * ��ҵ�¼�ɹ��Ժ���е�һϵ�в���
	 * @param pl
	 * @throws Exception
	 */
	public void LoginSuccess(Player pl) throws Exception{
		
		//�����������״̬Ϊ�ѵ�¼
		if(Var.isMysql){
			
			Var.SQL.SQL_setPlayerLogin(pl, true);
			
		}else{
			
			Util.setPlayerLogin(pl, true);
			
		}
			
		
		pl.sendMessage(Language.LoginSuccessful);
		
		
		Var.Util.ShowMessage(pl);
		
		
		//�������ڵ�¼ǰ��OP ��OP�������
		for (int i=0;i<Var.opTables.size();i++) {

			if (pl.getName().equals(Var.opTables.get(i))){		
				pl.setOp(true);
				Var.opTables.remove(i);
			}

		}
			
		
		//��½��������ʱ����Ҵ��ͻ�������ߵص�
		if (Var.Enable_LoginSecurity) {
			
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
		
		
		
	}
	
	
	/**
	 * ���ע��ɹ��Ժ���е�һϵ�в���
	 * @param PlayerEntity
	 */
	public void RegisterSuccess(Player PlayerEntity,String Passwd){
		
		File PlayerConfig=Var.Util.getPlDataFile(PlayerEntity);
		
		
		/** ��������||ע��״̬||��½״̬ -��ʼ**/
		
		
		
		try {
			
			if(Var.isMysql){
				
				Var.SQL.SQL_setPlayerLogin(PlayerEntity, true);
				Var.SQL.SQL_setPlayerReg(PlayerEntity, true);
				Var.SQL.SQL_setPlayerPwd(PlayerEntity, Passwd);
				
			}else{
				
				Var.V3.setTargetFile(PlayerConfig);
				Var.V3.WriteAtKey("password=", Passwd);
				Var.V3.WriteAtKey("register=", "1");
				Var.V3.WriteAtKey("login=", "1");
				
			}

			
		} catch (IOException e) {

			e.printStackTrace();			
			PlayerEntity.sendMessage(Var.Util.getFileErrorText());
			
		}
		
		//��ע��ǰ��OP��������¸���OP
		for(int i=0;i<Var.opTables.size();i++){
			
			if(PlayerEntity.getName().equals(Var.opTables.get(i))){
				PlayerEntity.setOp(true);
				Var.opTables.remove(Var.opTables.remove(i));
			}
			
		}
		
		PlayerEntity.sendMessage(Language.RegisterSuccessful);
		
		
		//����Ҵ��͵�Ĭ�ϵ�½λ��
		Var.Util.TelePort_DefaultLoginLocation(PlayerEntity);
		
		
	}

	
	
		
	
}
