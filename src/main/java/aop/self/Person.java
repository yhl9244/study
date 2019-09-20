package aop.self;

public class Person implements BaseService {
    @Override
    public void eat() {
        System.out.println("Person eat");
    }

    @Override
    public void play() {
        System.out.println("Person play");
    }
}
