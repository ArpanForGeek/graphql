package com.example.graphql.config.Jasyptstarter;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Configuration to define our own Jasypt encryptor bean.
 *
 * @author a.l.bandyopadhyay
 */
@Configuration
public class JasyptStarterConfig {
    private static final String JASYPT_SECRET_KEY = "KrTyrsRt";
    private static final String JASYPT_ALGORITHM = "PBEWithMD5AndDES";
    private static final String JASYPT_NUMBER_OF_HASH = "1000";
    private static final String JASYPT_POOL_SIZE = "1";
    private static final String JASYPT_SECURITY_PROVIDER = "SunJCE";
    private static final String JASYPT_SALT_GENERATOR_CLASSNAME = "org.jasypt.salt.RandomSaltGenerator";
    private static final String JASYPT_STRING_OUTPUT_TYPE = "base64";


    /**
     * Defined our own Jasypt encryptor bean by setting below properties :
     * <p>
     * setPassword  - (secret key) used to encrypt database secret key.
     * setAlgorithm   -  the name of the algorithm to be used for encryption/decryption.
     * setKeyObtentionIterations - Sets the number of hashing iterations applied to obtain the encryption key.
     * setPoolSize - the size of the pool to be used if this configuration is used with a pooled encryptor.
     * setProviderName - the name of the security provider.
     * setSaltGeneratorClassName - the name of the salt generator class.
     * setStringOutputType - the string output type.
     *
     * @return StringEncryptor
     */
    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(JASYPT_SECRET_KEY);
        config.setAlgorithm(JASYPT_ALGORITHM);
        config.setKeyObtentionIterations(JASYPT_NUMBER_OF_HASH);
        config.setPoolSize(JASYPT_POOL_SIZE);
        config.setProviderName(JASYPT_SECURITY_PROVIDER);
        config.setSaltGeneratorClassName(JASYPT_SALT_GENERATOR_CLASSNAME);
        config.setStringOutputType(JASYPT_STRING_OUTPUT_TYPE);
        encryptor.setConfig(config);
        return encryptor;
    }
}