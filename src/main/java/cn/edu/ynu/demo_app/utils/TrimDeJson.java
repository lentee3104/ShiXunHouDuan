package cn.edu.ynu.demo_app.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class TrimDeJson extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        var str = jsonParser.getText();
        try {
            return (str != null)? str.trim(): null;
        }catch (Exception e){
            throw new IOException("字符串去除空格失败");
        }
    }
}
