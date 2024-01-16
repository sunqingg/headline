package com.sun.qing.filters;

import com.sun.qing.common.Result;
import com.sun.qing.common.ResultCodeEnum;
import com.sun.qing.util.JwtHelper;
import com.sun.qing.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)  servletRequest;
        HttpServletResponse response =  (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        Boolean flag =false;
        if (token != null){
            if (!JwtHelper.isExpiration(token)){
                flag = true;
            }
        }
        if (flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            WebUtil.writeJson(response, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }
}
