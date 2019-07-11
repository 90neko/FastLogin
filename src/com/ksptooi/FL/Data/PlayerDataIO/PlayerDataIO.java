package com.ksptooi.FL.Data.PlayerDataIO;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Manager.DataManager;
import com.ksptooi.FL.Data.Player.Cache.PlayerDataCache;
import com.ksptooi.FL.Data.Player.Entity.PlayerData;
import com.ksptooi.FL.General.Performance.PerformanceMonitorManager;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v6.Factory.DataSessionFactory;
import com.ksptooi.gdc.v6.Session.dataSession;

public class PlayerDataIO implements PlayerDataIO_Interfrace{

	
	
	DataSessionFactory dataSessionFactory = null;
	
	Logger LM=null;
	
	public PlayerDataIO(){
		
		LM=new Logger();
		dataSessionFactory = DataManager.getDataSessionFactory();
		
		
	}
	
	
	//������������ļ�
	@Override
	public boolean createPlayerData(String playerName) {
		
		playerName=playerName.toLowerCase();
		
		File playerDataFile = this.getPlayerDataFile(playerName);
			
		//�ж��ļ��Ƿ����
		if( ! playerDataFile.exists()){
			
			LM.logInfo("��Ϊ��Ҵ����µ������ļ�:"+playerName+".gd");
			
			dataSessionFactory.createdata(playerDataFile);
			
			dataSession ds = dataSessionFactory.openSession(playerDataFile);
			
			ds.addline("playername=" + playerName);
			ds.addline("password=#");
			ds.addline("register=0");
			ds.addline("login=0");
			ds.addline("loc.world=empty");
			ds.addline("loc.x=empty");
			ds.addline("loc.y=empty");
			ds.addline("loc.z=empty");
			ds.addline("loc.pitch=empty");
			ds.addline("loc.yaw=empty");
			
			ds.release();
			
			return true;
		}
		
		return false;
	}
	

	//��GD�ļ�||���� ������������ļ�
	@Override
	public PlayerData queryPlayerDataByName(String playerName) {
		
				
		//��黺��
		
		if(PlayerDataCache.isExistsOfPlayerData(playerName)){
						
			return PlayerDataCache.getPlayerData(playerName);
			
		}
		
		//����IO����
		PerformanceMonitorManager.addPFPC();
			
		
		File playerDataFile = this.getPlayerDataFile(playerName.toLowerCase());
		
		PlayerData pde=new PlayerData();
		
		
		dataSession ds = dataSessionFactory.openSession(playerDataFile);
		
		pde.setPlayername(ds.get("playername"));
		pde.setPassword(ds.get("password"));
		pde.setRegister(ds.get("register"));
		pde.setLogin(ds.get("login"));
		
		//�����ҵ�loc.world������empty���ȡ��������˳�λ��
		if(!ds.get("loc.world").equals("empty")){
			
			pde.setLoc_world(ds.get("loc.world"));
			pde.setLoc_x(ds.getDouble("loc.x"));
			pde.setLoc_y(ds.getDouble("loc.y"));
			pde.setLoc_z(ds.getDouble("loc.z"));
			pde.setLoc_pitch(ds.getDouble("loc.pitch"));
			pde.setLoc_yaw(ds.getDouble("loc.yaw"));
			
			ds.release();
			
			Location loc=new Location(null, 0, 0, 0, 0, 0);
			
			loc.setWorld(Bukkit.getWorld(pde.getLoc_world()));
			loc.setX(pde.getLoc_x());
			loc.setY(pde.getLoc_y());
			loc.setZ(pde.getLoc_z());
			loc.setPitch(new Float(pde.getLoc_pitch()));
			loc.setYaw(new Float(pde.getLoc_yaw()));
			
		}
			
		//��ӻ���
		PlayerDataCache.updatePlayerData(pde);
		
		return pde;
	}

	//��GD�ļ�������������ļ�
	@Override
	public PlayerData queryPlayerData(Player PlayerEntity) {
		return this.queryPlayerDataByName(PlayerEntity.getName());
	}
	

	//������������ļ���GD
	@Override
	public boolean updatePlayerData(PlayerData playerDataEntity) {
		
		
		File playerDataFile = this.getPlayerDataFile(playerDataEntity.getPlayername());
		
		PlayerData pde=playerDataEntity;
		
		dataSession ds = dataSessionFactory.openSession(playerDataFile);
		
		
		ds.set("password", pde.getPassword());
		ds.set("register", pde.getRegister());
		ds.set("login", pde.getLogin());
		ds.set("loc.world", pde.getLoc_world());
		ds.set("loc.x", pde.getLoc_x());
		ds.set("loc.y", pde.getLoc_y());
		ds.set("loc.z", pde.getLoc_z());
		ds.set("loc.pitch", pde.getLoc_pitch());
		ds.set("loc.yaw", pde.getLoc_yaw());
		
		ds.release();
		
		//����IO����
		PerformanceMonitorManager.addPFPC();
		
		//���»���
		PlayerDataCache.updatePlayerData(playerDataEntity);

		return true;
	}
	
	

	//��ȡ��������ļ�λ��
	@Override
	public File getPlayerDataFile(String playerName) {
		return new File(ConfigManager.fastLoginPlayerDataFolder+playerName+".gd");
	}

	//��ȡ��������ļ�λ��
	@Override
	public File getPlayerDataFile(Player playerEntity) {
		return this.getPlayerDataFile(playerEntity.getName());
	}
	

}
