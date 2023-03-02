package kz.evo.selfinjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "kz.evo.selfinjection")
@Configuration
public class Config {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        annotationConfigApplicationContext.getBean(ExampleService.class).method1();
    }
}
