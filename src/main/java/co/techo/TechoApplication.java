package co.techo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechoApplication.class, args);
	}

//	@Bean
//	public PropertiesFactoryBean propertiesfilemapping() {
//		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
//		factoryBean.setFileEncoding("UTF-8");
//		factoryBean.setLocation(new ClassPathResource("application.properties"));
//		return factoryBean;
//	}
}
