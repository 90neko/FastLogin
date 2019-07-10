package com.ksptooi.FL.Player.Check;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.FastPlayer;


public class PlayerNameRuleCheck {

	
	/**
	 * �����ж��������
	 * 
	 * @param Player ���ʵ��
	 * @return ������ƺϷ�����True ���Ϸ�����False ���߳����
	 */
	public boolean nameValid(FastPlayer Player) {
		
		
		//�ж�������Ƴ���
		if (Player.getName().length() < ConfigManager.getConfig().getPlayerNameMinLength()) {
			Player.kickPlayer("��������� ��С��Ҫ" + ConfigManager.getConfig().getPlayerNameMinLength() + "���ַ�");
			return false;
		}
		//End

		
		//���Զ���������ʽ�ж�������� Start
		if(!ConfigManager.getConfig().getRegexMatchForPlayerName().equalsIgnoreCase("*")){
			
			if(!Player.getName().matches(ConfigManager.getConfig().getRegexMatchForPlayerName())){
				Player.kickPlayer("������Ʋ�����Ҫ��");			
				return false; 
			}
			
		}	
		//End
		
		
		
		//���ϸ�ģʽ�ж�������� Start
		if (ConfigManager.getConfig().isEnable_UserNameStrictmode()){
			
			String regex = "([A-Z]|[a-z]|[0-9]|-|_){1,}";
	
			if (!Player.getName().matches(regex)) {
				
				Player.kickPlayer("�����������Ҫ��,�����ֻ����A-Z 0-9���»������,���ú��пո����������!");		
				return false; 
				
			}		
		
		}
		//End
	    	    
		//�ж���������Ƿ��б����������õ��ַ�
		for(String str:ConfigManager.getConfig().getBanName()){
			
			if(Player.getName().toLowerCase().contains(str.toLowerCase())){
				
				Player.kickPlayer("��������б����������õĹؼ���:"+str);
				return false;
				
			}
			
		}
		//End
		
		return true;
	}
	
	
}
