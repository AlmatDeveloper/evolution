package kz.evo.selfinjection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// данный BeanPostProcessor сработает самым последним, так как реализуем интерфейс Ordered
@Component
public class SelfInjectAnnotationBeanPostProcessor implements BeanPostProcessor, Ordered {

    Map<String, Object> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(SelfInject.class)) {
                map.put(beanName, bean);
                break;
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object originalObject = map.get(beanName);

        if (originalObject != null) {
            Field[] declaredFields = originalObject.getClass().getDeclaredFields();

            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(SelfInject.class)) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, bean, bean);
                }
            }
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}