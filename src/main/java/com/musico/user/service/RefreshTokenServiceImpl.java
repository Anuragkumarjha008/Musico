package com.musico.user.service;


import com.musico.common.exception.InvalidTokenException;
import com.musico.common.exception.ResourceNotFoundException;
import com.musico.security.jwt.JwtProperties;
import com.musico.user.entity.RefreshToken;
import com.musico.user.entity.User;
import com.musico.user.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    @Override
    public RefreshToken createRefreshToken(User user) {

        String token = UUID.randomUUID().toString();

        Instant expiresAt = Instant.now().plus(
                Duration.ofDays(jwtProperties.getRefreshTokenExpirationDays())
        );

        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(token)
                .user(user)
                .expiresAt(expiresAt)
                .revoked(false)
                .createdAt(Instant.now())
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    @Override
    public void revokeAllUserTokens(User user) {

        List<RefreshToken> refreshTokens =
                refreshTokenRepository.findByUserAndRevokedFalse(user);

        if (refreshTokens.isEmpty()) {
            return;
        }

        refreshTokens.forEach(token -> token.setRevoked(true));

        refreshTokenRepository.saveAll(refreshTokens);
    }

    @Override
    public RefreshToken findByToken(String token) {

        return refreshTokenRepository
                .findByRefreshToken(token)
                .orElseThrow(() ->
                        new InvalidTokenException(
                                "Invalid refresh token."
                        ));
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiresAt().isBefore(Instant.now())) {

            token.setRevoked(true);
            refreshTokenRepository.save(token);

            throw new InvalidTokenException(
                    "Refresh token has expired."
            );
        }

        return token;
    }

    @Override
    public void revokeToken(RefreshToken token) {

        token.setRevoked(true);

        refreshTokenRepository.save(token);
    }

    @Override
    public RefreshToken verifyNotRevoked(RefreshToken token) {

        if (Boolean.TRUE.equals(token.getRevoked())) {
            throw new InvalidTokenException(
                    "Refresh token has been revoked."
            );
        }

        return token;
    }
}
