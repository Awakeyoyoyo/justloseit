package com.awake.orm.codec.basetype;


import com.awake.orm.codec.MapKeyCodec;

/**
 * @Author：lqh
 * @Date：2024/6/17 11:38
 */
public class FloatMapCodec implements MapKeyCodec<Float> {

    @Override
    public String encode(Float value) {
        return value.toString();
    }

    @Override
    public Float decode(String text) {
        return (text == null) ? null : Float.valueOf(text);
    }
}
