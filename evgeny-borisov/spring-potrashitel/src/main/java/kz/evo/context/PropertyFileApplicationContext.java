package kz.evo.context;

import kz.evo.service.Quoter;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

// Deprecated propertyContext для property конфигурации
public class PropertyFileApplicationContext extends GenericApplicationContext {
    public PropertyFileApplicationContext(String fileName) {
        // такой же, как и xmlBeanDefinitionReader, но сканирует properties файлы
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(this);
        // загружаем все beanDefinition из файла
        int beanNum = propertiesBeanDefinitionReader.loadBeanDefinitions(fileName);

        System.out.println("found " + beanNum + " beans");

        // последнее, что делает context - это refresh (когда закончился процесс добавления bean - ов)
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext propertyFileApplicationContext = new PropertyFileApplicationContext("context.properties");
        propertyFileApplicationContext.getBean(Quoter.class).say();
    }
}