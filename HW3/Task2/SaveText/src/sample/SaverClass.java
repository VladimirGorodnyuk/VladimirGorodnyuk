package sample;
//Написать класс TextContainer, который содержит в себе строку. С помощью механизма
//        аннотаций указать 1) в какой файл должен сохраниться текст 2) метод, который выполнит
//        сохранение. Написать класс Saver, который сохранит поле класса TextContainer в указанный
//        файл.
//@SaveTo(path=“file.txt”)
//class Container {
//    String text = “…”;
//    @Saver
//   public void save(..) {…}
//}

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SaverClass {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

            TextContainer textcontainer = new TextContainer();
            Class<?> cls = textcontainer.getClass();
            String fileadress=null;
        if(cls.isAnnotationPresent(SaveTo.class))
        {
            SaveTo testannotation=cls.getAnnotation(SaveTo.class);
            fileadress=testannotation.path();
        }
        Method[] methods=cls.getDeclaredMethods();
        for (Method method:methods) {
            if(method.isAnnotationPresent(Saver.class))
            {
                method.invoke(new TextContainer(),TextContainer.text,fileadress);
            }
        }


    }
}