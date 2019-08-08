package jvm;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class MethodHandle {

    private static void test(Object o) {

    }

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }
}

class TestMethodHandle {

    public void test() throws NoSuchMethodException, IllegalAccessException {
        // 通过反射获取方法句柄
        MethodHandles.Lookup lookup = MethodHandle.lookup();
        Method m = MethodHandle.class.getDeclaredMethod("test", Object.class);
        java.lang.invoke.MethodHandle unreflect = lookup.unreflect(m);

        // 通过句柄类型获取
        MethodType methodType = MethodType.methodType(void.class, Object.class);
        java.lang.invoke.MethodHandle test = lookup.findStatic(MethodHandle.class, "test", methodType);
    }

    public void test(java.lang.invoke.MethodHandle methodHandle, String s) throws Throwable {
        methodHandle.invokeExact(s);
        methodHandle.invokeExact((Object)s);
    }
}
