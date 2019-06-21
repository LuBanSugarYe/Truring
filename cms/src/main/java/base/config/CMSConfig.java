package base.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

import base.admin.AdminRoutes;
import base.front.FrontRoutes;

public class CMSConfig extends JFinalConfig {
	
	static Prop p;
	
	public static void main(String[] args) {
		UndertowServer.start(CMSConfig.class, 8088, true);
	}
	
	/**
	 * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
	 * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
	 */
	static void loadConfig() {
		if (p == null) {
			p = PropKit.useFirstFound("demo-config-pro.txt", "demo-config-dev.txt");
		}
	}

	@Override
	public void configConstant(Constants me) {
		loadConfig();
		
		me.setDevMode(p.getBoolean("devMode", false));
		
		/**
		 * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
		 * 注入动作支持任意深度并自动处理循环注入
		 */
		me.setInjectDependency(true);
		
		// 配置对超类中的属性进行注入
		me.setInjectSuperClass(true);
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new FrontRoutes()); //前端路由
		me.add(new AdminRoutes()); //后端路由
			
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置 druid 数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		//_MappingKit.mapping(arp);
		me.add(arp);
		
	}
	
	public static DruidPlugin createDruidPlugin() {
		loadConfig();
		
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
