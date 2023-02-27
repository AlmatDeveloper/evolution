package kz.evo.screensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "kz.evo.screensaver")
public class Config {
    @Bean
    // �������� prototype � singleton
    // 1 - ������ ����� proxyMode:
    // ��� ����� ������ ����� ���������� ����� ��� (���� ���� �������� � ����� ����� (������))
    // @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    // 2 - ������ ����� ����������� ����� (���������� �����)
    // @Scope("prototype")
    //
    // ��������� scope - periodical
    @Scope("periodical")
    public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    // �������� prototype � singleton, 2 - ������ ����� ����������� �����
    @Bean
    public ColorFrame colorFrame() {
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                // ��� �� ����� ������, � ������ ����� ������ bean
                return color();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        // �������� ��� ������, ������� �������� �������������� (�����) ���� ����� � ������ ��� ��� ����������������� ���������� ���������
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);

        while (true) {
            annotationConfigApplicationContext.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(100);
        }
    }
}
