package cp.moneytransferapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")
    @ConfigurationProperties(prefix = "transfer")
    public AppProperties appProperties() {
        return new AppProperties();
    }

}