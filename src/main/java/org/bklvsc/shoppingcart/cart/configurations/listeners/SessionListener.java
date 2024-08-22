package org.bklvsc.shoppingcart.cart.configurations.listeners;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bklvsc.shoppingcart.cart.domain.entities.CartDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener{
	@Autowired
	RedisTemplate<String, String> template;

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		
		String pattern = "*" + sessionId + "*";
		List<String> sessionKeys;
		try(Cursor<String> cursor = template.scan(ScanOptions.scanOptions()
		      .match(pattern).count(500).build())) {
		    sessionKeys = cursor.stream().toList();
		}
		if (!sessionKeys.isEmpty()) 
            template.delete(sessionKeys);
	}
}
