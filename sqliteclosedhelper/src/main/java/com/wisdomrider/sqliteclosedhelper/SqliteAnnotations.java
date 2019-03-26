package com.wisdomrider.sqliteclosedhelper;

import android.support.annotation.RequiresPermission;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
   Created By WisdomRider(Avishek Adhikari)

    Email : avishekzone@gmail.com

    Make Sure to Star Me On Github :
       https://github.com/wisdomrider/SqliteClosedHelper

     Credit Me SomeWhere In Your Project :)

     Thanks !!
*/

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqliteAnnotations {
    @Documented()
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Primary {
    }

    @Documented
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface AutoIncrement {
    }

    @Documented
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Unique {
    }

    @Documented
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Nullable {
        boolean isNullable() default true;
    }



}
