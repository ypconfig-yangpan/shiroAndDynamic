package com.pan.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by 
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.profiles.active}")
    private String system_environment;

    private Properties config;

    @PostConstruct
    public void init() throws IOException {
        String location = "";
        switch (system_environment) {
            case "dev":
                location = "redis/redis_dev.properties";
                break;
            case "test":
                location = "redis/redis_test.properties";
                break;
            case "official":
                location = "redis/redis_official.properties";
                break;
            case "gray":
                location = "redis/redis_gray.properties";
                break;
        }
        config= PropertiesLoaderUtils.loadProperties(new ClassPathResource(location));
      /*  RedisUtils.init(config);*/
    }


    @Bean("keyGenerator")
    @Qualifier("keyGenerator")
    public KeyGenerator keyGenerator() {
        return (Object target, Method method, Object... params)->{
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };

    }

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(config.getProperty("redis.max.idle")));
        jedisPoolConfig.setMaxWaitMillis(Long.parseLong(config.getProperty("redis.max.wait")));
        jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(config.getProperty("redis.test.on.borrow")));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(config.getProperty("redis.max.active")));
        return jedisPoolConfig;
    }


    @Bean(name="jedisConnectionFactory")
    @Qualifier("jedisConnectionFactory")
    public JedisConnectionFactory getJedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory factory=new JedisConnectionFactory();
        factory.setPoolConfig(jedisPoolConfig);
        factory.setHostName(config.getProperty("redis.ip"));
        factory.setPort(Integer.parseInt(config.getProperty("redis.port")));
        factory.setDatabase(Integer.parseInt(config.getProperty("redis.database.index")));
        return factory;
    }

    @Bean
    public RedisTemplate getRedisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory factory){
        RedisTemplate redisTemplate= new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);

        RedisSerializer<Object>  valueSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(valueSerializer);

        return redisTemplate;
    }
    @Bean
    public StringRedisTemplate getStringRedisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory factory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }


    @Bean
    public RedisCacheManager getRedisCacheManager(RedisTemplate redisTemplate){
        RedisCacheManager redisCacheManager=new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(Long.parseLong(config.getProperty("redis.expiration")));
  
        redisCacheManager.setCachePrefix(new  CustomRedisCachePrefix("yangpan"));
        redisCacheManager.setUsePrefix(true);

        return redisCacheManager;
    }


     class CustomRedisCachePrefix implements RedisCachePrefix {

        private final StringRedisSerializer serializer = new StringRedisSerializer();
        private final String delimiter;

        public CustomRedisCachePrefix() {
            this(":");
        }

        public CustomRedisCachePrefix(String delimiter) {
            this.delimiter = delimiter;
        }

        @Override
        public byte[] prefix(String cacheName) {
            return serializer.serialize((delimiter != null ? delimiter.concat(cacheName).concat(":") : cacheName.concat(":")));
        }
    }


}

