package io.themis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.sql.SQLException;

/**
 * 主入口程序
 *
 */
@SpringBootApplication(scanBasePackages={"io.themis"})
public class Application
{
    public static ApplicationContext applicationContext;
    public Application() {
    }

    public static void main(String[] args ) throws SQLException, InterruptedException {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(
                Application.class)
                .run(args);
    }
}
