package kz.evo.context;

import kz.evo.service.Quoter;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

// Deprecated propertyContext ��� property ������������
public class PropertyFileApplicationContext extends GenericApplicationContext {
    public PropertyFileApplicationContext(String fileName) {
        // ����� ��, ��� � xmlBeanDefinitionReader, �� ��������� properties �����
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(this);
        // ��������� ��� beanDefinition �� �����
        int beanNum = propertiesBeanDefinitionReader.loadBeanDefinitions(fileName);

        System.out.println("found " + beanNum + " beans");

        // ���������, ��� ������ context - ��� refresh (����� ���������� ������� ���������� �����)
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext propertyFileApplicationContext = new PropertyFileApplicationContext("context.properties");
        propertyFileApplicationContext.getBean(Quoter.class).say();
    }
}