package com.nhom27.nhatkykhambenh.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object taiKhoan = session.getAttribute("taiKhoan");

        String requestURI = request.getRequestURI();
        if (taiKhoan != null && (requestURI.equals("/login") || requestURI.equals("/register"))) {
            response.sendRedirect("/");
            return false;
        }

        if (taiKhoan == null && !requestURI.equals("/login") && !requestURI.equals("/register") && !requestURI.startsWith("/public")) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}

