package co.techo.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@Data
@PropertySource(value = "application.properties", encoding = "UTF-8")
public class Config {

    @Value("${application.export.excel.path}")
    private String exportPath;

    @Value("${application.export.excel.course.signing.filename}")
    private String courseSigningFileName;
}
