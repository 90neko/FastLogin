package com.ksptooi.playerData_DAL;

import java.io.File;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Entity.PlayerDataEntity;

public interface PlayerDataDAL_Interface {
	
	/**
	 * ������������ļ�
	 * @return �����ɹ�����true �ļ��Ѵ���/��ʧ�� ����false
	 */
	public boolean createPlayerData(String playerName);
	
	/**
	 * ��GD�ļ�������������ļ�
	 * @param �����
	 * @return ����һ��PlayerDataEntity
	 */
	public PlayerDataEntity queryPlayerDataByName(String playerName);
	
	//������queryPlayerData(String PlayerName)	
	public PlayerDataEntity queryPlayerData(Player PlayerEntity);
	
	/**
	 * ������������ļ���GD
	 * @param playerDataEntity
	 * @return �ɹ�����true ʧ�ܷ���false
	 */
	public boolean updatePlayerData(PlayerDataEntity playerDataEntity);
	
	/**
	 * ��ȡ��������ļ�λ��
	 * @param playerName �������
	 * @return ����һ��Fileʵ����
	 */
	public File getPlayerDataFile(String playerName);
	
	//������getPlayerDataFile(String playerName)
	public File getPlayerDataFile(Player playerEntity);
	
	
}
