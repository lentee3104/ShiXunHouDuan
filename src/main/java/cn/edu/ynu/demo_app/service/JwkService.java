package cn.edu.ynu.demo_app.service;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;

/** 用于生成和管理 JWK（Json Web Key） */
@Service
@CommonsLog
public class JwkService {
    // 用于保存密钥对的文件名
    private static final String RSA_KEY_FILE_NAME = "demo-app-rsa.key";

    private KeyPair keyPair;

    public JwkService() throws Exception {
        init();
    }

    public RSAPublicKey getPublicKey() {
        return (RSAPublicKey) this.keyPair.getPublic();
    }

    public JWKSet jwkSet() {
        var rsaKey = new RSAKey.Builder(getPublicKey()).privateKey(this.keyPair.getPrivate()).build();
        return new JWKSet(rsaKey);
    }

    /* 用于验证 JWT 的解码器,
    // 由于 Spring Security OAuth2 已经提供了，所以不需要自己实现
    // 但要在配置文件中给出用于获取公钥的 URL
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(getPublicKey()).build();
    }
    */

    // JWT 编码器
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableJWKSet<SecurityContext>(jwkSet()));
    }

    public String encodeJwt(JwtClaimsSet claims) {
        return this.jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private void init() throws Exception {
        try {
            this.keyPair = loadKeyPairFromFile();
        } catch (Exception e) {
            this.keyPair = generateRSAKeyPair();
        }
    }

    private KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        var keyPair =  keyPairGenerator.generateKeyPair();
        this.saveKeyPairToFile(keyPair);
        return keyPair;
    }

    private void saveKeyPairToFile(KeyPair keyPair) throws Exception {
        var fileStream = new FileOutputStream(RSA_KEY_FILE_NAME);
        try (var objectOutputStream = new ObjectOutputStream(fileStream)) {
            objectOutputStream.writeObject(keyPair);
            log.info("已创建新的密钥对文件: " + Paths.get(RSA_KEY_FILE_NAME).toAbsolutePath());
        }
    }

    private KeyPair loadKeyPairFromFile() throws Exception {
        try (var objectInputStream = new ObjectInputStream(new FileInputStream(RSA_KEY_FILE_NAME))) {
            return (KeyPair) objectInputStream.readObject();
        }
    }
}
