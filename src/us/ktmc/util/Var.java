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
	
	//ȫ�ֱ��� ��ʼ��
	
	public static int PasswordMaxlength=0;
	public static int PasswordMinlength=0;
	public static int LoginTimeOut=0;

	public static final File FLconf=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
	public static final File OLDConf=new File("plugins/ksptooi/fastlogin/OldFastLogin.conf");
	
	public static final File IPtables=new File("plugins/ksptooi/fastlogin/iptables.gd");
	
	public static int MinNameLength=3;
	
	public static int MessageInterval=5;
	
	public static Location LoginLocation=new Location(null, 0, 0, 0);
	
	public static String Location_world="empty";
	public static double Location_x=0;
	public static double Location_y=0;
	public static double Location_z=0;
	public static float Location_pitch=0F;
	public static float Location_yaw=0F;
	
	public static int NoDamageTime=0;
	
	public static boolean LocationProtection = false;
	
	public static boolean isStrictmode=false;
	
	public static String[] BanName={"*"};
	
	public static int ipmaxreg=3;
	
	public static String Regex="*";
	
	public static String PlayerLoginedMessage="";
	
	public static String PlayerJoinedMessage="";
	
	
	
}