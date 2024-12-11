package com.wty.network;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 * @author peter
 * @date 2023/1/12 11:15
 */
public class Test {

    public static void main(String[] args) {
        String bodyStr = "{\"id\":\"de3ec66e-62f2-4c99-959b-b1935699d847\",\"time\":\"2023-01-12T01:46:32.517Z\",\"event\":\"trade.expired\",\"context\":{\"traderId\":\"3413277\",\"partnerContext\":\"26920156-6f85-4b56-83b3-107295d45bf4\",\"residenceCountry\":\"SK\",\"id\":\"4246932\"}}";
        String coinifySecretKey = "ba4a3dbb-1928-4b9b-a89d-80da6972b862";
        String bodySignature = sign(bodyStr, coinifySecretKey);
        System.out.println(bodySignature);
    }


    public static String sign(String data, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(data.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }
}
