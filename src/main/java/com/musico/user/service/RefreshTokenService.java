package com.musico.user.service;

import com.musico.user.entity.RefreshToken;
import com.musico.user.entity.User;
import org.springframework.stereotype.Service;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);
    void revokeAllUserTokens(User user);
    RefreshToken findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
    void revokeToken(RefreshToken token);
    RefreshToken verifyNotRevoked(RefreshToken token);
}
