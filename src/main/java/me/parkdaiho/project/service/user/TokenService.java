package me.parkdaiho.project.service.user;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.parkdaiho.project.config.PrincipalDetails;
import me.parkdaiho.project.config.properties.JwtProperties;
import me.parkdaiho.project.config.token.TokenProvider;
import me.parkdaiho.project.domain.user.RefreshToken;
import me.parkdaiho.project.domain.user.User;
import me.parkdaiho.project.repository.user.RefreshTokenRepository;
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

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    private final JwtProperties jwtProperties;

    public String getAccessTokenByRefreshToken(String refreshToken) {
        if (!checkInvalidRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Non-validated refreshToken");
        }

        User user = userService.findById(getUserId(refreshToken));

        return tokenProvider.generateToken(user, jwtProperties.getAccessTokenDuration());
    }

    public Boolean checkInvalidRefreshToken(String refreshToken) {
        if (!validToken(refreshToken)) return false;

        Long id = getUserId(refreshToken);

        RefreshToken savedRefreshToken = refreshTokenRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected refreshTokenId: " + id));

        return refreshToken.equals(savedRefreshToken.getRefreshToken());
    }

    public String saveRefreshToken(User user) {
        String newRefreshToken = tokenProvider.generateToken(user, jwtProperties.getRefreshTokenDuration());
        RefreshToken refreshToken = refreshTokenRepository.findById(user.getId())
                .map(entity -> entity.update(newRefreshToken))
                .orElseGet(() -> RefreshToken.builder()
                        .user(user)
                        .refreshToken(newRefreshToken)
                        .build());

        return refreshTokenRepository.save(refreshToken).getRefreshToken();
    }

    public void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        CookieUtils.deleteCookie(request, response, jwtProperties.getRefreshTokenCookieName());
        CookieUtils.addCookie(response, jwtProperties.getRefreshTokenCookieName(),
                refreshToken, (int) jwtProperties.getRefreshTokenDuration().toSeconds());
    }

    public String getAccessToken(User user) {
        return tokenProvider.generateToken(user, jwtProperties.getAccessTokenDuration());
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
        User user = userService.findById(getUserId(accessToken));

        Collection<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getAuthority()));

        return new UsernamePasswordAuthenticationToken(new PrincipalDetails(user), accessToken, authorities);
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
}
