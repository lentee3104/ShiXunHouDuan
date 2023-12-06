package cn.edu.ynu.demo_app.extensions.java.time.LocalDateTime;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Extension
public class LocalDateTimeExtension {
    public static String toString(@This LocalDateTime thiz, String format){
        return thiz.format(DateTimeFormatter.ofPattern(format));
    }
}
