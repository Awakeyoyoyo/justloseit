package com.awake.orm.codec.basetype;


import com.awake.orm.codec.MapKeyCodec;

/**
 * @Author：lqh
 * @Date：2024/6/17 11:32
 */
public class BooleanMapCodec implements MapKeyCodec<Boolean> {


    @Override
    public String encode(Boolean value) {
        return value ? "True" : "False";
    }

    @Override
    public Boolean decode(String text) {
        if (text == null) {
            throw new NullPointerException();
        } else if (isValidName(true, text)) {
            return true;
        } else if (isValidName(false, text)) {
            return false;
        } else {
            throw new IllegalArgumentException(text);
        }
    }

    private String getValidName(boolean value) {
        return value ? "True" : "False";
    }

    private boolean isValidName(boolean value, String name) {
        return getValidName(value).equalsIgnoreCase(name);
    }
}
