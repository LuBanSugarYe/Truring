package base.front;

import com.jfinal.config.Routes;

/**
 * 
 * @ClassName:  FrontRoutes   
 * @Description:ǰ��·�ɵ�ַ
 * @author: SuagrYe 
 * @date:   2019��6��14�� ����8:55:35      
 * @Copyright: 2019 www.sugarye.com Inc. All rights reserved. 
 * ע�⣺�����ݽ������ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class FrontRoutes extends Routes {

    //jfinal·�������ļ� 
	public void config() {
		
		//����ǰ��Ĭ�ϵ�view����·��
		setBaseViewPath("/view/front");
		
		//����ǰ�˹��������
        add("/front",FrontController.class,"/");
	}

}
