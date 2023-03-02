package kz.evo.selfinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleService exampleServiceProxy;

    @Override
    public void method1() {
        System.out.print("he");
        exampleServiceProxy.method2();
    }

    public void method2() {
        System.out.println("llo");
    }
}
