package com.wxw.common.tools;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * @author: wxw
 * @date: 2020-11-30-23:01
 */
public class MyZkSerializer implements ZkSerializer {

    String charset = "UTF-8";

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }

    public byte[] serialize(Object obj) throws ZkMarshallingError {
        try {
            return String.valueOf(obj).getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }
}
