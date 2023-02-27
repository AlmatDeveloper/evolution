package kz.evo.screensaver;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

// любой кастомный scope будет регистрировать этот класс
// в нашем же примере регистрируем scope periodical
@Component
public class CustomScopeRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // добавляем новые виды scope, здесь же periodical scope
        beanFactory.registerScope("periodical", new PeriodicalScopeConfigurer());
    }
}
