package kz.evo.screensaver;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

// ����� ��������� scope ����� �������������� ���� �����
// � ����� �� ������� ������������ scope periodical
@Component
public class CustomScopeRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // ��������� ����� ���� scope, ����� �� periodical scope
        beanFactory.registerScope("periodical", new PeriodicalScopeConfigurer());
    }
}
