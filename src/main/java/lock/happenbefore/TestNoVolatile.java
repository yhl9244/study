package lock.happenbefore;

public class TestNoVolatile {

    int i = 0;
    boolean flag = false;

    public void writer() {
        i = 123;
        flag = true;
    }

    public void reader() {
        if (flag) {
            System.out.println("i=" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestNoVolatile testNoVolatile = new TestNoVolatile();
        Thread t1 = new Thread(() -> testNoVolatile.writer());

        Thread t2 = new Thread(() -> testNoVolatile.reader());
        t1.start();
        t2.start();
        System.out.println("主线程退出");
    }

    // 可能出现不输出i或者i=0
}
