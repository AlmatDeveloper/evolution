package kz.evo.bpp;

import kz.evo.annotation.Profiling;
import kz.evo.mbean.ProfilingController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// Первый метод работает с оригинальным классом
// Второй метод работает с прокси классом

// Если мы настраиваем объект, то логику пишем в первом методе
// Если мы меняем поведение объекта путем создания прокси, то логику пишем во втором методе
// Прокси нужно делать после инит метода
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();

    private ProfilingController profilingController = new ProfilingController();

    public ProfilingAnnotationBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer
                .registerMBean(profilingController, new ObjectName("profiling", "name", "profilingController"));
    }

    // Все аннотации ищутся в оригинальном классе
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, beanClass);
        }

        return bean;
    }

    // Логику пишем во втором методе, чтобы быть уверенными, что меняем именно тот бин который нам нужен
    // В данный метод не попадает оригинальный класс с аннотациями.
    // Попадает прокси класс в котором нет аннотаций
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // Используется Map, так как есть возможность что спринг нам отдаст не тот прокси
        // отвязываемся от логики getClass
        Class beanClass = map.get(beanName);

        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (o, method, objects) -> {
                if (profilingController.isEnabled()) {
                    System.out.println("Профилирую...");

                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, objects);
                    long after = System.nanoTime();

                    System.out.println(after - before);
                    System.out.println("Все");

                    return retVal;
                } else {
                    return method.invoke(bean, objects);
                }
            });
        }
        // статичный метод который сгенерит на лету новый объект
        // имеет 3 аргумента: 1 - classLoader который загрузит в HEAP новый объект
        // (должен грузить именно тот ClassLoader который загружал предыдущий bean)
        // 2 - список интерфейсов который должен имплементировать тот класс который сгенерится на лету
        // 3 - инкапсулирует логику которая попадет во все методы класса который сгенерится на лету

        // Любой класс знает какой ClassLoader его загрузил

        return bean;
    }
}
