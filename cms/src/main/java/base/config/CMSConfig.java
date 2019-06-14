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
	 * PropKit.useFirstFound(...) ʹ�ò����д��������ȱ��ҵ��������ļ�
	 * ����������ȥ�����ã��ҵ����������ز��������أ��������ý�������
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
		 * ֧�� Controller��Interceptor��Validator ֮��ʹ�� @Inject ע��ҵ��㣬�����Զ�ʵ�� AOP
		 * ע�붯��֧��������Ȳ��Զ�����ѭ��ע��
		 */
		me.setInjectDependency(true);
		
		// ���öԳ����е����Խ���ע��
		me.setInjectSuperClass(true);
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new FrontRoutes()); //ǰ��·��
		me.add(new AdminRoutes()); //���·��
			
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// ���� druid ���ݿ����ӳز��
		DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
		me.add(druidPlugin);
		
		// ����ActiveRecord���
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// ����ӳ���� MappingKit ���Զ����㶨
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
