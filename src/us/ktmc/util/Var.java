package us.ktmc.util;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.Location;
import us.ktmc.file.PlayerConf;
import us.ktmc.file.PluginConf;
import us.ktmc.main.FastLogin;
import us.ktmc.main.IO2;
import us.ktmc.main.IO3;
import us.ktmc.main.IODispatch;
import us.ktmc.player.PlayerIPCheck;
import us.ktmc.util_interface.FastSecurity;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;
import us.ktmc.process.*;

/**
 *
 * ��Class��Ϊȫ�ֱ���ʹ�ã�
 *  
 * @author KspTooi
 *
 */
public class Var {
		
	//ȫ������ ��ʼ��
	public static ArrayList<String> NoDamagePlayer=new ArrayList<String>();
		
	public static ArrayList<String> opTables=new ArrayList<String>();
	
	//ȫ�ֹ��� ��ʼ��
	
	public static FastLogin MainClass=null;
	
	public static PlayerConf PlConf=new PlayerConf();
	
	public static final FastUtil Util=new GeneralUtil();
	
	public static IODispatch IO=new IODispatch();
	
	public static PluginConf conf=new PluginConf();
	
	public static IO2 V2=new IO2();
	
	public static IO3 V3=new IO3();
	
	public static ActionHandler AH=new ActionHandler();
	
	public static FastSecurity FS=new FastSecurity();
	
	public static PlayerIPCheck PIP=new PlayerIPCheck();
	
	//ȫ�ֱ��� ��ʼ��

	public static final File FLconf=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
	public static final File OLDConf=new File("plugins/ksptooi/fastlogin/OldFastLogin.conf");
	
	public static final File IPtables=new File("plugins/ksptooi/fastlogin/iptables.gd");
	
	public static String PlayerDataType="GeneralDataCore";
	
	public static int LoginTimeOut=0;
	
	public static int PasswordMaxLength=0;
	
	public static int PasswordMinLength=0;
	
	public static int PlayerNameMinLength=3;
	
	
	public static Location LoginLocation=new Location(null, 0, 0, 0);
	public static String Location_world="empty";
	public static double Location_x=0;
	public static double Location_y=0;
	public static double Location_z=0;
	public static float Location_pitch=0F;
	public static float Location_yaw=0F;
	
	public static int MessageInterval=5;
	
	public static int NoDamageTime=0;
	
	public static boolean Enable_LoginSecurity = false;
	
	public static boolean Enable_UserNameStrictmode=false;
	
	public static String[] BanName={"*"};
	
	public static int MaxRegisterIP=3;
	
	public static String RegexMatchForPlayerName="*";
	
	public static String PlayerLoginedMessage="";
	
	public static String PlayerJoinedMessage="";
	
	public static boolean Enable_OPSecurity=true;
	
	public static boolean Enable_SecurityWarning=true;
	
	
	
}