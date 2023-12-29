package developapp.jp.workspace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import developapp.jp.workspace.service.DatabaseConnector;
//未使用
@Configuration
public class AppConfig {

    @Bean
    public DatabaseConnector databaseConnector() {
        return new DatabaseConnector();
    }
}
