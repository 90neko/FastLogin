package com.ksptooi.FL.Data.PlayerDataIO;

import java.io.File;

import org.bukkit.entity.Player;

import com.ksptooi.FL.Data.Player.Entity.PlayerEntity;

public interface PlayerDataIO_Interfrace {
	
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
	public PlayerEntity queryPlayerDataByName(String playerName);
	
	//������queryPlayerData(String PlayerName)	
	public PlayerEntity queryPlayerData(Player PlayerEntity);
	
	/**
	 * ������������ļ���GD
	 * @param playerDataEntity
	 * @return �ɹ�����true ʧ�ܷ���false
	 */
	public boolean updatePlayerData(PlayerEntity playerDataEntity);
	
	/**
	 * ��ȡ��������ļ�λ��
	 * @param playerName �������
	 * @return ����һ��Fileʵ����
	 */
	public File getPlayerDataFile(String playerName);
	
	//������getPlayerDataFile(String playerName)
	public File getPlayerDataFile(Player playerEntity);
	
	
}
