package cn.edu.ynu.demo_app.extensions.java.util.Date;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.text.SimpleDateFormat;
import java.util.Date;

@Extension
public class DateExtension {
    public static String toString(@This Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
