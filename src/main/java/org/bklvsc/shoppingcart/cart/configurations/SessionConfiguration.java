package org.bklvsc.shoppingcart.cart.configurations;

import java.util.ArrayList;
import java.util.List;

import org.bklvsc.shoppingcart.cart.configurations.listeners.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.FlushMode;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import jakarta.servlet.http.HttpSessionListener;

@Configuration
@EnableRedisIndexedHttpSession(maxInactiveIntervalInSeconds = 20)
public class SessionConfiguration{}
