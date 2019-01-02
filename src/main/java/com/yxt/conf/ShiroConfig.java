package com.yxt.conf;

import com.yxt.officialVehicl.core.jwt.JwtFilter;
import com.yxt.officialVehicl.core.jwt.JwtRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * jwt Realm
     * @return
     */
	@Bean
	public JwtRealm jwtRealm()
	{
	    return new JwtRealm();
	}
	
	@Bean
	public DefaultWebSecurityManager securityManager()
	{
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jwtRealm());
        
        // 关闭shiro session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);      
        return securityManager;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
	{
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
	    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	    shiroFilterFactoryBean.setSecurityManager(securityManager());
	    
	    Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
	    filters.put("jwt", jwtFilter());
	    
	    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	    filterChainDefinitionMap.put("/toLogin", "anon");
	    filterChainDefinitionMap.put("/login", "anon");
	    //filterChainDefinitionMap.put("/**", "user");
	    //filterChainDefinitionMap.put("/**", "jwt");
	    filterChainDefinitionMap.put("/user/queryMenu", "jwt");
	    filterChainDefinitionMap.put("/menu/queryCurrentPermission", "jwt");
	    filterChainDefinitionMap.put("/menu/queryCurrentMenuTree", "jwt");
	    //build
		filterChainDefinitionMap.put("/build/save", "jwt");
		filterChainDefinitionMap.put("/build/pageList", "jwt");
		filterChainDefinitionMap.put("/build/findByid", "jwt");
		filterChainDefinitionMap.put("/build/update", "jwt");
		filterChainDefinitionMap.put("/build/deleteByid", "jwt");
		filterChainDefinitionMap.put("/buildRegion/queryRegionList", "jwt");
		//floor
		filterChainDefinitionMap.put("/floor/save", "jwt");
		filterChainDefinitionMap.put("/floor/pageList", "jwt");
		filterChainDefinitionMap.put("/floor/findByid", "jwt");
		filterChainDefinitionMap.put("/floor/deleteByid", "jwt");
		filterChainDefinitionMap.put("/floor/update", "jwt");
		filterChainDefinitionMap.put("/build/showBuildList", "jwt");
		//room
		filterChainDefinitionMap.put("/room/save", "jwt");
		filterChainDefinitionMap.put("/room/pageList", "jwt");
		filterChainDefinitionMap.put("/room/findByid", "jwt");
		filterChainDefinitionMap.put("/room/deleteByid", "jwt");
		filterChainDefinitionMap.put("/room/update", "jwt");
		filterChainDefinitionMap.put("/floor/findFlistByBid", "jwt");
		//level
		filterChainDefinitionMap.put("/level/save", "jwt");
		filterChainDefinitionMap.put("/level/pageList", "jwt");
		filterChainDefinitionMap.put("/level/findByid", "jwt");
		filterChainDefinitionMap.put("/level/deleteByid", "jwt");
		filterChainDefinitionMap.put("/level/update", "jwt");
		//绑定
		filterChainDefinitionMap.put("/deptbind/deptList", "jwt");
		filterChainDefinitionMap.put("/deptbind/buildList", "jwt");
		filterChainDefinitionMap.put("/deptbind/deptBindRoom", "jwt");
		filterChainDefinitionMap.put("/deptbind/roomByDept", "jwt");
		filterChainDefinitionMap.put("/bind/deptList", "jwt");
		filterChainDefinitionMap.put("/bind/roomListBydeptid", "jwt");
		filterChainDefinitionMap.put("/bind/personListBydeptid", "jwt");
		filterChainDefinitionMap.put("/bind/personsByRoom", "jwt");
		filterChainDefinitionMap.put("/bind/personBindRoom", "jwt");
		//person
		filterChainDefinitionMap.put("/person/personListByDept", "jwt");
		filterChainDefinitionMap.put("/person/pageListByDeptid", "jwt");
		filterChainDefinitionMap.put("/person/save", "jwt");
		filterChainDefinitionMap.put("/person/deleteByid", "jwt");
		filterChainDefinitionMap.put("/person/findByid", "jwt");
		filterChainDefinitionMap.put("/person/update", "jwt");




	    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	    shiroFilterFactoryBean.setFilters(filters);
	    return shiroFilterFactoryBean;
	}
	
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() 
	{
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
	}
	
	/**
	 * 定义Jwt过滤器
	 * @return
	 */
	@Bean
	public JwtFilter jwtFilter() {
	    return new JwtFilter();
	}
	
}
