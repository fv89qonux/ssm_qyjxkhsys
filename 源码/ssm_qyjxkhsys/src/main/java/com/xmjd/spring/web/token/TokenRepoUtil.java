package com.xmjd.spring.web.token;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;


public class TokenRepoUtil {
	
	protected static PropertiesConfiguration config=new PropertiesConfiguration();
	static {
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			ResourceLoader resourceLoader=new DefaultResourceLoader(classLoader);
			Resource res = resourceLoader.getResource("classpath:token.properties");
			InputStream is=res.getInputStream();
			ReloadingStrategy r=new FileChangedReloadingStrategy();
			config.setReloadingStrategy(r);
			config.load(is);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getToken(){
		String token=config.getString("sys.token");
		return token;
	}
}
