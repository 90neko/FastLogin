package com.ksptooi.FL.Data.Player.Entity;

import java.net.InetSocketAddress;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Hash.PasswordHash;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Event.FastEvent.PlayerTelemetryEvent;
import com.ksptooi.FL.PAsync.Task.AsyncTask;
import com.ksptooi.FL.Player.Check.PlayerPasswordRuleCheck;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.Util.Logger;

public class FastPlayer{

	
	private Player bukkitPlayer = null;
	
	private PlayerData playerData = null;
	
	private PlayerDataManager playerDataManager = null;
	
	private AsyncTask asyncTask = null;
	
	private PlayerEffectManager effectManager = null;
	
	private PasswordHash passwordHash = null;
	
	
	public FastPlayer(Player pl) {
			
		this.bukkitPlayer = pl;
		
		this.playerDataManager = DataManager.getPlayerDataManager();		
		
		this.asyncTask = FastLogin.getAsyncTask();
		
		this.effectManager = FastLogin.getPlayerEffectManager();
		
		this.passwordHash = DataManager.getAdvPasswordHash();
		
		
		//��ȡ����	
		playerData = playerDataManager.getPlayerData(pl);
		
	}
	
	
	
	//������������ļ�(���������û�в���)
	public void createData() {		
		
		playerDataManager.createPlayerData(bukkitPlayer.getName());
		
	}
	
	//���ļ�/���� ˢ����ҵ������ļ�
	public void reload() {
		playerData = playerDataManager.getPlayerData(bukkitPlayer);
	}
	
	
	//������������ļ����ļ�ϵͳ
	public void save() {		
		playerDataManager.updatePlayerData(playerData);		
	}
	
	
	//������ҵ�����˳�λ��
	public void saveQuitLocation() {
				
		playerData.setLastQuitLocation(bukkitPlayer.getLocation());		
		
	}
	
	//���õ�½״̬
	public void setLogin(boolean bool) {
		this.playerData.setLogin(bool);
	}
		
	//��������
	public void setPassword(String str) {
		this.playerData.setPassword(str);
	}
	
	//����ע��״̬
	public void setRegister(boolean bool) {
		this.playerData.setRegister(bool);
	}
	
	//��ӵ�½����Ч����
	public void addLoginedEffect() {
		this.effectManager.addLoginedEffect(bukkitPlayer);
	}
	
	//��ȡ����
	public String getPassword() {
		this.reload();
		return this.playerData.getPassword();
	}
	
	
	//��ӵ�½ʧ��Ч��
	public void addPreLoginEffect() {
		this.effectManager.addPreLoginEffect(bukkitPlayer);
	}
	
	//�Ƴ���½ʧ��Ч��
	public void removePreLoginEffect() {
		this.effectManager.removePreLoginEffect(bukkitPlayer);
	}
	
	
	
	public boolean isLogin() {
		this.reload();
		return playerData.isLogin();
	}
	
	public boolean isRegister() {
		this.reload();
		return playerData.isRegister();
	}
	
	
	
	//������ҵ��������趨��Ĭ�ϵ�½λ��
	public boolean tpFastSpawn(){
		
		
		if(! ConfigManager.getLocation().getLocation_world().equalsIgnoreCase("empty")){
			
			asyncTask.taskTp(bukkitPlayer, ConfigManager.getLocation().getLoginLocation());
					
			return true;
			
		}	
		
		return false;
		
	}
	
	//������ҵ�������ߵĵط�
	public void tpLastQuitLocation() {
		
		this.asyncTask.taskTp(bukkitPlayer, playerData.getLastQuitLocation());
		
	}
	
	
	//�ж��Ƿ�ΪӰ����
	public boolean isGhostPlayer(){
		
		int i=0;

		
		for(Player p:Bukkit.getServer().getOnlinePlayers()){
			
			if(p.getName().equalsIgnoreCase(bukkitPlayer.getName())){
				i++;
			}
			
		}
		
		
		if(i>1){
			return true;
		}
		
		return false;
		
	}
	
	
	//У����ҵ�����
	public boolean isRightPassword(String password){
			
		String Hash = ConfigManager.getConfig().getEnable_passwordHash();		
		
		Boolean isSupportOldpwd = ConfigManager.getConfig().isEnable_SupportOldPassword();
		
		String SaltPassword = password;
		
		Logger logger = FastLogin.getLoggerr();
		
			
		//ʹ��MD5
		if(Hash.equalsIgnoreCase("MD5")){
			
			logger.DM("ʹ��MD5����");
						
			
			if(passwordHash.autoCompression(SaltPassword).equals(playerData.getPassword())){
				logger.DM("������ȷ");
				return true;
			}
			
			
			//�ж��Ƿ�֧�־�����
			if(! isSupportOldpwd){
				logger.DM("�������");
				return false;
			}
			
			logger.DM("֧��ʹ�þ�����");
			
			
			//���þ�����֧��
			if(password.equals(playerData.getPassword())){
				logger.DM("��������ȷ");			
				this.updatePlayerPassword(playerData,SaltPassword);
				return true;
				
			}
			
			logger.DM("���������");		
			return false;
		}

		
		
		//û�м����㷨 - false & Other	
		if(SaltPassword.equals(playerData.getPassword())){
			return true;
		}
		
		return false;	
			
		
	}
	
	
	/**����������ҵľ�����**/
	public void updatePlayerPassword(PlayerData PDE,String Password){
				
		PDE.setPassword(passwordHash.autoCompression(Password));
		this.save();

	}
	
	
	/**
	 * ������ҵ�����
	 * 
	 * @param PlayerData ���ʵ��
	 * @param OldPasswd ԭ����
	 * @param NewPasswd ������
	 * @param ConfirmNewPasswd ȷ��������
	 */
	public void ChangePasswd(String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		PlayerPasswordRuleCheck passwordRuleCheck = DataManager.getPlayerPasswordRuleCheck();
		
		//�ж����޼���
		if(ConfigManager.getConfig().getEnable_passwordHash().equalsIgnoreCase("md5")){
			this.ChangePasswdMD5(OldPasswd, NewPasswd, ConfirmNewPasswd);
			return;
		}	
		
		this.reload();
			
		//����������
		if(!passwordRuleCheck.isValid(this, OldPasswd, NewPasswd, ConfirmNewPasswd)) {
			return;
		}
		

			
		/** �������� - ��ʼ **/
		this.setPassword(NewPasswd);
		this.setLogin(false);
		
		this.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		this.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//ͬ�������ݿ�
		this.save();
		
		
		PlayerTelemetryEvent event=new PlayerTelemetryEvent(this);
		FastLogin.getEventManager().runFastEvent(event);
		
	}

	
	/**
	 * ������ҵ����� - ��������
	 */
	
	public void ChangePasswdMD5(String OldPasswd, String NewPasswd,String ConfirmNewPasswd) {
		
		PlayerPasswordRuleCheck passwordRuleCheck = DataManager.getPlayerPasswordRuleCheck();
		
		this.reload();
		
		//����������
		if(!passwordRuleCheck.isValidMD5(this, OldPasswd, NewPasswd, ConfirmNewPasswd)) {
			return;
		}
		
		/** �������� - ��ʼ **/
		
		this.setPassword(passwordHash.autoCompression(NewPasswd));
		this.setLogin(false);
		
		this.sendMessage(ConfigManager.getLanguage().getChangePw_Success());
		this.sendMessage(ConfigManager.getLanguage().getLoginOut());
		
		//ͬ�������ݿ�
		this.save();
			
		PlayerTelemetryEvent event=new PlayerTelemetryEvent(this);
		FastLogin.getEventManager().runFastEvent(event);
		
	}
	
	
	//�ж�����Ƿ�Ϊ������ ����������ʵ��
	public boolean isRealPlayer(){
		
		String Address=null;
		
		try{
			
			Address=this.getAddress().getHostName();
			
			if(Address == null) {
				return false;
			}
			
			return true;
			
			
		}catch(Exception exc){
			return false;
		}
			
	}
	
	
	
	/**
	 * 
	 * BUKKIT�ķ�����װ
	 * 
	 */
	
	
	//��ȡLocation
	public Location getLocation() {
		return bukkitPlayer.getLocation();
	}
	
	
	//�Ƿ���OP
	public boolean isOp() {
		return bukkitPlayer.isOp();
	}
	
	
	//�Ƿ�����
	public boolean isOnline() {
		return bukkitPlayer.isOnline();
	}
	
	//��ȡ����
	public String getName() {
		return bukkitPlayer.getName();
	}
	
	//��ȡ��ַ
	public InetSocketAddress getAddress() {
		return bukkitPlayer.getAddress();
	}
	
	
	//�����ҷ���һ����Ϣ(�������첽)
	public void sendMessage(String str) {
		this.asyncTask.taskMessage(bukkitPlayer, str);
	}
	
	//�߳������(�������첽)
	public void kickPlayer(String message) {
		this.asyncTask.taskKick(bukkitPlayer, message);
	}
	
	//����OP(�������첽)
	public void setOp(boolean bool) {
		this.asyncTask.taskSetOP(bukkitPlayer, bool);
	}
	
	//��ȡ��Ϸģʽ
	public GameMode getGameMode() {
		return bukkitPlayer.getGameMode();
	}
	
	//������Ϸģʽ
	public void setGameMode(int i) {
		this.asyncTask.taskSetGameMode(bukkitPlayer,i);
	}
	
}
