package aop.self;

public class Dog implements BaseService {
    @Override
    public void eat() {
        System.out.println("Dog eat");
    }

    @Override
    public void play() {
        System.out.println("Dog play");
    }
}
