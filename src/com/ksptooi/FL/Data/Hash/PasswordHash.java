package com.ksptooi.FL.Data.Hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.ksptooi.FL.Data.Config.ConfigManager;

public class PasswordHash {

	//���ݲ�������ļ� �Զ�ʶ������㷨
	public String autoCompression(String str){
		
		String Hash = ConfigManager.getConfig().getEnable_passwordHash();
		
		//�������㷨
		if(Hash.equalsIgnoreCase("false")){
			return str;
		}
		
		//md5
		if(Hash.equalsIgnoreCase("md5")){		
			return md5(str);			
		}
		
		
		return str;	
		
	}
	
	//ʹ��ָ���ļ����㷨
	public String Compression(String str,String Hash){
		
		//�������㷨
		if(Hash.equalsIgnoreCase("false")){
			return str;
		}
		
		//md5
		if(Hash.equalsIgnoreCase("md5")){		
			return md5(str);			
		}
		
		return str;
		
	}
	
	
	
	
	
	//�����㷨
	private String md5(String str){
		

        byte[] secretBytes = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(str.getBytes());
           
            secretBytes = md.digest();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("�㷨�쳣! - MD5");
        }
        
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16��������

        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        
        return md5code;
         
    }
		
		
		

	
	
	@SuppressWarnings("unused")
	private String sha1(String str){
		return str;
		
	}
	
	
	
	
	
}
