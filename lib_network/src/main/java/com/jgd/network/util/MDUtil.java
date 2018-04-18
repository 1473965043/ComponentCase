package com.jgd.network.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by guodong on 2018/4/18.
 */

public class MDUtil {
    public enum TYPE {
        MD2("MD2"),
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA224("SHA-224"),
        SHA256("SHA-256"),
        SHA384("SHA-384"),
        SHA512("SHA-512");
        private String value;

        TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static String encode(TYPE type, String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(type.getValue());
            digest.update(data.getBytes());
            return bytes2Hex(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * 32位加密补零
     * @param bts
     * @return
     */
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static String FileMD(TYPE type, File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(type.getValue());

            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int    length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, length);
            }
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return file.getPath();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String key  = "password";
        String data = "I believe you can.";
        System.out.println("待MD字符串：" + data);
        System.out.println(encode(TYPE.MD2, data));

        //path表示你所创建文件的路径
        String path      = "D:/";
        File   directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // fileName表示你创建的文件名；为txt类型；
        String fileName = "test.txt";
        File   file     = new File(directory, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println("待MD文件：" + file.getAbsolutePath());
        System.out.println(FileMD(TYPE.MD5, file));
    }
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            //使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                //System.out.println((int)b);
                //将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

}