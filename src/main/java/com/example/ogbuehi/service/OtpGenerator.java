package com.example.ogbuehi.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Description(value = "Service for generating and validating OTP")
@Service
public class OtpGenerator {

    private static final Integer EXPIRE_MIN = 5;
    private final LoadingCache<String, Integer> otpCache;

    public OtpGenerator() {
        super();
        this.otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }
    /**
     * Method for generating OTP and put it in cache.
     *
     * @param key - cache key
     * @return cache value (generated OTP number)
     */
    public Integer generateOtp(String key) {
        Random random = new Random();
        int OTP = 100000 + random.nextInt(900000);
        otpCache.put(key,OTP);
        return OTP;
    }
    /**
     * Method for getting OTP value by key.
     *
     * @param key - target key
     * @return OTP value
     */
    public Integer getOtpByKey(String key) {
        return otpCache.getIfPresent(key);
    }
    /**
     * Method for removing key from cache.
     *
     * @param key - target key
     */
    public void clearOTPFromCache(String key) {
        otpCache.invalidate(key);
    }
}
