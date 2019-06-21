package designmodel.singleton;

import java.io.*;

public class SingletonDemo {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tempfile"));
            outputStream.writeObject(Singleton.getInstance());

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("tempfile"));
            Singleton newInstance = (Singleton) objectInputStream.readObject();

            System.out.println(newInstance == Singleton.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
