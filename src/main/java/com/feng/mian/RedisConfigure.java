package com.feng.mian;

import java.io.IOException;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.feng.util.NullMapper;

@Configuration
@EnableCaching
public class RedisConfigure {

	/**
	 * 定义 StringRedisTemplate ，指定序列化和反序列化的处理类
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate t = new StringRedisTemplate(factory);
		//此方法配置redis的序列化有些问题，暂时不知道咋回事
//		Jackson2JsonRedisSerializer<Object> jackson = new Jackson2JsonRedisSerializer<>(Object.class);
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//			@Override
//			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
//					throws IOException, JsonProcessingException {
//				jgen.writeString("");
//			}
//		});
//		
//		jackson.setObjectMapper(new NullMapper());
//		t.setValueSerializer(jackson);
		
		t.setKeySerializer(new StringRedisSerializer());
        t.setHashKeySerializer(new StringRedisSerializer());
        t.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        t.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		t.afterPropertiesSet();
		return t;
	}
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		//使用前缀
		manager.setUsePrefix(true);
		//设置缓存分割符，默认为:
		manager.setCachePrefix(new DefaultRedisCachePrefix(":"));
		//设置缓存时间
		manager.setDefaultExpiration(60*5);
		return manager;
	}
}
