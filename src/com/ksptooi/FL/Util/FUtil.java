package com.ksptooi.FL.Util;

import java.io.File;
import java.util.ArrayList;
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Entity.DefaultLocationEntity;
import com.ksptooi.FL.Entity.FastLoginConfigEntity;
import com.ksptooi.FL.Entity.FastLoginLanguageEntity;
import com.ksptooi.playerData_BLL.PlayerSqlDataBLL;
import com.ksptooi.security.LoginGhostDefense;
import com.ksptooi.security.LoginSecurity;
import com.ksptooi.security.RegsterIPCount;

/**
 *
 * ��Class��Ϊȫ�ֱ���ʹ�ã�
 *  
 * @author KspTooi
 *
 */
public class FUtil {
		
	//ȫ�ֱ���
	public static final String Version="0.41-L-RE";
	
	public static final File fastLoginConfigFile=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
//	public static final File fastLoginConfigFile=new File("F:\\1217/MineCraft Server/1.7.10[PT]/plugins/ksptooi/fastlogin/fastlogin.conf");
	
	public static final File fastLoginLocationFile=new File("plugins/ksptooi/fastlogin/Location.conf");

	public static final File fastLoginIPCountFile=new File("plugins/ksptooi/fastlogin/IPCount.conf");
	
	public static final File fastLoginLanguageFile=new File("plugins/ksptooi/fastlogin/LanguageV1.conf");
	
	public static final String fastLoginPlayerDataFolder="plugins/ksptooi/fastlogin/database/";
		
	public static PlayerSqlDataBLL playerSqlDataBLL=null;
	
	public static LoginSecurity LS=new LoginSecurity();
	
	public static RegsterIPCount RIC=new RegsterIPCount();
	
	public static LoginGhostDefense LGD=new LoginGhostDefense();
	
	//ȫ������ ��ʼ��
	public static ArrayList<String> NoDamagePlayer=new ArrayList<String>();
	
	//�����ļ�ʵ������
	public static FastLoginConfigEntity config=null;

	public static FastLoginLanguageEntity language=null;
	
	public static DefaultLocationEntity defaultLocationEntity=null;
	
	
	//ȫ�ֹ��� ��ʼ��
	public static FastLogin MainClass=null;
	
	
}