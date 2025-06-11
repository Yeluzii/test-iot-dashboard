package fun.ychen.iot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class IotDashboardApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotDashboardApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner printKnife4jUrl(
            @Value("${server.port}") int port,
            @Value("${server.address}") String address) {
        return args -> {
            String baseUrl = "http://" + address + ":" + port;
            String knife4jUrl = baseUrl + "/doc.html";
            System.out.println("Knife4J 文档地址: " + knife4jUrl);
        };
    }

}
