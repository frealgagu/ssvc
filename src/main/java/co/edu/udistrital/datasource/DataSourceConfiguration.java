package co.edu.udistrital.datasource;

import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

@Configuration
//***@PropertySource("../../../../../webapp/WEB-INF/configuration.properties")
public class DataSourceConfiguration {

	@Autowired
	private Environment env;

    @Value("${database.host}")
    private String host;
    @Value("${database.port}")
    private int port;
    @Value("${database.name}")
    private String databaseName;
    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;

    @Value("${max.active}")
    private int maxActive;
    @Value("${max.idle}")
    private int maxIdle;
    @Value("${max.wait}")
    private int maxWait;
    @Value("${test.on.borrow}")
    private boolean testOnBorrow;
    @Value("${test.on.return}")
    private boolean testOnReturn;
    @Value("${test.while.idle}")
    private boolean testWhileIdle;
	
	@Bean
	public SharedPoolDataSource dataSource() {
		SharedPoolDataSource dataSource = new SharedPoolDataSource();

		MysqlConnectionPoolDataSource poolDataSource = new MysqlConnectionPoolDataSource();
		poolDataSource.setServerName(host);
		poolDataSource.setPortNumber(port);
		poolDataSource.setDatabaseName(databaseName);
		poolDataSource.setUser(username);
		poolDataSource.setPassword(password);
		dataSource.setConnectionPoolDataSource(poolDataSource);
		
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMaxWait(maxWait);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setTestWhileIdle(testWhileIdle);
		return dataSource;
	}
}
