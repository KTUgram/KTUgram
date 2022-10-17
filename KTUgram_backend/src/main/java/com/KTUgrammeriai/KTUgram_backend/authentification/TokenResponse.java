package com.KTUgrammeriai.KTUgram_backend.authentification;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class TokenResponse {
    private String token;
    private List<String> rights = new ArrayList<>();
    private HashMap<String, Object> settings = new HashMap<String, Object>();
    private String loggedInUserName;
    private long loggedInUserId;
    private boolean loggedInUserIsLdap;
}
