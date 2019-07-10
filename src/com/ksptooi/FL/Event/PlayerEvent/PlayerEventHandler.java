package com.ksptooi.FL.Event.PlayerEvent;

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
import com.ksptooi.FL.BukkitSupport.FastLogin;
import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Data.PlayerData.PlayerDataManager;
import com.ksptooi.FL.Data.PlayerData.PlayerData_Interface;
import com.ksptooi.FL.Player.Effect.PlayerEffectManager;
import com.ksptooi.FL.PlayerProcess.PlayerLocationProcess;
import com.ksptooi.FL.PlayerProcess.PlayerNameProcess;
import com.ksptooi.FL.Thread.Player.PlayerLoginMessageSendThread;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.FL.security.PlayerFilter;

/**P.E.H**/
public class PlayerEventHandler implements Listener{

	Logger lm=null;
	PlayerData_Interface PDB=null;
	PlayerLocationProcess playerLocationProcess=null;
	PlayerNameProcess playerNameProcess=null;
	PlayerFilter PF=null;
	Logger logManager=null;
	PlayerEffectManager PEP=null;
	
	public PlayerEventHandler(){
		lm=new Logger();
		PDB=new PlayerDataManager();
		playerLocationProcess=new PlayerLocationProcess();
		playerNameProcess=new PlayerNameProcess();
		PF=new PlayerFilter();
		logManager = new Logger();
		PEP = new PlayerEffectManager();
	}
	
	
	/**�첽��¼ - ��ʼ**/
	@EventHandler(priority = EventPriority.MONITOR)		
	public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event){
		
		String PlayerName=event.getName().toLowerCase();
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
	
            if(pl.getName().toLowerCase().equalsIgnoreCase(PlayerName)){
                event.setLoginResult(Result.KICK_OTHER);
                event.setKickMessage(ConfigManager.getLanguage().getJoinGameError1());
            }
            
		}
		
	}
		
	//��Ҽ����¼�
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	
		Player pl = event.getPlayer();
		PlayerEntity PDE=null;
		
		
		//��֤�Ƿ�ΪrealPlayer
		if(!PF.isRealPlayer(pl)){
			return;
		}
		

		
		//���������ݻ���
		PlayerDataCache.removePDE(pl.getName());
		
			
		
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
		
		//���Online�б�
		FastLogin.addOnlinePlayer(pl.getName(), pl);
		
		
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
		
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
		if(!PDE.isLogin()){
			event.getPlayer().teleport(event.getFrom());
		}
		

		
	}
	
	//˵��
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
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
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
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

		String message = event.getMessage().toLowerCase();
		
		if(message.contains("/l")||message.contains("/login")||message.contains("/register")||message.contains("/reg")) {
			return;
		}	
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
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
		
		
		
		PlayerEntity PDE = PDB.getPlayerData(pl);
		
		
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
		
		
		PlayerEntity PDE = null;
		
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
		
		
		
		PlayerEntity PDE = PDB.getPlayerData(pl);
		
		
		if(!PDE.isLogin()){
			event.setCancelled(true);
		}
		

		
	}
	
	//�������
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
			
		Player pl=(Player)event.getWhoClicked();
		

		
		PlayerEntity PDE = PDB.getPlayerData(pl);
		
		
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
		
		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		
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

		
		PlayerEntity PDE = PDB.getPlayerData(event.getPlayer());
		
		//����Ѿ���¼�򱣴�һ���û���λ��
		if(PDE.isLogin()){
			PDE.setLastQuitLocation(event.getPlayer().getLocation());
		}
		
		//�Ƴ�Online�б�
		FastLogin.removeOnlinePlayer(event.getPlayer().getName());
		
		//���û�����Ϊδ��¼״̬
		PDE.setLogin(false);
		
		//�����û�GD�ļ�
		PDB.updatePlayerData(PDE);
		
		//������
		PlayerDataCache.removePDE(event.getPlayer().getName());

	}
	
	
	
	
}
