package base.admin;

import com.jfinal.config.Routes;

/**
 * 
 * @ClassName:  AdminRoutes   
 * @Description:后端路由管理入口  
 * @author: SuagrYe 
 * @date:   2019年6月14日 下午10:45:34        
 * @Copyright: 2019 www.sugarye.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
public class AdminRoutes extends Routes {

    //jfinal路由配置文件 
	public void config() {
		
	    //设置后端默认的view访问路径
		setBaseViewPath("/view/admin");
		
		//设置后端管理控制器
        add("/admin",AdminController.class,"/");
		
	}
	
}
