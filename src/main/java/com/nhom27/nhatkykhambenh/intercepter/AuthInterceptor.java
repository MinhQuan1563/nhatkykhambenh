package com.nhom27.nhatkykhambenh.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Object loggedInUser = session.getAttribute("loggedInUser");

        String requestURI = request.getRequestURI();
        System.out.println("requestURI: " + requestURI);
        System.out.println("loggedInUser: " + loggedInUser);

        if (loggedInUser != null) {
            if (requestURI.equals("/login") || requestURI.equals("/register")) {
                response.sendRedirect("/error?errorMessage=Bạn đã đăng nhập rồi!");
                System.out.println("Bạn đã đăng nhập rồi!");
                return false;
            }
        }

        if (loggedInUser == null && !requestURI.equals("/login") && !requestURI.equals("/register")) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}

