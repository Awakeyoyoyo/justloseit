package com.awake.orm.codec.basetype;


import com.awake.orm.codec.MapKeyCodec;

/**
 * @Author：lqh
 * @Date：2024/6/17 11:35
 */
public class DoubleMapCodec implements MapKeyCodec<Double> {

    @Override
    public String encode(Double value) {
        return value.toString();
    }

    @Override
    public Double decode(String text) {
        return (text == null) ? null : Double.valueOf(text);
    }
}
