package com.yeweiyang.token.utils;

import cn.dev33.satoken.secure.SaBase64Util;
import cn.dev33.satoken.secure.SaSecureUtil;

import java.util.HashMap;

/**
 * @author Jay
 * @version V1.0
 * @Package com.shanghai.test1114.utils
 * @date 2022/1/29 11:25 上午
 * 密码加密解密工具类🔐
 */
public class SaTokenPasswordUtils {
        private static final String KEY_ = "";
        //私钥
        private static final String privateKey = "";
        //公钥
        private static final String publicKey = "";

        /**
         * AES加密
         */
        public static String setAesEncrypt(String key,String password){
                // 加密
                String ciphertext = SaSecureUtil.aesEncrypt(key, password);
                System.out.println("AES加密后：" + ciphertext);
                return ciphertext;
        }
        /**
         * AES解密
         */
        public static String getAesDecrypt(String key,String ciphertext){
                // 解密
                String aesDecrypt = SaSecureUtil.aesDecrypt(key, ciphertext);
                System.out.println("AES解密后：" + aesDecrypt);
                return aesDecrypt;
        }
        /**
         * RSA加密
         */
        public static String setRsaEncryptByPublic(String privateKey,String publicKey,String text){
                // 定义私钥和公钥
//                privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO+wmt01pwm9lHMdq7A8gkEigk0XKMfjv+4IjAFhWCSiTeP7dtlnceFJbkWxvbc7Qo3fCOpwmfcskwUc3VSgyiJkNJDs9ivPbvlt8IU2bZ+PBDxYxSCJFrgouVOpAr8ar/b6gNuYTi1vt3FkGtSjACFb002/68RKUTye8/tdcVilAgMBAAECgYA1COmrSqTUJeuD8Su9ChZ0HROhxR8T45PjMmbwIz7ilDsR1+E7R4VOKPZKW4Kz2VvnklMhtJqMs4MwXWunvxAaUFzQTTg2Fu/WU8Y9ha14OaWZABfChMZlpkmpJW9arKmI22ZuxCEsFGxghTiJQ3tK8npj5IZq5vk+6mFHQ6aJAQJBAPghz91Dpuj+0bOUfOUmzi22obWCBncAD/0CqCLnJlpfOoa9bOcXSusGuSPuKy5KiGyblHMgKI6bq7gcM2DWrGUCQQD3SkOcmia2s/6i7DUEzMKaB0bkkX4Ela/xrfV+A3GzTPv9bIBamu0VIHznuiZbeNeyw7sVo4/GTItq/zn2QJdBAkEA8xHsVoyXTVeShaDIWJKTFyT5dJ1TR++/udKIcuiNIap34tZdgGPI+EM1yoTduBM7YWlnGwA9urW0mj7F9e9WIQJAFjxqSfmeg40512KP/ed/lCQVXtYqU7U2BfBTg8pBfhLtEcOg4wTNTroGITwe2NjL5HovJ2n2sqkNXEio6Ji0QQJAFLW1Kt80qypMqot+mHhS+0KfdOpaKeMWMSR4Ij5VfE63WzETEeWAMQESxzhavN1WOTb3/p6icgcVbgPQBaWhGg==";
//                publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDvsJrdNacJvZRzHauwPIJBIoJNFyjH47/uCIwBYVgkok3j+3bZZ3HhSW5Fsb23O0KN3wjqcJn3LJMFHN1UoMoiZDSQ7PYrz275bfCFNm2fjwQ8WMUgiRa4KLlTqQK/Gq/2+oDbmE4tb7dxZBrUowAhW9NNv+vESlE8nvP7XXFYpQIDAQAB";
                // 使用公钥加密
                String ciphertext = SaSecureUtil.rsaEncryptByPublic(publicKey, text);
                System.out.println("公钥加密后：" + ciphertext);

                return ciphertext;
        }
        /**
         * RSA解密
         */
        public static String getRsaDecryptByPrivate(String privateKey, String publicKey, String rsaDecryptByPrivate){
                // 定义私钥和公钥
//                privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO+wmt01pwm9lHMdq7A8gkEigk0XKMfjv+4IjAFhWCSiTeP7dtlnceFJbkWxvbc7Qo3fCOpwmfcskwUc3VSgyiJkNJDs9ivPbvlt8IU2bZ+PBDxYxSCJFrgouVOpAr8ar/b6gNuYTi1vt3FkGtSjACFb002/68RKUTye8/tdcVilAgMBAAECgYA1COmrSqTUJeuD8Su9ChZ0HROhxR8T45PjMmbwIz7ilDsR1+E7R4VOKPZKW4Kz2VvnklMhtJqMs4MwXWunvxAaUFzQTTg2Fu/WU8Y9ha14OaWZABfChMZlpkmpJW9arKmI22ZuxCEsFGxghTiJQ3tK8npj5IZq5vk+6mFHQ6aJAQJBAPghz91Dpuj+0bOUfOUmzi22obWCBncAD/0CqCLnJlpfOoa9bOcXSusGuSPuKy5KiGyblHMgKI6bq7gcM2DWrGUCQQD3SkOcmia2s/6i7DUEzMKaB0bkkX4Ela/xrfV+A3GzTPv9bIBamu0VIHznuiZbeNeyw7sVo4/GTItq/zn2QJdBAkEA8xHsVoyXTVeShaDIWJKTFyT5dJ1TR++/udKIcuiNIap34tZdgGPI+EM1yoTduBM7YWlnGwA9urW0mj7F9e9WIQJAFjxqSfmeg40512KP/ed/lCQVXtYqU7U2BfBTg8pBfhLtEcOg4wTNTroGITwe2NjL5HovJ2n2sqkNXEio6Ji0QQJAFLW1Kt80qypMqot+mHhS+0KfdOpaKeMWMSR4Ij5VfE63WzETEeWAMQESxzhavN1WOTb3/p6icgcVbgPQBaWhGg==";
//                publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDvsJrdNacJvZRzHauwPIJBIoJNFyjH47/uCIwBYVgkok3j+3bZZ3HhSW5Fsb23O0KN3wjqcJn3LJMFHN1UoMoiZDSQ7PYrz275bfCFNm2fjwQ8WMUgiRa4KLlTqQK/Gq/2+oDbmE4tb7dxZBrUowAhW9NNv+vESlE8nvP7XXFYpQIDAQAB";
                // 使用私钥解密
                String decryptByPrivate = SaSecureUtil.rsaDecryptByPrivate(privateKey, rsaDecryptByPrivate);
                System.out.println("私钥解密后：" + decryptByPrivate);
                return decryptByPrivate;
        }
        /**
         * 生成RSA密钥对
         */
        public static HashMap<String, String> getRsaGenerateKeyPair(){
                // 生成一对公钥和私钥，其中Map对象 (private=私钥, public=公钥)
                HashMap<String, String> generateKeyPair = null;
                try {
                        generateKeyPair = SaSecureUtil.rsaGenerateKeyPair();
                } catch (Exception e) {
                        System.out.println("==========rsaGenerateKeyPair密钥对生成失败=============");
                        e.printStackTrace();
                }
                System.out.println("RSA密钥对私钥==>private:         "+generateKeyPair.get("private"));
                System.out.println("RSA密钥对公钥==>public           "+generateKeyPair.get("public"));
                return generateKeyPair;
        }
        /**
         * base64编码
         */
        public static String setEncodeBase64(String text){
                return SaBase64Util.encode(text);
        }
        /**
         * base64解码
         */
        public static String getDecodeBase64(String encodeBase64Text){
                return SaBase64Util.decode(encodeBase64Text);
        }

}
