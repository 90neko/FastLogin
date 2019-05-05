package us.ktmc.file;

import java.io.File;
import java.io.IOException;
import us.ktmc.util.Var;
import us.ktmc.util_interface.FastUtil;

public class OldConfUpdata {

	
	public final void AutoUpdataOldConf(){
	
		try {
			
			if(!(Var.FLconf.exists())){
				return;
			}

			if(!Var.V2.getKeyValue(FastUtil.Mconf, "Version=").equalsIgnoreCase(FastUtil.FastLoginVersion)){
				System.out.println("��[FastLogin]����������....");	
				System.out.println("[FastLogin]�����־ɰ������ļ�");
				System.out.println("[FastLogin]���ѽ����ļ�������Ŀ¼:Plugins/ksptooi/fastlogin/"+Var.OLDConf.getName());
				Var.OLDConf.delete();
				Var.FLconf.renameTo(Var.OLDConf);
			}
			
				
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[����]���ļ�ϵͳ����,��ɾ��FastLogin�����ļ���������������");
			System.exit(0);
			
		}
				
	}
	
	
	
	
	public final void AutoUpdataPlayerdata(){
		
		File[] dir=new File("plugins/ksptooi/fastlogin/database/").listFiles();
		System.out.println("[FastLogin]��������������ļ�....");
		
		for(int i=0;i<dir.length;i++){		
			if(dir[i].isFile()){
				
				Var.IO.AddtoFile(dir[i], "\r\nloc.world=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.x=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.y=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.z=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.pitch=empty", "UTF-8");
				Var.IO.AddtoFile(dir[i], "\r\nloc.yaw=empty", "UTF-8");				
				 
			}
				
		}
			
		
	}
	
	
			
	
}
