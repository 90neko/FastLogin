package us.ktmc.util_interface;

import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.ktmc.Exception.PasswdRulesException;
import us.ktmc.Exception.PlayerNotRegisterException;

/**
 * @author KspTooi
 * 
 * FastLogin��׼�ӿ� V1
 */

public interface FastUtil {
	
	public static final String PlayerDataDir="plugins/ksptooi/fastlogin/database/";

	public static final File Mconf=new File("plugins/ksptooi/fastlogin/FastLogin.conf");
	
	public static final File LanguageFile=new File("plugins/ksptooi/fastlogin/language.gd");
	
	public static final String FastLoginVersion="0.3.6-G-21";
	
	/**
	 * �����ж�������ִ�л����ǿ���ִ̨��
	 * 
	 * @param sender ����ִ����	
	 * @return true ����ִ̨����������� false ���ִ��������
	 */
	public boolean PlayerCommandExecuteAtConsole(CommandSender sender);
	
	/**
	 * ��ȡ��ҵ������ļ�
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return ����һ����������ļ�
	 */
	public File getPlDataFile(Player PlayerEntity);
	
	/**
	 * ��ȡ��ҵ������ļ�
	 * 
	 * @param PlayerEntity �������
	 * @return ����һ����������ļ�
	 */
	public File getPlDataFile(String PlayerName);
	
	/**
	 * �ж���������ļ��Ƿ����
	 * 
	 * @param PlayerEntity ���ʵ�� 
	 * @return ���Ϊtrue������ļ�����
	 */	
	public boolean DataFileIsExists(Player PlayerEntity);
	
	/**
	 * �ж���������ļ��Ƿ����
	 * 
	 * @param PlayerName �������
	 * @return ���Ϊtrue������ļ�����
	 */	
	public boolean DataFileIsExists(String PlayerName);
	
	
	/**
	 * �ж�����Ƿ�ע��
	 * 
	 * @param PlayerEntity ���ʵ�� 
	 * @return ���ΪTrue�������ע��.
	 */	
	public boolean isReg(Player PlayerEntity);
	
	/**
	 * �ж�����Ƿ�ע��
	 * 
	 * @param PlayerName �������
	 * @return ���ΪTrue�������ע��.
	 */	
	public boolean isReg(String PlayerName);
	
	/**
	 * ��ȡ����ļ�ϵͳ����ʱ���ı�
	 * @return �����ı�
	 */
	public String getFileErrorText();
	
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
	public void SetDefaultLoginLocation(String WorldName,int X,int Y,int Z,int Pitch,int Yaw);
	
	
	/**
	 * ɾ�����õ�Ĭ�ϵ�½λ��
	 * @return ����ɹ�ɾ�� ����True ɾ��ʧ�ܷ��� Flase
	 */
	public boolean DeleteDefaultLoginLocation();
	
	/**
	 * OP����������ҵ����룡(�˷�������ͨ���������ù���)
	 * @param PlayerEntity ���ʵ��
	 * @param Passwd Ҫ���õ�����
	 * @throws IOException �ļ�ϵͳ����ʱ�׳����쳣
	 * @throws PasswdRulesException ��������벻����Ҫ��ʱ�׳����쳣��(���糬����󳤶�.)
	 * @throws PlayerNotRegisterException �����δע��ʱ�׳����쳣
	 */
	public void ReSetPasswd(Player PlayerEntity,String Passwd)throws PlayerNotRegisterException,PasswdRulesException,IOException;
	
	/**
	 * OP����������ҵ����룡(�˷�������ͨ���������ù���)
	 * @param PlayerName �������
	 * @param Passwd Ҫ���õ�����
	 * @throws IOException �ļ�ϵͳ����ʱ�׳����쳣
	 * @throws PasswdRulesException ��������벻����Ҫ��ʱ�׳����쳣��(���糬����󳤶�.)
	 * @throws PlayerNotRegisterException �����δע��ʱ�׳����쳣
	 */
	public void ReSetPasswd(String PlayerName,String Passwd)throws PlayerNotRegisterException,PasswdRulesException,IOException;
	
	/**
	 * �����ҵ������Ƿ���Ϲ���
	 * 
	 * @param Passwd Ҫ��������
	 * @throws PasswdRulesException ��������벻����Ҫ��ʱ�׳����쳣��(���糬����󳤶�.)
	 */
	public void PasswdIsAvailable(String Passwd)throws PasswdRulesException;
		
	/**
	 * ����һ��OP�������������̨
	 * @param ����̨ʵ��
	 */
	public void SendOPHelp(CommandSender sender);
	
	/**
	 * ��Ҹ�����ҵ�����
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @param OldPasswd ԭ����
	 * @param NewPasswd ������
	 * @param ConfirmNewPasswd ȷ��������
	 */
	public void ChangePasswd(Player PlayerEntity, String OldPasswd, String NewPasswd,String ConfirmNewPasswd);
	
	/**
	 * �ж��������������Ƿ���ȷ
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @param Passwd Ҫ�жϵ�����
	 */
	public boolean isPassword(Player PlayerEntity,String Passwd);
	
	
	/**
	 * �ж�����Ƿ��¼
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return ����ѵ�¼����True �����δע��ʱ����False ���δ��¼ʱ����False
	 */
	public boolean isLogin(Player PlayerEntity);
	
	/**
	 * �������ע��
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @param Passwd ����
	 * @param ConfirmPasswd ȷ������
	 */
	public void PlayerRegister(Player PlayerEntity,String Passwd,String ConfirmPasswd);
	
	/**
	 * ����Ҵ��͵�Ĭ�ϵ�½λ��
	 * 
	 * @param PlayerEntity ���ʵ��
	 * @return �ɹ����ͷ���True Ĭ�ϵ�½λ�ò����ڷ���False
	 */
	public boolean TelePort_DefaultLoginLocation(Player PlayerEntity);
	
	/**
	 * ������ҵ�¼״̬
	 * 
	 * @param Player ���ʵ��
	 * @param isLogin �����Ƿ��¼ ���ֵΪTrue������ļ�ͬ�����ѵ�¼����δ��¼
	 */
	public void setPlayerLogin(Player Player,Boolean isLogin);
	
	
	/**
	 * ����һ����������ļ�,���ļ��Ѵ���ʱ��ִ���κβ�����
	 * 
	 * @param Player ���ʵ��
	 */
	public void CreatePlayerData(Player Player) throws Exception;
		
	/**
	 * ���ڴ���������� �����Ʋ��Ϸ�ʱֱ���߳����
	 * 
	 * @param Player ���ʵ��
	 * @return ������ƺϷ�����True ���Ϸ�����False��ֱ���߳����
	 */
	public boolean PlayerNameProcess(Player Player);
	
	
	/**
	 * ����ҵ�ǰ��λ�ñ��浽�����ļ� ��Ϊ�������λ��ʹ��
	 * 
	 * @param Player ���ʵ��
	 */
	public void setPlayerQuitLocation(Player Player);
	
	/**
	 * �������ļ���ȡ�������˳���λ��
	 * 
	 * @param Player ���ʵ��
	 * @return �������˳���λ�� ���û��,�򷵻�null
	 */
	public Location getPlayerQuitLocation(Player Player) throws Exception;
	
	/**
	 * ����ҵ�¼����ʾ��Ϣ
	 */
	public void ShowMessage(Player pl);
	
	/**
	 * ���ڻ�ȡ��Ҽ�����Ϣ
	 */
	public String GenJoinedMessage(Player pl);
	
	
	/**
	 * �첽תͬ���߳����
	 */
	public void KickPlayerForBukkit(Player pl,String Message);
	
}
