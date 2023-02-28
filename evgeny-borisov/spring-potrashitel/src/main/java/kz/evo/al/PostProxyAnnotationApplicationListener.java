package kz.evo.al;

import kz.evo.annotation.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

// внедрять spring в spring - нормально, внедрять spring в свой bean - плохо (пример - внедрить context в свой bean)
// можно выбирать нужный еvent, чтобы не проверять каждый раз на нужный (instanceof)

// используем ApplicationListener для вызова метода, после создания всех proxy классов (пример - Transactional)
// пример - наполнить кэш данными
public class PostProxyAnnotationApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    // внедрение spring компонента в spring
    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // каждый event знает свой контекст
        ApplicationContext applicationContext = event.getApplicationContext();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        // находить по названию bean объект и делать getClass не получится, т.к. это уже прокси
        for (String beanDefinitionName : beanDefinitionNames) {
            // искать описания bean по имени через фабрику
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanDefinitionName);

            // найти оригинальное название класса
            String beanClassName = beanDefinition.getBeanClassName();

            try {
                Class<?> originalClass = Class.forName(beanClassName);

                for (Method method : originalClass.getMethods()) {
                    if (method.isAnnotationPresent(PostProxy.class)) {
                        // method.invoke() не сработает т.к. это вызов метода оригинального класса, нам нужен proxy класс (бин)
                        Object bean = applicationContext.getBean(beanDefinitionName);
                        // это proxy, т.к. вызываем из bean
                        bean.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(bean);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
