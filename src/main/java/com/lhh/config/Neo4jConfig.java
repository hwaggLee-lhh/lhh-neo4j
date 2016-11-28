package com.lhh.config;
/*package com.qinghuainvest.config;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
// 启动类的@SpringBootApplication会自动扫描同级包以及子包，所以下面的@ComponentScan不加应该没关系
// @ComponentScan("cn.didadu.sdn")
@EnableNeo4jRepositories("cn.didadu.sdn.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

	@Bean
	public Configuration getConfiguration() {
		Configuration config = new Configuration();
		config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
		.setURI("http://neo4j:zhangjing@localhost:7474");
		
		return config;
	}

	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "cn.didadu.sdn.entity");
	}
	
	
}
*/