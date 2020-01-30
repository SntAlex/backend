package ftc.shift.sample;

import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

public class H2ServerProperties {
    @Bean(initMethod="start",destroyMethod="stop")
    public org.h2.tools.Server h2WebConsoleServer () throws SQLException {
        return org.h2.tools.Server.createWebServer("-web","-webAllowOthers","-webDaemon","-webPort", "8081");
    }
}
