package base.admin;

import com.jfinal.core.Controller;

/**
 * 
 * @ClassName: AdminController
 * @Description:����ϵͳ�����
 * @author: SuagrYe
 * @date: 2019��6��14�� ����9:59:51
 * 
 * @Copyright: 2019 www.sugarye.com Inc. All rights reserved.
 *             ע�⣺�����ݽ������ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class AdminController extends Controller {
	
	/**
	 * 
	 * @Title: index 
	 * @Description: Ĭ�Ϸ���ҳ��
	 * @param: void
	 * @return:void 
	 * @throws
	 */
	public void index() {
        render("login.html");
	}
}
