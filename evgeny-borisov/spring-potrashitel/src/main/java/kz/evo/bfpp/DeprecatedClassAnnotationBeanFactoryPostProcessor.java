package kz.evo.bfpp;

import kz.evo.annotation.DeprecatedClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

// BeanFactoryPostProcessor используем, если хотим изменить поведение bean до обработки BeanFactory
// меняем напрямую в BeanDefinition
public class DeprecatedClassAnnotationBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            // искать описания bean по имени через фабрику
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);

            // найти оригинальное название класса
            String beanClassName = beanDefinition.getBeanClassName();

            try {
                Class<?> originalClass = Class.forName(beanClassName);

                DeprecatedClass annotation = originalClass.getAnnotation(DeprecatedClass.class);

                if (annotation != null) {
                    // устанавливаем название нового класса
                    beanDefinition.setBeanClassName(annotation.newImpl().getName());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
