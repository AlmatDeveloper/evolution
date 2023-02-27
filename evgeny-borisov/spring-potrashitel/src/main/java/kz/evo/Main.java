package kz.evo;

import kz.evo.service.Quoter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        // есть множество реализаций контекста (xml, properties, annotation, ...)
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Quoter.class).say();
//        while (true) {
//            Thread.sleep(1000);
//            context.getBean(Quoter.class).say();
//        }
    }
}

// BeanDefinition (BD) - объекты, которые хранят информацию о bean.
// обычная Map, id bean - декларация(из какого класса создавать, имеет ли init метод, какие property, ...)

// setter - обязательны для property, чтобы spring понимал куда вставлять значения
// ClassPathXmlApplicationContext сканируется XmlBeanDefinitionReader - ом

// BeanFactory (BF), создает объекты из наших классов и складывает их в контейнер.
// в начале он смотрит на те bean которые имплементируют BPP, и с помощью их настраивает остальные "простые" bean
// BeanFactory, для настройки bean проходится два раза по BPP, до и после init метода, из-за этого BPP имеет два метода

// Singleton создается на этапе поднятия контекста
// Prototype создаются когда они нужны, spring создаст, настроит, отдаст и забудет про него (из-за этого destroy методы не будут работать)

// BeanPostProcessor (BPP) - настраивает наши bean до того как они попадают в контейнер
// второй метод в BPP нужен, если мы меняем что-то в классе. Так мы уверены что получаем именно тот bean который нам нужен
// id bean - у дают если мы хотим его использовать где-то в коде
// если в конструкторе вызвать то что настраивает Spring, то это не сработает. Для этого нужны init методы
// объекты настраиваются после создания
// так же init методы - это двухуровневые конструкторы
// аннотации обрабатываются с помощью beanPostProcessor

// есть два способа создания bean на лету: dynamicProxy(имплементировать те жи методы, что и класс) и CGLib(наследовать на прямую от класса(медленнее, есть ограничения(final методы ...)))
// BBP который отвечает за логику Transactional инициализирует логику во втором методе(после init метода)

// Listener слушает context Spring (все event которые там происходит)
// contextStarted - контекст начал свое построение(но еще не построился)
// contextRefresh - делается всегда после построения (в большинстве случаях слушается именно этот event)

// на этапе PostConstruct нет никаких прокси
// если в классе 2 PostConstruct, сработают оба

// есть 3 фазы "конструктора" -> 1: сам конструктор (ничего не настроено и не внедряется),
//                               2: PostConstruct (настроены некоторые bean, но не такие, как @Transactional, вызывается после первого метода BPP (нет proxy))
//                               3: ApplicationListener (настроены все bean, вызывается после второго метода BPP, вызывается refresh event)

// BeanFactoryPostProcessor (BFPP) срабатывает раньше чем BPP
// BFPP служит для настройки BeanDefinition на этапе обработки
// может так же поднастроить и сам BeanFactory до начала его работы

// IoC container - обычный HashMap

// есть отдельные классы для сканирования xml, java, groovy конфигураций

// если bean singleton, то все bean которые prototype в него внедряются не будут меняться
// тут вопрос - как обновлять prototype в singleton

// в proxy классе нет никаких аннотаций