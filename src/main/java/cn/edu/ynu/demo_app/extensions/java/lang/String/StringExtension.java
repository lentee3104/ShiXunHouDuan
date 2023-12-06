package cn.edu.ynu.demo_app.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.util.UUID;

@Extension
public class StringExtension {

    public static UUID toUUID(@This String str) {
        return UUID.fromString(str);
    }
}

