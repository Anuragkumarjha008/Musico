package com.musico.common.util;

import com.musico.security.userdetails.CustomUserDetails;
import com.musico.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static CustomUserDetails getCurrentUserDetails() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (CustomUserDetails) authentication.getPrincipal();
    }

    public static User getCurrentUser() {
        return getCurrentUserDetails().getUser();
    }
}
