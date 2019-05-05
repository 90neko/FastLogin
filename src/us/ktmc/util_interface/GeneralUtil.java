package us.ktmc.util_interface;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.Exception.PlayerNotRegisterException;
import us.ktmc.player.PlayerProcessThread;
import us.ktmc.util.Language;
import us.ktmc.util.Var;


public class GeneralUtil implements FastUtil{

	
	
	public String getFileErrorText(){
		return "[����]�������ļ�ϵͳ����!";	
	}
	
	/**
	 * ����һ��OP�������������̨
	 * @param ����̨ʵ��
	 */
	public void SendOPHelp(CommandSender sender){
		
		sender.sendMessage("��b[FastLogin]�������÷�:");
		sender.sendMessage("��e/Fast Reload - ���ز�������ļ�");
		sender.sendMessage("��e/Fast set    - ����һ����¼���͵�");
		sender.sendMessage("��e/Fast go     - ���͵������õĵ�¼���͵�");
		sender.sendMessage("��e/Fast del    - ɾ����Ĵ��͵�");
		sender.sendMessage("��e/Fast reset  - �����趨ĳ��ҵ�����");
		
	}
	
	
	
	/**
	 * �����ж�������ִ�л����ǿ���ִ̨��
	 * 
	 * @param sender ����ִ����
	 * @return true ����ִ̨����������� false ���ִ��������
	 */
	
	public boolean PlayerCommandExecuteAtConsole(CommandSender sender) {
		
		if(!(sender instanceof Player)){
			System.out.println("[FastLogin]������̨����ִ�д�������");
			return true;	
		}
		
		return false;
			
	}

	/**
	 * ��ȡ��ҵ������ļ�
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return ����һ����������ļ�
	 */
	public File getPlDataFile(Player PlayerEntity) {
		return getPlDataFile(PlayerEntity.getName());
	}

	public File getPlDataFile(String PlayerName) {
		return new File(FastUtil.PlayerDataDir+PlayerName.toLowerCase()+".gd");
		
	}

	/**
	 * �ж���������ļ��Ƿ����
	 * 
	 * @param PlayerEntity ���ʵ�� 
	 * @param PlayerName �������
	 * @return ���Ϊtrue������ļ�����
	 */	
	public boolean DataFileIsExists(Player PlayerEntity) {
		return DataFileIsExists(PlayerEntity.getName());
	}

	public boolean DataFileIsExists(String PlayerName) {
		return new File(FastUtil.PlayerDataDir+PlayerName.toLowerCase()+".gd").exists();
	}

	/**
	 * �ж�����Ƿ�ע��
	 * 
	 * @param PlayerEntity ���ʵ�� 
	 * @param PlayerName �������
	 * @return ���ΪTrue�������ע��.
	 */	
	public boolean isReg(Player PlayerEntity) {	
		return isReg(PlayerEntity.getName());
	}

	public boolean isReg(String PlayerName) {
		
		File PlayerConfig=getPlDataFile(PlayerName);
		
		if(!(PlayerConfig.exists())){
			return false;
		}
		
		try{
			
			if(Var.V2.getKeyValue(PlayerConfig, "register=").equalsIgnoreCase("1"))
				return true;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(getFileErrorText());
		}

			
		return false;
	}


	/**
	 * ����һ��Ĭ�ϵ�����λ�á�������ߺ󽫻ᱻ���͵��˴���
	 * @param WorldName ������
	 * @param X X����
	 * @param Y Y����
	 * @param Z Z����
	 * @param Pitch ���峯��
	 * @param Yaw ͷ������
	 * 	
	 */
	public void SetDefaultLoginLocation(String WorldName, int X, int Y, int Z, int Pitch, int Yaw) {
		
		try {
			
			
			Var.V2.WriteAtKey(Mconf,"location.world=", WorldName);
			Var.V2.WriteAtKey(Mconf,"location.x=", ""+X);
			Var.V2.WriteAtKey(Mconf,"location.y=", ""+Y);
			Var.V2.WriteAtKey(Mconf,"location.z=", ""+Z);
			Var.V2.WriteAtKey(Mconf,"location.pitch=", ""+Pitch);
			Var.V2.WriteAtKey(Mconf,"location.yaw=", ""+Yaw);
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(getFileErrorText());
			
		}
				
	}


	/**
	 * ɾ�����õ�Ĭ�ϵ�½λ��
	 * @return ����ɹ�ɾ�� ����True ɾ��ʧ�ܷ��� Flase
	 */
	public boolean DeleteDefaultLoginLocation() {
			
		try{			
	
		Var.V2.WriteAtKey(Mconf,"location.world=","empty");
		Var.V2.WriteAtKey(Mconf,"location.x=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.y=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.z=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.pitch=", "empty");
		Var.V2.WriteAtKey(Mconf,"location.yaw=", "empty");
		return true;
		
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println(getFileErrorText());
			return false;
			
		}
					
	}


	/**
	 * ����������ҵ����룡(�˷�������ͨ���������ù���)
	 * @param PlayerEntity ���ʵ��
	 * @param Passwd Ҫ���õ�����
	 * @throws IOException �ļ�ϵͳ����ʱ�׳����쳣
	 * @throws PasswdRulesException ��������벻����Ҫ��ʱ�׳����쳣��(���糬����󳤶�.)
	 * @throws PlayerNotRegisterException �����δע��ʱ�׳����쳣
	 */
	public void ReSetPasswd(Player PlayerEntity,String Passwd) throws PlayerNotRegisterException, PasswdRulesException, IOException {
		ReSetPasswd(PlayerEntity.getName(),Passwd);
	}



	public void ReSetPasswd(String PlayerName,String Passwd) throws PlayerNotRegisterException, PasswdRulesException, IOException {
		
		if(!(isReg(PlayerName))){
			throw new PlayerNotRegisterException();
		}
		
		PasswdIsAvailable(Passwd);
		
		Var.V2.WriteAtKey(getPlDataFile(PlayerName), "password=", Passwd);
		
	}


	
	/**
	 * �����ҵ������Ƿ���Ϲ���
	 * 
	 * @param Passwd Ҫ��������
	 * @throws PasswdRulesException ��������벻����Ҫ��ʱ�׳����쳣��(���糬����󳤶�.)
	 */
	public void PasswdIsAvailable(String Passwd) throws PasswdRulesException{
		
		if(Passwd.length()>Var.PasswordMaxLength){
			throw new PasswdRulesException(true,false);
		}
		
		if(Passwd.length()<Var.PasswordMinLength){
			throw new PasswdRulesException(false,true);
		}
		
		
	}

	/**
	 * �ж��������������Ƿ���ȷ
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @param Passwd Ҫ�жϵ�����
	 */
	public boolean isPassword(Player PlayerEntity,String Passwd){
		
		File PlConf=getPlDataFile(PlayerEntity);
		
		try {
			
			if(Var.V2.getKeyValue(PlConf, "password=").equals(Passwd)){
				return true;
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			PlayerEntity.sendMessage(getFileErrorText());
			
		}
		
		
		return false;
		
	}
	
	
	/**
	 * ������ҵ�����
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @param OldPasswd ԭ����
	 * @param NewPasswd ������
	 * @param ConfirmNewPasswd ȷ��������
	 */
	public void ChangePasswd(Player PlayerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		//�ж�����ľ������Ƿ���ȷ
		if(!(isPassword(PlayerEntity,OldPasswd))){
			PlayerEntity.sendMessage(Language.ModifyOldpwErr);
			return;
		}
		
		//�ж���������������Ƿ�һ��
		if(!(NewPasswd.equals(ConfirmNewPasswd))){
			PlayerEntity.sendMessage(Language.ModifyConfirmError);
			return;
		}
		
		//�ж��������Ƿ��������һ��
		if(NewPasswd.equalsIgnoreCase(OldPasswd)){
			PlayerEntity.sendMessage(Language.ReModifyPasswd);
			return;
		}
		
		/** �������� - ��ʼ **/
		try {
			
			Var.V2.WriteAtKey(getPlDataFile(PlayerEntity),"password=", NewPasswd);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			PlayerEntity.sendMessage(getFileErrorText());
			
		}
		
		PlayerEntity.sendMessage(Language.ModifySuccessful);
		PlayerEntity.sendMessage(Language.LoginOut);
		
		setPlayerLogin(PlayerEntity, false);
		new Thread(new PlayerProcessThread(PlayerEntity)).start();
		
		/** �������� - ���� **/
	}
	
	
	/**
	 * �ж�����Ƿ��¼
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return ����ѵ�¼����True �����δע��ʱ����False ���δ��¼ʱ����False
	 */
	public boolean isLogin(Player PlayerEntity){
		
		if(!(isReg(PlayerEntity))){
			return false;
		}
		
		try {
			
			if(Var.V2.getKeyValue(getPlDataFile(PlayerEntity), "login=").equalsIgnoreCase("1"))
				return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			PlayerEntity.sendMessage(getFileErrorText());
			
		}
				
		return false;
	
	}



	/**
	 * ����Ҵ��͵�Ĭ�ϵ�½λ��
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return �ɹ����ͷ���True Ĭ�ϵ�½λ�ò����ڷ���False
	 */
	public boolean TelePort_DefaultLoginLocation(Player PlayerEntity) {
		
		if(!(Var.Location_world.equalsIgnoreCase("empty"))){
			
			PlayerEntity.teleport(Var.LoginLocation);
			return true;
			
		}
				
		return false;
	}

	
	/**
	 * ������ҵ�¼״̬
	 * 
	 * @param Player ���ʵ��
	 * @param isLogin �����Ƿ��¼ ���ֵΪTrue������ļ�ͬ�����ѵ�¼����δ��¼
	 */
	public void setPlayerLogin(Player Player, Boolean isLogin) {
		
		try {
			
			if (isLogin) {

				Var.V2.WriteAtKey(getPlDataFile(Player), "login=", "1");
				return;
				
			}
			
			Var.V2.WriteAtKey(getPlDataFile(Player), "login=", "0");

		} catch (Exception e) {
			e.printStackTrace();
			Player.sendMessage(getFileErrorText());

		}
		
	}

	/**
	 * ����һ����������ļ�,���ļ��Ѵ���ʱ��ִ���κβ�����
	 * 
	 * @param Player ���ʵ��
	 * @throws Exception �����ļ�����ʱ�׳����쳣
	 */
	public void CreatePlayerData(Player Player) throws Exception {
		
		File Conf=getPlDataFile(Player);
		
		File Dir=new File("plugins/ksptooi/fastlogin/database/");
		
		Var.V3.setTargetFile(Conf);
		
		if(!Dir.exists()){
			Dir.mkdirs();
		}
		
		if(!(Conf.exists())){
			
			Conf.createNewFile();
					
			System.out.println("[FastLogin]��Ϊ��Ҵ����µ������ļ�:"+Conf.getName());
			
			Var.V2.OutputToFile(Conf, "playername="+Player.getName(), "UTF-8");
			Var.V3.AddtoFile("\r\npassword=password");
			Var.V3.AddtoFile("\r\nregister=0");
			Var.V3.AddtoFile("\r\nlogin=0");
			Var.V3.AddtoFile("\r\nloc.world=empty");
			Var.V3.AddtoFile("\r\nloc.x=empty");
			Var.V3.AddtoFile("\r\nloc.y=empty");
			Var.V3.AddtoFile("\r\nloc.z=empty");
			Var.V3.AddtoFile("\r\nloc.pitch=empty");
			Var.V3.AddtoFile("\r\nloc.yaw=empty");
			
			
		}
		
		setPlayerLogin(Player, false);
		
	}

	/**
	 * ���ڴ���������� �����Ʋ��Ϸ�ʱֱ���߳����
	 * 
	 * @param Player ���ʵ��
	 * @return ������ƺϷ�����True ���Ϸ�����False��ֱ���߳����
	 */
	public boolean PlayerNameProcess(Player Player) {
		
		//�ж�������Ƴ���
		if (Player.getName().length() < Var.PlayerNameMinLength) {
			Player.kickPlayer("��������� ��С��Ҫ" + Var.PlayerNameMinLength + "���ַ�");
			return false;
		}
		//End

		
		//���Զ���������ʽ�ж�������� Start
		if(!Var.RegexMatchForPlayerName.equalsIgnoreCase("*")){
			
			if(!Player.getName().matches(Var.RegexMatchForPlayerName)){
				Player.kickPlayer("������Ʋ�����Ҫ��");			
				return false; 
			}
			
			
		}	
		//End
		
		
		
		//���ϸ�ģʽ�ж�������� Start
		if (Var.Enable_UserNameStrictmode){
			
			String regex = "([A-Z]|[a-z]|[0-9]|-|_){1,}";
	
			if (!Player.getName().matches(regex)) {
				
				Player.kickPlayer("�����������Ҫ��,�����ֻ����A-Z 0-9���»������,���ú��пո����������!");		
				return false; 
				
			}		
		
		}
		//End
	    	    
		//�ж���������Ƿ��б����������õ��ַ�
		for(String str:Var.BanName){
			
			if(Player.getName().toLowerCase().contains(str.toLowerCase())){
				
				Player.kickPlayer("��������б����������õĹؼ���:"+str);
				return false;
				
			}
			
		}
		//End
		
		return true;
	}

	/**
	 * ����ҵ�ǰ��λ�ñ��浽�����ļ� ��Ϊ�������λ��ʹ��
	 * 
	 * @param Player ���ʵ��
	 */
	public void setPlayerQuitLocation(Player Player) {
		
		Location loc=Player.getLocation();
		
		Var.V3.setTargetFile(getPlDataFile(Player));
		
		try {
			
			Var.V3.WriteAtKey("loc.world=", loc.getWorld().getName());
			Var.V3.WriteAtKey("loc.x=", ""+(int)loc.getX());
			Var.V3.WriteAtKey("loc.y=", ""+(int)loc.getY());
			Var.V3.WriteAtKey("loc.z=", ""+(int)loc.getZ());
			Var.V3.WriteAtKey("loc.pitch=", ""+(int)loc.getPitch());
			Var.V3.WriteAtKey("loc.yaw=", ""+(int)loc.getYaw());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println(getFileErrorText());
			
		}
		
	}

	/**
	 * �������ļ���ȡ�������˳���λ��
	 * 
	 * @param Player ���ʵ��
	 * @return �������˳���λ�� ���û��,�򷵻�null
	 * @throws Exception �ļ�ϵͳ����ʱ�׳����쳣
	 */
	public Location getPlayerQuitLocation(Player Player) throws Exception {
		
		Var.V3.setTargetFile(getPlDataFile(Player));
		
		if(Var.V3.getKeyValue("loc.world=").equalsIgnoreCase("empty")){
			return null;	
		}
		
		Location loc=new Location(null, 0, 0, 0);
		
		loc.setWorld(Bukkit.getWorld(Var.V3.getKeyValue("loc.world=")));
		loc.setX(new Integer(Var.V3.getKeyValue("loc.x=")));
		loc.setY(new Integer(Var.V3.getKeyValue("loc.y=")));
		loc.setZ(new Integer(Var.V3.getKeyValue("loc.z=")));
		loc.setPitch(new Integer(Var.V3.getKeyValue("loc.pitch=")));
		loc.setYaw(new Integer(Var.V3.getKeyValue("loc.yaw=")));
		
		return loc;
	}

	
	
	/**
	 * ������ҵ�¼�����Ϣ����
	 */
	public void ShowMessage(Player pl) {
		
		
		String ShowMessage="";
		
		//�ж��Ƿ������˵�¼��Ϣ
		if(Var.PlayerLoginedMessage.equalsIgnoreCase("false")){
			return;
		}
		
		//���ɵ�¼��Ϣ
		ShowMessage=Var.PlayerLoginedMessage;
		
		ShowMessage=ShowMessage.replace("%Player%", pl.getName());
		
		ShowMessage=ShowMessage.replace("&", "��");
		
		ShowMessage=ShowMessage.replace("#", "\n");
		
		
		//���͵�¼��Ϣ
		pl.sendMessage(ShowMessage);
		
		
	}

	/**
	 * ���ڻ�ȡ��Ҽ�����Ϣ
	 */
	@Override
	public String GenJoinedMessage(Player pl) {

		String str=Var.PlayerJoinedMessage;
		
		//������Ϣ
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "��");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}


	/**
	 * ���ڻ�ȡ����˳���Ϣ
	 */
	@Override
	public String GenQuitMessage(Player pl) {

		String str=Var.PlayerQuitMessage;
		
		//������Ϣ
		str=str.replace("%Player%", pl.getName());
		
		str=str.replace("&", "��");
		
		str=str.replace("#", "\n");
		
		
		return str;
	}
	
	
	

}
