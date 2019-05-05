package com.ksptooi.FL.PlayerProcess;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Util.FUtil;


public class PlayerNameProcess {

	
	/**
	 * �����ж��������
	 * 
	 * @param Player ���ʵ��
	 * @return ������ƺϷ�����True ���Ϸ�����False ���߳����
	 */
	public boolean playerNameIsAccess(Player Player) {
		
		
		//�ж�������Ƴ���
		if (Player.getName().length() < FUtil.config.getPlayerNameMinLength()) {
			Player.kickPlayer("��������� ��С��Ҫ" + FUtil.config.getPlayerNameMinLength() + "���ַ�");
			return false;
		}
		//End

		
		//���Զ���������ʽ�ж�������� Start
		if(!FUtil.config.getRegexMatchForPlayerName().equalsIgnoreCase("*")){
			
			if(!Player.getName().matches(FUtil.config.getRegexMatchForPlayerName())){
				Player.kickPlayer("������Ʋ�����Ҫ��");			
				return false; 
			}
			
		}	
		//End
		
		
		
		//���ϸ�ģʽ�ж�������� Start
		if (FUtil.config.isEnable_UserNameStrictmode()){
			
			String regex = "([A-Z]|[a-z]|[0-9]|-|_){1,}";
	
			if (!Player.getName().matches(regex)) {
				
				Player.kickPlayer("�����������Ҫ��,�����ֻ����A-Z 0-9���»������,���ú��пո����������!");		
				return false; 
				
			}		
		
		}
		//End
	    	    
		//�ж���������Ƿ��б����������õ��ַ�
		for(String str:FUtil.config.getBanName()){
			
			if(Player.getName().toLowerCase().contains(str.toLowerCase())){
				
				Player.kickPlayer("��������б����������õĹؼ���:"+str);
				return false;
				
			}
			
		}
		//End
		
		return true;
	}
	
	
}
