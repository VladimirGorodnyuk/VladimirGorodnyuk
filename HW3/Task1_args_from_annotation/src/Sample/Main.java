package Sample;

import java.lang.annotation.*;
import java.lang.reflect.*;

//Создать аннотацию, которая принимает параметры для метода. Написать код, который
//        вызовет метод, помеченный этой аннотацией, и передаст параметры аннотации в
//        вызываемый метод.


public class Main {
    @Target(value = ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
        int a();

        int b();
    }

    public static class sum {
        @TestAnnotation(a = 2, b = 5)
        public void test(int a, int b) {
            System.out.println("Sum of args = " + (a + b));
        }

        public void test1(int a, int b) {
            System.out.println(a + "+" + b);
        }
    }

    public static void main(String[] args) {
        Class<?> cls = sum.class;
        Method methods[] = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(TestAnnotation.class)) {
                TestAnnotation ta = method.getAnnotation(TestAnnotation.class);
                try {
                    System.out.println(method.getName() + " is annotated method with parameters: " + ta.a() + " and " + ta.b());
                    method.invoke(new sum(), ta.a(), ta.b());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else System.out.println(method.getName() + " is no such annotated method");

        }
    }
}