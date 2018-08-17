package com.utils;

import com.ApiKey;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.crypto.provider.HmacMD5;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * APP登录Token的生成和解析
 */
public class JwtToken {

    /**
     * token 过期时间: 10天
     */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     * <p>
     * 登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(long serverTIme, ApiKey apiKey) throws Exception {
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create().withHeader(map)
                .withClaim("type", "OpenAPI")
                .withClaim("sub", apiKey.getApiKey())
                .withClaim("nonce", serverTIme)
                .sign(Algorithm.HMAC256(apiKey.getSecret()));

        return token;
    }

    public static String hmacMd5(String cmds, String sec) throws Exception {
        SecretKey secretKey = new SecretKeySpec(sec.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return new String(mac.doFinal(cmds.getBytes()),"UTF-8");
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }


    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}

