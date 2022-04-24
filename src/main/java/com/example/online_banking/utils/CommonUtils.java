package com.example.online_banking.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static String getAuthority(Authentication authentication) {
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            roles.add(ga.getAuthority());
        }
        if (!CollectionUtils.isEmpty(roles)) {
            return roles.get(0);
        }
        return null;
    }
}
