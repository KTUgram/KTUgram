package com.KTUgrammeriai.KTUgram_backend.authentification;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SessionHandlerService {
    private final HashMap<String, Session> sessions = new HashMap<String, Session>();

    public void registerSession(final String key, final long id, final String permissions) {
        sessions.put(key, new Session(id, permissions));
    }

    public void unregisterSession(final String key) {
        sessions.remove(key);
    }

    public Session getSession(final String key) {
        return sessions.get(key);
    }

    public boolean hasSession(final String key) {
        return sessions.containsKey(key);
    }
}
