//package com.example.DRS_Project;
//
//import com.example.DRS_Project.Model.User;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//public class SessionService {
//
//    private final Map<String, org.springframework.security.core.userdetails.User> sessionStore = new ConcurrentHashMap<>();
//
//    public void createSession(String sessionId, org.springframework.security.core.userdetails.User user) {
//        sessionStore.put(sessionId, user);
//    }
//
//    public org.springframework.security.core.userdetails.User getSessionUser(String sessionId) {
//        return sessionStore.get(sessionId);
//    }
//
//    public void removeSession(String sessionId) {
//        sessionStore.remove(sessionId);
//    }
//
//}
//
