package us.ktmc.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import us.ktmc.util.Var;

public class Fast_SQLHandler {

	
	Statement stm=null;
	
	//����������ݱ�,��������򲻽����κβ���
	public void CreateDataTable(){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			
		System.out.println("[FastLogin]�����������ݱ�.");
		//���������ݱ��Ƿ����
		if(Var.V4.TableExists(Var.SQLConn, Var.PlayerDataTable)){
			return;
		}
		
		System.out.println("[FastLogin]������������ݱ�.");
		//������
		stm.executeUpdate("create table "+Var.PlayerDataTable+"("
						+Var.PlayerNameField+" varchar(50) NOT NULL PRIMARY KEY,"
						+Var.PlayerPwdField+ " varchar(50) NOT NULL,"
						+Var.PlayerRegStatusField+ " varchar(5) NOT NULL,"
						+Var.PlayerLoginStatusField+ " varchar(5) NOT NULL)");
		
		
		stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
	}
	
	//�������λ�ñ�,��������򲻽����κβ���
	public void CreateLocTable(){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			

		
			System.out.println("[FastLogin]��������λ�ñ�.");
		//������λ�ñ��Ƿ����
		if(Var.V4.TableExists(Var.SQLConn, Var.PlayerLocTable)){
			return;
		}
		
		System.out.println("[FastLogin]���������λ�ñ�.");
		//������
		stm.executeUpdate("create table "+Var.PlayerLocTable+"("
						+Var.PlayerNameField+" varchar(50) NOT NULL PRIMARY KEY,"
						+Var.PlayerLocworld+ " varchar(50) NOT NULL,"
						+Var.PlayerLocx+ " DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocy+" DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocz+ " DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocpitch+ " DOUBLE(20,2) NOT NULL,"
						+Var.PlayerLocyaw+ " DOUBLE(20,2) NOT NULL)");
		
		//����Լ��
		
		System.out.println("[FastLogin]������Լ��.");
		stm.executeUpdate("alter table "+Var.PlayerLocTable+" add constraint FK_playername foreign key("+Var.PlayerNameField+") references "+Var.PlayerDataTable+"("+Var.PlayerNameField+")");
		
		stm.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
	}
	
	
	/**
	 * ��ѯ��Ҽ�¼�Ƿ���ڡ�
	 * 
	 * @param Player ���ʵ��
	 * 
	 */
	public boolean SQL_PlayerExists(Player pl){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerNameField+" from "+Var.PlayerDataTable+" "
										+ "where "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			while(RS.next()){
				
				if(RS.getString(Var.PlayerNameField).equalsIgnoreCase(pl.getName())){
					stm.close();
					return true;
				}
				
			}
			
			stm.close();
			return false;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}	
		
		
		return false;
		
	}
	
	
	
	
	/**
	 * ����һ����Ҽ�¼,����¼�Ѵ���ʱ��ִ���κβ�����
	 * 
	 * @param Player ���ʵ��
	 * 
	 */
	public void SQL_CreatePlayerData(Player pl){
		
		if(SQL_PlayerExists(pl)){
			return;
		}
		
		try {
			
			System.out.println("[FastLogin]��Ϊ��Ҵ����µ����ݿ��¼:"+pl.getName());
			
			stm=Var.SQLConn.createStatement();
			
			//���������ݱ� - ��¼
			stm.executeUpdate("INSERT INTO "+Var.PlayerDataTable+"("+Var.PlayerNameField+","+Var.PlayerPwdField+","+Var.PlayerRegStatusField+","+Var.PlayerLoginStatusField+")"
					+ "VALUES('"+pl.getName().toLowerCase()+"',"+"'#','0','0')");
			
			//������λ�ñ� - ��¼
			stm.executeUpdate("INSERT INTO "+Var.PlayerLocTable+"("+Var.PlayerNameField+","+Var.PlayerLocworld+","+Var.PlayerLocx+","+Var.PlayerLocy+","+Var.PlayerLocz+","+Var.PlayerLocpitch+","+Var.PlayerLocyaw+") "
					+ "VALUES('"+pl.getName().toLowerCase()+"','world',0,0,0,0,0)");
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
			
		
	}
	
	/**
	 * ������ҵ�¼״̬
	 * 
	 * @param Player ���ʵ��
	 * @param isLogin �����Ƿ��¼ ���ֵΪTrue������ļ�ͬ�����ѵ�¼����δ��¼
	 */
	public void SQL_setPlayerLogin(Player pl,Boolean isLogin){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			if(isLogin){
				
				stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerLoginStatusField+"='1'"
						+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
				
				stm.close();
				return;
				
			}
			
			stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerLoginStatusField+"='0'"
					+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
		
	}
	
	
	
	/**
	 * �ж�����Ƿ�ע��
	 * @param PlayerEntity ���ʵ�� 
	 * @return ���ΪTrue�������ע��.
	 */
	public boolean SQL_isReg(Player PlayerEntity){	
		return SQL_isReg(PlayerEntity.getName());
	}
	
	
	
	/**
	 * �ж�����Ƿ�ע��
	 * @param PlayerName �������
	 * @return ���ΪTrue�������ע��.
	 */
	
	public boolean SQL_isReg(String Player){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerRegStatusField+" from "+Var.PlayerDataTable+" "
										+ "where playername='"+Player.toLowerCase()+"'");
			
			while(RS.next()){
				
				if(RS.getString(Var.PlayerRegStatusField).equalsIgnoreCase("1")){
					System.out.println("��ע�ᣡ");
					return true;
				}
				
			}
				
			System.out.println("δע�ᣡ");
			stm.close();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
		return false;

	}
	
	
	/**
	 * �ж�����Ƿ��¼
	 * @param pl ���ʵ��
	 * @return ���ΪTrue������ѵ�¼.
	 */
	public boolean SQL_isLogin(Player pl){
		return SQL_isLogin(pl.getName());
	}
	
	
	/**
	 * �ж�����Ƿ��¼
	 * @param PlayerName �������
	 * @return ���ΪTrue������ѵ�¼.
	 */
	public boolean SQL_isLogin(String Player){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerLoginStatusField+" from "+Var.PlayerDataTable+" "
										+ "where playername='"+Player.toLowerCase()+"'");
			
			while(RS.next()){
				
				if(RS.getString(Var.PlayerLoginStatusField).equalsIgnoreCase("1")){
					return true;
				}
				
			}
				
			
			stm.close();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
		return false;

	}
	
	/**
	 * �������ע��״̬
	 * 
	 * @param Player ���ʵ��
	 * @param isReg �����Ƿ�ע�� ���ֵΪTrue������ļ�ͬ������ע�ᷴ��δע��
	 */
	public void SQL_setPlayerReg(Player pl,Boolean isReg){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			if(isReg){
				
				stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerRegStatusField+"='1'"
						+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
				
				stm.close();
				return;
				
			}
			
			stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerRegStatusField+"='0'"
					+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
		
	}
	
	/**
	 * �����������
	 * 
	 * @param Player ���ʵ��
	 * @param pwd ����
	 */
	public void SQL_setPlayerPwd(Player pl,String pwd){
		
		try {
			
			stm=Var.SQLConn.createStatement();
			
			
			
			stm.executeUpdate("UPDATE "+Var.PlayerDataTable+" set "+Var.PlayerPwdField+"='"+pwd+"'"
					+ "WHERE "+Var.PlayerNameField+"='"+pl.getName()+"'");
			
			
			stm.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
		
	}
	
	/**
	 * �ж���������Ƿ���ȷ
	 * @param pl ���ʵ��
	 * @param pwd �������
	 * @return ���ֵΪtrue�����������ȷ
	 */
	public boolean SQL_isPassword(Player pl,String pwd){
		
		String SQLpwd="";
		
		try {
			stm=Var.SQLConn.createStatement();
			
			
			ResultSet RS=stm.executeQuery("select "+Var.PlayerPwdField+" from "+Var.PlayerDataTable+" "
										+ "where playername='"+pl.getName().toLowerCase()+"'");
			
			while(RS.next()){
				SQLpwd=RS.getString(Var.PlayerPwdField);
			}
			
			if(pwd.equals(SQLpwd)){
				return true;
			}
			
			
			stm.close();
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[FastLogin]������:���ݿ����Ӵ���");
		}
		
		
		return false;
	}
	
	
	
	
	
}
