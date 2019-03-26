package com.wisdomrider.sqliteclosedhelper;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
   Created By WisdomRider(Avishek Adhikari)

    Email : avishekzone@gmail.com

    Make Sure to Star Us On Github :
       https://github.com/wisdomrider/SqliteClosedHelper

     Credit Me SomeWhere In Your Project :)

     Thanks !!
*/

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotations {
    @Documented()
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Exclude {
    }

}
