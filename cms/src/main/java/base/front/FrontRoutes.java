package base.front;

import com.jfinal.config.Routes;

/**
 * 
 * @ClassName:  FrontRoutes   
 * @Description:前端路由地址
 * @author: SuagrYe 
 * @date:   2019年6月14日 下午8:55:35      
 * @Copyright: 2019 www.sugarye.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
public class FrontRoutes extends Routes {

    //jfinal路由配置文件 
	public void config() {
		
		//设置前端默认的view访问路径
		setBaseViewPath("/view/front");
		
		//设置前端管理控制器
        add("/front",FrontController.class,"/");
	}

}
