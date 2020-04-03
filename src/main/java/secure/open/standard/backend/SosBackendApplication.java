package secure.open.standard.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@EnableSwagger2()
@ComponentScan(basePackages = { "secure.open.standard.backend", "secure.open.standard.backend.api" , "secure.open.standard.backend.config"})
public class SosBackendApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(SosBackendApplication.class);
  }

  public static void main(final String[] args) {
    SpringApplication.run(SosBackendApplication.class, args);
  }



}
