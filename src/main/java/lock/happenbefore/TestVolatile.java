package lock.happenbefore;

public class TestVolatile {

    private int x = 0;
    private int y = 1;
    private volatile boolean flag = false;

    public void writer(){
        x = 42;    //1
        y = 50;    //2
        flag = true;    //3
    }

    public void reader(){
        if (flag){    //4
            System.out.println("x:" + x);    //5
            System.out.println("y:" + y);    //6
        }
    }

    public static void main(String[] args) {
        TestVolatile testVolatile = new TestVolatile();
        Thread t1 = new Thread(() -> testVolatile.writer());

        Thread t2 = new Thread(() -> testVolatile.reader());
        t1.start();
        t2.start();
        System.out.println("主线程退出");
    }

    // 可能出现不输出i,但是不会出现i=0;
}
