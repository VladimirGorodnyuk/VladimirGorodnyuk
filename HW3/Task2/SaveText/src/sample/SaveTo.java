package sample;

import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveTo {
   String path();
}
