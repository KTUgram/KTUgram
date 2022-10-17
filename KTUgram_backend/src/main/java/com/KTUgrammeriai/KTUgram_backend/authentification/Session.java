package com.KTUgrammeriai.KTUgram_backend.authentification;

public class Session {
    private final long id;
    private final String permissions;

    public Session(final long id, final String permissions) {
        this.id = id;
        this.permissions = permissions;
    }

    public long getId() {
        return id;
    }

    public String getPermissions() {
        return permissions;
    }
}
