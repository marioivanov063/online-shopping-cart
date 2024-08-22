package org.bklvsc.shoppingcart.cart.configurations;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.session.Session;

@Service
public class SessionService {
	/*@Autowired
    private FindByIndexNameSessionRepository<? extends Session> sessions;

    public Collection<? extends Session> getSessions(Principal principal) {
    	if (principal == null) {
            System.out.println("Principal is null");
            return Collections.emptyList();
        }

        String principalName = principal.getName();
        System.out.println("Fetching sessions for principal: " + principalName);
        
        Map<String, ? extends Session> sessionsByPrincipal = this.sessions.findByPrincipalName(principalName);
        System.out.println("Sessions found: " + sessionsByPrincipal);
        return sessionsByPrincipal.values();
    }*/}
