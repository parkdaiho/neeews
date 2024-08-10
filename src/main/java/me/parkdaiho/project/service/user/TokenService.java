package me.parkdaiho.project.service.user;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.util.TokenProvider;
import me.parkdaiho.project.domain.user.Role;
import me.parkdaiho.project.domain.user.Token;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.repository.user.TokenRepository;
import me.parkdaiho.project.util.CookieUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserService userService;

    private final JwtProperties jwtProperties;

    public String getAccessTokenByRefreshToken(String refreshToken) {
        if (!checkInvalidRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Non-validated refreshToken");
        }

        Token token = findByRefresToken(refreshToken);

        User user = userService.findById(token.getId());

        return getAccessToken(user);
    }

    private Token findByRefresToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Non-valid refreshToken"));
    }

    public Boolean checkInvalidRefreshToken(String refreshToken) {
        if (!validToken(refreshToken)) return false;

        Long id = getUserId(refreshToken);

        Token savedToken = tokenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected refreshTokenId: " + id));

        return refreshToken.equals(savedToken.getRefreshToken());
    }

    public Token saveToken(User user) {
        String newRefreshToken = TokenProvider.generateRefreshToken(user, jwtProperties);
        String newAccessToken = TokenProvider.generateAccessToken(user, jwtProperties);

        Token token = tokenRepository.findById(user.getId())
                .map(entity -> entity.updateRefreshToken(newRefreshToken)
                        .updateAccessToken(newAccessToken))
                .orElseGet(() -> Token.builder()
                        .user(user)
                        .refreshToken(newRefreshToken)
                        .accessToken(newAccessToken)
                        .build());

        return tokenRepository.save(token);
    }

    public void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response,
                                        String refreshToken) {
        CookieUtils.deleteCookie(request, response, jwtProperties.getRefreshTokenCookieName());
        CookieUtils.addCookie(response, jwtProperties.getRefreshTokenCookieName(),
                refreshToken, (int) jwtProperties.getRefreshTokenDuration().toSeconds());
    }

    @Transactional
    public String getAccessToken(User user) {
        Token token = getTokenById(user.getId());
        String accessToken = token.getAccessToken();
        if(validToken(accessToken)) {
            return accessToken;
        }

        accessToken = TokenProvider.generateAccessToken(user, jwtProperties);
        token.updateAccessToken(accessToken);

        return accessToken;
    }

    public String getAccessTokenByRequest(HttpServletRequest request) {
        String authorization = request.getHeader(jwtProperties.getAuthorizationHeaderName());
        if (authorization != null && authorization.startsWith(jwtProperties.getAuthorizationTokenPrefix())) {
            String accessToken = authorization.substring(jwtProperties.getAuthorizationTokenPrefix().length());

            return validToken(accessToken) ? accessToken : null;
        }

        return null;
    }

    public Authentication getAuthenticationByAccessToken(String accessToken) {
        Token token = findByAccessToken(accessToken);
        if(token == null) return null;

        User user = userService.findById(token.getId());

        Collection<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getAuthority()));

        return new UsernamePasswordAuthenticationToken(new PrincipalDetails(user), accessToken, authorities);
    }

    private Token findByAccessToken(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new IllegalArgumentException("Non-valid accessToken"));
    }

    public String getNicknameByAccessToken(String accessToken) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    private Boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Long getUserId(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);
    }

    private Token getTokenById(Long id) {
        return tokenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user-id: " + id ));
    }

    public String getRefreshTokenByRequest(HttpServletRequest request) {
        Cookie refreshTokenCookie = CookieUtils.getCookieByName(request, jwtProperties.getRefreshTokenCookieName());
        if(refreshTokenCookie == null) return null;

        return refreshTokenCookie.getValue();
    }

    public Role getRoleByAccessToken(String accessToken) {
        User user = userService.findById(getUserId(accessToken));

        return user.getRole();
    }

    public void invalidateToken(String refreshToken) {
        Token token = findByRefresToken(refreshToken);

        token.invalidate();
    }
}
