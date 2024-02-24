package by.valashchuk.migration_tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class MigrationToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MigrationToolsApplication.class, args);
    }

}
