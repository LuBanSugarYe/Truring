package base.admin;

import com.jfinal.core.Controller;

/**
 * 
 * @ClassName: AdminController
 * @Description:管理系统的入口
 * @author: SuagrYe
 * @date: 2019年6月14日 下午9:59:51
 * 
 * @Copyright: 2019 www.sugarye.com Inc. All rights reserved.
 *             注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
public class AdminController extends Controller {
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 默认访问页面
	 * @param: void
	 * @return:void 
	 * @throws
	 */
	public void index() {
        render("login.html");
	}
}
