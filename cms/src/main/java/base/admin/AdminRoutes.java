package base.admin;

import com.jfinal.config.Routes;

/**
 * 
 * @ClassName:  AdminRoutes   
 * @Description:���·�ɹ������  
 * @author: SuagrYe 
 * @date:   2019��6��14�� ����10:45:34        
 * @Copyright: 2019 www.sugarye.com Inc. All rights reserved. 
 * ע�⣺�����ݽ������ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class AdminRoutes extends Routes {

    //jfinal·�������ļ� 
	public void config() {
		
	    //���ú��Ĭ�ϵ�view����·��
		setBaseViewPath("/view/admin");
		
		//���ú�˹��������
        add("/admin",AdminController.class,"/");
		
	}
	
}
