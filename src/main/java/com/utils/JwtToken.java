package com.utils;

import com.ApiKey;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * APP登录Token的生成和解析
 *
 */
public class JwtToken {

    /** token 过期时间: 10天 */
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;

    /**
     * JWT生成Token.<br/>
     *
     * JWT构成: header, payload, signature
     *
     *            登录成功后用户user_id, 参数user_id不可传空
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


}

