package com.example.labsystem.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;


@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtConfig jwtConfig;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        if (uri.contains("/login")){
            return true;
        }

        //处理token
        String token =request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isEmpty(token)){
            token=request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)){
            //返回401，token不能为空
            sendError(response,"token不能为空");
            return false;
        }

        try {
            jwtConfig.getTokenClaim(token);
        } catch (Exception e) {
            //返回401,token失效
            sendError(response,"token无效");
            return false;
        }
        return true;
    }

    private void sendError(HttpServletResponse response,String msg) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(msg);
    }
}
