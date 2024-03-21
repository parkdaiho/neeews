package me.parkdaiho.project.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.parkdaiho.project.domain.Domain;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class CookieUtils {

    public static void addCookie(HttpServletResponse response, String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expiry);

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return;
        }

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(name)) {
                cookie.setValue("");
                cookie.setMaxAge(0);

                response.addCookie(cookie);
                return;
            }
        }
    }

    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    public static <T> T deserialize(String value, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(value)
                )
        );
    }

    public static boolean checkView(HttpServletRequest request, HttpServletResponse response,
                                    Domain domain, Long id) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return false;

        List<Long> viewedList = null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(domain.getDomainPl())) {
                viewedList = deserialize(cookie.getValue(), List.class);
            }
        }

        if(viewedList == null) viewedList = new ArrayList<>();

        for(Long viewedId : viewedList) {
            if(Objects.equals(viewedId, id)) return true;
        }

        viewedList.add(id);
        addCookie(response, domain.getDomainPl(), serialize(viewedList), 60 * 60 * 2);

        return false;
    }
}
