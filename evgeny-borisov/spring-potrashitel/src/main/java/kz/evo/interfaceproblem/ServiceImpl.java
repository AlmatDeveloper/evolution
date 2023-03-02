package kz.evo.interfaceproblem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.io.Serializable;

// что бы сработал dynamicProxy нужен хотя бы один интерфейс с одним методом
// иначе может произойти ошибка как с Comparable (создастся бин как Comparable)
@org.springframework.stereotype.Service
public class ServiceImpl implements Service, Serializable, Comparable {
    @Override
    public void method() {
        System.out.println("hello hello");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("kz.evo.interfaceproblem");
        annotationConfigApplicationContext.getBean(Service.class).method();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}