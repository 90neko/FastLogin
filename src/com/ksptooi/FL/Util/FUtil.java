package com.ksptooi.FL.Util;

import java.util.ArrayList;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.PlayerData.PlayerSqlDataManager;
import com.ksptooi.FL.security.LoginSecurity;
import com.ksptooi.FL.security.RegsterIPCount;

/**
 *
 * ��Class��Ϊȫ�ֱ���ʹ�ã�
 *  
 * @author KspTooi
 *
 */
public class FUtil {
		
	//ȫ�ֱ���
	
//	public static final File fastLoginConfigFile=new File("F:\\1217/MineCraft Server/1.7.10[PT]/plugins/ksptooi/fastlogin/fastlogin.conf");
	
		
	public static PlayerSqlDataManager playerSqlDataBLL=null;
	
	public static LoginSecurity LS=new LoginSecurity();
	
	public static RegsterIPCount RIC=new RegsterIPCount();
	
	
	//ȫ������ ��ʼ��
	public static ArrayList<String> NoDamagePlayer=new ArrayList<String>();
	
	
	//ȫ�ֹ��� ��ʼ��
	public static FastLogin MainClass=null;
	
	
}