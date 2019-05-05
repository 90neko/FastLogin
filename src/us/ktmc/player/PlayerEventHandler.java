package us.ktmc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
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
import us.ktmc.util.Language;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;
import us.ktmc.util_interface.GeneralUtil;

/**P.E.P**/
public class PlayerEventHandler implements Listener{

	FastUtil Util=new GeneralUtil();
	
	/**�첽��¼ - ��ʼ**/
	@EventHandler(priority = EventPriority.MONITOR)		
	public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event){
		
		String PlayerName=event.getName().toLowerCase();
		
		for(Player pl:Bukkit.getServer().getOnlinePlayers()){   		
	
            if(pl.getName().toLowerCase().equalsIgnoreCase(PlayerName)){
                event.setLoginResult(Result.KICK_OTHER);
                event.setKickMessage(Language.JoinGameError1);
            }                                   
		}
		
	}
		
	//��¼
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
	
		try {
			
			Player pl = event.getPlayer();
						
			
			Util.CreatePlayerData(pl);
			
			Util.setPlayerLogin(pl, false);

			Util.TelePort_DefaultLoginLocation(pl);

			event.setJoinMessage(Var.Util.GenJoinedMessage(pl));
			
			if (pl.isOp()) {
				Var.opTables.add(pl.getName());
				pl.setOp(false);
			}

			if (!Util.PlayerNameProcess(pl)) {
				return;
			}
			
			new Thread(new PlayerProcessThread(pl)).start();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println(Util.getFileErrorText());

		}

	}
		
	
	//�ƶ�	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		
		//��ʼ������֤
		if(!(event.getPlayer().getType() == EntityType.PLAYER)){
			return;
		}
		
		
		//End
		
		//��ʼ����У�� 
		if(!Util.isLogin(event.getPlayer())){
			
			if(event.getFrom().getY()>event.getTo().getY()){
				return ;
			}
			
		
			event.getPlayer().teleport(event.getFrom());
		
		}
		
	}
	
	//˵��
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		
		if(Var.PlConf.isLogin(event.getPlayer())){
			return ;
		}	
							
		
		event.setCancelled(true);
		
	}
	
	//����
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
		if(Var.PlConf.isLogin(event.getPlayer())){
			return ;
		}	
		event.setCancelled(true);
		
	}
	//����
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event){
		
		if(!Util.PlayerNameProcess(event.getPlayer())){
			return;
		}	
		
		
//		if(!Util.isLogin(event.getPlayer())){
//			
//			if(Var.LocationProtection){
//				return ;
//			}
//			
//			event.setCancelled(true);
//		}	
		
		
	}
	//����
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event){
		

		if(event.getMessage().toLowerCase().contains("/login")|event.getMessage().toLowerCase().contains("/register")|event.getMessage().toLowerCase().contains("/l")|event.getMessage().toLowerCase().contains("/reg")){
			return;
		}
		
		if(Var.PlConf.isLogin(event.getPlayer())){
			return ;
		}	
		event.setCancelled(true);
		
	}
	
	//���˺�
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event){
		
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		  
		Player pl=(Player)event.getEntity();
		
		
//		for(int i=0;i<Var.NoDamagePlayer.size();i++){
//			if(pl.getName().equalsIgnoreCase(Var.NoDamagePlayer.get(i))){
//				event.setCancelled(true);
//				return ;
//			}
//			
//		}
			
		
		if(Var.PlConf.isLogin(pl)){
			return ;
		}	
	
		event.setCancelled(true);
		
	}
	
	
	
	//����
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
		
		
		if(!(event.getDamager().getType() == EntityType.PLAYER)){
			return;
		}

		Player pl=(Player)event.getDamager();
		
		if(Util.isLogin(pl)){
			return ;
		}
	
		event.setCancelled(true);
		
	}
	
	//�������
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
			
		if(Util.isLogin((Player)event.getWhoClicked())){
			return ;
		}	
		
		event.setCancelled(true);
		
	}
	
	
	
	//����
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event){
			
		if(Var.PlConf.isLogin(event.getPlayer())){
			return ;
		}	
	
		event.setCancelled(true);
		
	}
	

	//�˳�
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {

		Var.NoDamagePlayer.remove(event.getPlayer().getName());

		
		if (!Util.PlayerNameProcess(event.getPlayer())) {
			return;
		}

		
		if (Util.isLogin(event.getPlayer())) {

			Util.setPlayerQuitLocation(event.getPlayer());

		}

		Util.setPlayerLogin(event.getPlayer(), false);
		

	}
	
	
	
	
}
