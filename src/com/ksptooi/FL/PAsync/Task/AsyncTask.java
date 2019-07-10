package com.ksptooi.FL.PAsync.Task;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.ksptooi.FL.Util.FUtil;

public class AsyncTask {

	
	
	//������Ϣ
	public void taskMessage(Player pl,String str) {
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {
			
			public void run() {
				pl.sendMessage(str);			
			}
				
		});	
		
	}
	
	
	
	
	//�첽תͬ���߳����
	public void taskKick(Player pl,String KickMessage){
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				pl.kickPlayer(KickMessage);
			}

		});
		
	}
	
	//�첽TP
	public void taskTp(Player pl,Location loc) {
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			@Override
			public void run() {
				
				pl.teleport(loc);
				
			}
			
		});	
		
	}
	
	
	//�첽����op
	public void taskSetOP(Player pl,Boolean bool) {
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			@Override
			public void run() {
				
				pl.setOp(bool);
				
			}
			
		});
		
		
	}
	
	
	
	//�첽תͬ��������ҵ���Ϸģʽ
	public void taskSetGameMode(Player pl,int GameModeCode){
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				
				if(GameModeCode==0){
					pl.setGameMode(GameMode.SURVIVAL);
				}
				
				if(GameModeCode==1){
					pl.setGameMode(GameMode.CREATIVE);
				}
				
				if(GameModeCode==2){
					pl.setGameMode(GameMode.ADVENTURE);
				}
				
			}

		});
	}
	
	//�첽תͬ��Ϊ������Effect
	public void taskAddPotionEffect(Player pl,PotionEffect PE){
		
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				
				
				pl.addPotionEffect(PE,false);
				
			}

		});
		
		
	}
	
	//�첽תͬ��Ϊ����Ƴ�Effect
	public void taskRemovePotionEffect(Player pl,PotionEffectType PET){
		
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {

			public void run() {
				
				
				pl.removePotionEffect(PET);
				
			}

		});
		
	}

	
	//�������е�ĳ�����겥�Ŷ���
	public void taskPlayEffect(World world,Location loc,Effect effect) {
		
		Bukkit.getScheduler().runTask(FUtil.MainClass, new Runnable() {
			
			public void run() {
				
				world.playEffect(loc, effect,0);
						
			}	
			
		});
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
