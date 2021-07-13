package cn.edu.zucc.wyk31801026;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("cn.edu.zucc.wyk31801026.mapper")
//开启swaggerUi
@EnableSwagger2
@SpringBootApplication
public class SalmonellaSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalmonellaSystemApplication.class, args);
	}

}
