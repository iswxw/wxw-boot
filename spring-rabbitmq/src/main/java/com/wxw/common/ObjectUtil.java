package com.wxw.common;

import java.io.*;

/**
 * @author ：wxw.
 * @date ： 13:20 2021/1/25
 * @description：
 * @link:
 * @version: v_0.0.1
 */
public class ObjectUtil {

    /**
     * 对象转byte[]
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[] objectToBytes(Object obj) throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        byte[] bytes = bo.toByteArray();
        bo.close();
        oo.close();
        return bytes;
    }

    /**
     * byte[]转对象
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object bytesToObject(byte[] bytes) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream sIn = new ObjectInputStream(in);
        return sIn.readObject();
    }
}
