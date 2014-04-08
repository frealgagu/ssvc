package co.edu.udistrital.datasource;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

@Configuration
@PropertySource("classpath:configuration.properties")
public class DataSourceConfiguration {

	@Autowired
	private Environment env;
	
	@Bean
	public SharedPoolDataSource dataSource() {
		SharedPoolDataSource dataSource = new SharedPoolDataSource();
		
		MysqlConnectionPoolDataSource poolDataSource = new MysqlConnectionPoolDataSource();
		poolDataSource.setServerName(env.getProperty("database.host"));
		poolDataSource.setPortNumber(env.getProperty("database.port", Integer.class));
		poolDataSource.setDatabaseName(env.getProperty("database.name"));
		poolDataSource.setUser(env.getProperty("database.username"));
		poolDataSource.setPassword(env.getProperty("database.password"));
		dataSource.setConnectionPoolDataSource(poolDataSource);
		
		dataSource.setMaxActive(env.getProperty("max.active", Integer.class, 20));
		dataSource.setMaxIdle(env.getProperty("max.idle", Integer.class, 10));
		dataSource.setMaxWait(env.getProperty("max.wait", Integer.class, 5000));
		dataSource.setTestOnBorrow(env.getProperty("test.on.borrow", Boolean.class, true));
		dataSource.setTestOnReturn(env.getProperty("test.on.return", Boolean.class, true));
		dataSource.setTestWhileIdle(env.getProperty("test.while.idle", Boolean.class, true));
		return dataSource;
	}
}
