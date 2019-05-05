package com.ksptooi.FL.playerEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import com.ksptooi.FL.Entity.PlayerDataEntity;
import com.ksptooi.FL.LogFitter.PlayerPasswordLogFitter;
import com.ksptooi.FL.PDEContainer.PDECManager;
import com.ksptooi.FL.PlayerProcess.PlayerEffectProcess;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.PlayerProcess.PlayerNameProcess;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.LogManager;
import com.ksptooi.FL.playerThread.AdvPlayerLoginThread;
import com.ksptooi.FL.playerThread.AdvPlayerRegThread;
import com.ksptooi.FL.playerThread.PlayerLoginMessageSendThread;
import com.ksptooi.playerData_BLL.PlayerDataBLL_Interface;
import com.ksptooi.playerData_BLL.PlayerDataBLLimpl;
import com.ksptooi.security.PlayerFilter;

/**P.E.H**/
public class PlayerEventHandler implements Listener{

	LogManager lm=null;
	PlayerDataBLL_Interface PDB=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerNameProcess playerNameProcess=null;
	PlayerFilter PF=null;
	LogManager logManager=null;
	PlayerEffectProcess PEP=null;
	
	public PlayerEventHandler(){
		lm=new LogManager();
		PDB=new PlayerDataBLLimpl();
		playerLocationProcess=new PlayerLocationProcess();
		playerNameProcess=new PlayerNameProcess();
		PF=new PlayerFilter();
		logManager = new LogManager();
		PEP = new PlayerEffectProcess();
	}
	
	
	/**�첽��¼ - ��ʼ**/
	@EventHandler(priority = EventPriority.MONITOR)		
	public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event){
		
		String PlayerName=event.getName().toLowerCase();
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
	
            if(pl.getName().toLowerCase().equalsIgnoreCase(PlayerName)){
                event.setLoginResult(Result.KICK_OTHER);
                event.setKickMessage(FUtil.language.getJoinGameError1());
            }                           
		}
		
	}
		
	//��Ҽ����¼�
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	
		Player pl = event.getPlayer();
		PlayerDataEntity PDE=null;
		
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer(pl)){
			return;
		}
		
		//������IPδע��� ������IP��ӽ�IPCount
		
		//���������ݻ���
		PDECManager.removePDE(pl.getName());
		
		
		//���������
//		if(!PF.playerNameisAllow(pl.getName())){
//			
//			
//			pl.kickPlayer("��ʹ������:"+PF.findPlayerName(pl.getName())+"���������!");
//			
//		}
		
		
		//��ʼ���������
		PDB.createPlayerData(pl.getName());
		
		PDE=PDB.getPlayerData(pl.getName());
		
		PDE.setLogin(false);
		
		PDB.updatePlayerData(PDE);
		
		event.setJoinMessage(lm.GenJoinedMessage(pl));
		
		playerLocationProcess.TelePort_DefaultLoginLocation(pl);
		
		
		//OP��ȫ���
		FUtil.LS.joinServer_OpSecurityProcess(pl);
		//���찲ȫ���
		FUtil.LS.joinServer_CreativeSecurityProcess(pl);
		
		
		//������Ƽ��
		if(! playerNameProcess.playerNameIsAccess(pl)){
			return;
		}
		
		//Ϊ������ʧ��Ч��
		PEP.addPreLoginEffect(pl);
		
		
		
		//ȫ��ͨ������һ����ҵ�¼����߳�
		new Thread(new PlayerLoginMessageSendThread(pl)).start();

	}
		

	//�ƶ�	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer(event.getPlayer())){
			return;
		}
		
		//�ж��Ƿ�������
		if(event.getFrom().getY()>event.getTo().getY()){
			return ;
		}
		
		//�ж��Ƿ��ƶ���λ��
		if(event.getFrom().getX()==event.getTo().getX()){
			return ;
		}
		
		if(event.getFrom().getZ()==event.getTo().getZ()){
			return ;
		}
		
		
		PlayerDataEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.getPlayer().teleport(event.getFrom());
		}
		

		
	}
	
	//˵��
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		
		PlayerDataEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		if(!PDE.isLogin()){	
			event.setCancelled(true);
		}
		
		
	}
	
	//����
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer(event.getPlayer())){
			return;
		}
		
		PlayerDataEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}

		
	}
	
	//����
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		//�����������Ϸ����ֹ����		
		
	}
	
	//����
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event){

		Bukkit.getLogger().setFilter(new PlayerPasswordLogFitter());
		
		String[] Commands=null;

		//����Ԥ����
		try{
			Commands = event.getMessage().split(" ");

			for(int i=0;i<Commands.length;i++){
				Commands[i]=Commands[i].replace(" ", "");
			}
			
		}catch(Exception e){
		}
		
		Bukkit.getLogger().setFilter(null);
		
		//��¼
		if(Commands[0].equalsIgnoreCase("/login")|Commands[0].equalsIgnoreCase("/l")){
			
			logManager.writerInfo("���:"+event.getPlayer().getName()+"���ڳ��Ե�¼");
			
			new Thread(new AdvPlayerLoginThread(event.getPlayer(),Commands)).start();
			
			
			event.setCancelled(true);
			return;
		}
		
		//ע��
		if(Commands[0].equalsIgnoreCase("/register")|Commands[0].equalsIgnoreCase("/reg")){
			
			logManager.writerInfo("���:"+event.getPlayer().getName()+"���ڳ���ע��");
			
			new Thread(new AdvPlayerRegThread(event.getPlayer(),Commands)).start();
			
			
			event.setCancelled(true);
			return;
		}
		
		
		PlayerDataEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);   
		}
		
		
	}
	
	//������(Damage)
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		
		//��֤�Ƿ�ΪPlayerEntity
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer((Player)event.getEntity())){
			return;
		}
		
		
		  
		Player pl=(Player)event.getEntity();
		
		
		
		PlayerDataEntity PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
	
		
	}
	
	//���˺�
	@EventHandler
	public void onEntityDamage1(EntityDamageByBlockEvent event){
		
				
		//��֤�Ƿ�ΪPlayerEntity
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer((Player)event.getEntity())){
			return;
		}
		
		Player pl=(Player)event.getEntity();
		
		
		PlayerDataEntity PDE = null;
		
		try{
			
			PDE = PDB.getPlayerData(pl);
			
		}catch(Exception e){
			event.setCancelled(true);
			return;
		}
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		
		
	}
	/****/
	
	
	//����
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		
		//��֤�Ƿ�ΪPlayer
		if(!(event.getDamager().getType() == EntityType.PLAYER)){
			return;
		}
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer((Player)event.getDamager())){
			return;
		}

		Player pl=(Player)event.getDamager();
		
		
		
		PlayerDataEntity PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		

		
	}
	
	//�������
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
			
		Player pl=(Player)event.getWhoClicked();
		

		
		PlayerDataEntity PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			pl.closeInventory();
			event.setCancelled(true);
		}
		
		
	}
	
	
	
	
	//����
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
			
		//��֤�Ƿ�ΪrealPlayer
		if(!(event.getPlayer().getType() == EntityType.PLAYER)){
			return;
		}
		
		
		PlayerDataEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		
		
	}
	

	//�˳�
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {

		event.setQuitMessage(lm.GenQuitMessage(event.getPlayer()));
		
		
		if (!playerNameProcess.playerNameIsAccess(event.getPlayer())) {
			return;
		}

		
		PlayerDataEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		//����Ѿ���¼�򱣴�һ���û���λ��
		if(PDE.isLogin()){
			PDE.setLastQuitLocation(event.getPlayer().getLocation());
		}
		
		
		//���û�����Ϊδ��¼״̬
		PDE.setLogin(false);
		
		//�����û�GD�ļ�
		PDB.updatePlayerData(PDE);
		
		//������
		PDECManager.removePDE(event.getPlayer().getName());

	}
	
	
	
	
}
