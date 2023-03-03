package project.board.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@MapperScan("com.project.board.mapper")
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")  // application.properties에 작성한 내용을 사용할 수 있도록 위치를 지정해줍니다. 여러개 가능
public class DatabaseConfiguration {
	@Autowired
	private ApplicationContext applicationContext;

	@Bean        //application.properties에 설정했던 데이터베이스 관련 정보를 사용하도록 지정합니다.
	@ConfigurationProperties(prefix="spring.datasource.hikari")  //prefix를 spring.datasource.hikari로 지정하였기 때문에 spring.datasource.hikari로 시작하는 설정을 이용합니다.
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception {      //hikariConfig를 이용하여 데이터베이스와 연결하는 DataSource를 생성합니다.
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());  //확인하기 위해
		return dataSource;
	}
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // mapper xml을 찾는 경로
        System.out.println("Mapper Resources::"+sqlSessionFactoryBean.getObject());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/boardMapper.xml"));
        // 해당 코드 한줄만 추가 
        sqlSessionFactoryBean.setConfiguration(mybatisConfig());
        return sqlSessionFactoryBean.getObject();
    }

	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
	    return new org.apache.ibatis.session.Configuration();		
	}
}