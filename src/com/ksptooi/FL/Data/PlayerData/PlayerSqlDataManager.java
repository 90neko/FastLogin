package com.ksptooi.FL.Data.PlayerData;

import java.sql.ResultSet;
import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Config.ConfigManager;
import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;
import com.ksptooi.FL.Util.FUtil;
import com.ksptooi.FL.Util.Logger;
import com.ksptooi.gdc.v5.MysqlAPI.MysqlController;


public class PlayerSqlDataManager{

	MysqlController MC=null;
	Logger lm=null;
	
	String playerDataTable=null;
	String playerNameField=null;
	String playerPwdField=null;
	String playerRegStatusField=null;
	String playerLoginStatusField=null;
	
	String playerLocTable=null;
	String playerNameField1=null;
	String playerLocworld=null;
	String playerLocx=null;
	String playerLocy=null;
	String playerLocz=null;
	String playerLocpitch=null;
	String playerLocyaw=null;
	
	public PlayerSqlDataManager(){
		
		MC=new MysqlController();
		MC.loadConfigFromgdFile(ConfigManager.fastLoginConfigFile);
//		MC.loadConfigFromgdFile(new File("F:\\1217/MineCraft Server/1.7.10[PT]/plugins/ksptooi/fastlogin/fastlogin.conf"));
		lm=new Logger();
		
		playerDataTable=ConfigManager.getConfig().getPlayerDataTable();
		playerNameField=ConfigManager.getConfig().getPlayerNameField();
		playerPwdField=ConfigManager.getConfig().getPlayerPwdField();
		playerRegStatusField=ConfigManager.getConfig().getPlayerRegStatusField();
		playerLoginStatusField=ConfigManager.getConfig().getPlayerLoginStatusField();
		
		playerLocTable=ConfigManager.getConfig().getPlayerLocTable();
		playerNameField1=playerNameField;
		playerLocworld=ConfigManager.getConfig().getPlayerLocworld();
		playerLocx=ConfigManager.getConfig().getPlayerLocx();
		playerLocy=ConfigManager.getConfig().getPlayerLocy();
		playerLocz=ConfigManager.getConfig().getPlayerLocz();
		playerLocpitch=ConfigManager.getConfig().getPlayerLocpitch();
		playerLocyaw=ConfigManager.getConfig().getPlayerLocyaw();
		
		this.createTable();
	}
	
	
	
	public void createTable(){
		
		
		lm.logInfo("����");
		
		if( ! MC.tableIsExists(playerDataTable)){
			
			String playerDataTableSql="create table "+playerDataTable+"("+playerNameField+" varchar(50) not NULL PRIMARY KEY,"+playerPwdField+" varchar(50) not null,"+playerRegStatusField+" varchar(5) not null,"+playerLoginStatusField+" varchar(5) not NULL)";
			
			String playerLocTableSql="CREATE TABLE "+playerLocTable+"("+playerNameField1+" varchar(50) NOT NULL PRIMARY KEY,"+playerLocworld+" varchar(50) NOT NULL,"+playerLocx+" double(20,2) NOT NULL,"+playerLocy+" double(20,2) NOT NULL,"+playerLocz+" double(20,2) NOT NULL,"+playerLocpitch+" double(20,2) NOT NULL,"+playerLocyaw+" double(20,2) NOT NULL)";
			
			String foreign="alter table "+playerLocTable+" add constraint FK_"+playerNameField+" foreign key ("+playerNameField1+") references "+playerDataTable+"("+playerNameField+")";
			
			lm.logInfo("������ - "+playerDataTable);
			lm.logInfo("������ - "+playerLocTable);

			//������ݱ�
			MC.noQuery(playerDataTableSql);
			
			//���λ�ñ�
			MC.noQuery(playerLocTableSql);
			
			lm.logInfo("����Լ��");
			
			MC.noQuery(foreign);
			
		}
		
		
	}
	
	
	public boolean createPlayerData(String playerName) {
		
		String querySql="select * from "+playerDataTable+" left JOIN "+playerLocTable+" on "+playerDataTable+"."+playerNameField+"="+playerLocTable+"."+playerNameField1+" where "+playerDataTable+"."+playerNameField+"='"+playerName+"'";
		
		String insertSql="insert "+playerDataTable+" values('"+playerName+"','#','0','0')";
		
		String insertSql1="insert "+playerLocTable+" values('"+playerName+"','empty',0,0,0,0,0);";
			
		try {
		
			ResultSet rs=MC.query(querySql);
				
			
			while(rs.next()){
				return false;
			}
			
			
			lm.logInfo("��Ϊ��Ҵ����µ����ݿ��¼:"+playerName);
			
			MC.noQuery(insertSql);
			MC.noQuery(insertSql1);
			
			rs.getStatement().close();
			
		} catch (Exception e) {
			e.printStackTrace();
			lm.logError("���ݿ���� - createPlayerData");
			System.exit(0);
			return false;
		}
		
		
		return true;
	}

	
	public boolean createPlayerData(Player playerEntity) {
		return this.createPlayerData(playerEntity.getName());
	}

	
	public PlayerEntity getPlayerData(String playerName){
		
		try {
		
		String querySql="select * from "+playerDataTable+" "				
				+ "left JOIN "+playerLocTable+" on "+playerDataTable+"."+playerNameField+"="+playerLocTable+"."+playerNameField1+" "
				+ "where "+playerDataTable+"."+playerNameField+"='"+playerName+"'";
		
		PlayerEntity PDE=new PlayerEntity();
	
		this.createPlayerData(playerName);
		
		
		ResultSet rs=MC.query(querySql);
		
			
			
			while(rs.next()){
				
				PDE.setPlayername(rs.getString(playerNameField));
				PDE.setPassword(rs.getString(playerPwdField));		
				PDE.setRegister(rs.getString(playerRegStatusField));
				PDE.setLogin(rs.getString(playerLoginStatusField));
				PDE.setLoc_world(rs.getString(playerLocworld));
				PDE.setLoc_x(new Double(rs.getString(playerLocx)));
				PDE.setLoc_y(new Double(rs.getString(playerLocy)));
				PDE.setLoc_z(new Double(rs.getString(playerLocz)));
				PDE.setLoc_pitch(new Double(rs.getString(playerLocpitch)));
				PDE.setLoc_yaw(new Double(rs.getString(playerLocyaw)));
				rs.getStatement().close();
				return PDE;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			lm.logError("���ݿ���� - getPlayerData");
			System.exit(0);
		}
		
		
		
		return null;
	}

	public PlayerEntity getPlayerData(Player playerEntity) {
		return this.getPlayerData(playerEntity.getName());
	}

	
	public boolean updatePlayerData(PlayerEntity PDE) {
		
		String updateSql="UPDATE "+playerDataTable+" "
				+ "set "+playerPwdField+"='"+PDE.getPassword()+"',"+playerRegStatusField+"='"+PDE.getRegister()+"',"+playerLoginStatusField+"='"+PDE.getLogin()+"' "
				+ "where "+playerNameField+"='"+PDE.getPlayername()+"'";
		
		String updateSql1="update "+playerLocTable+" "
				+ "set "+playerLocworld+"='"+PDE.getLoc_world()+"',"+playerLocx+"='"+PDE.getLoc_x()+"',"+playerLocy+"='"+PDE.getLoc_y()+"',"+playerLocz+"='"+PDE.getLoc_z()+"',"+playerLocpitch+"='"+PDE.getLoc_pitch()+"',"+playerLocyaw+"='"+PDE.getLoc_yaw()+"' "
				+ "where "+playerNameField1+"='"+PDE.getPlayername()+"'";
		
		
		MC.noQuery(updateSql);
		MC.noQuery(updateSql1);
		
		
		return true;
	}

	
}
