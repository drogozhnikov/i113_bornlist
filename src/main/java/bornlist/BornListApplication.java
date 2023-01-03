package bornlist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "BornList"))
public class BornListApplication {

    public static void main(String[] args) {
        SpringApplication.run(BornListApplication.class, args);
    }
}
