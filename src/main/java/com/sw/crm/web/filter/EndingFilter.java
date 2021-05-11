package com.sw.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @MASTER 统治世界的霸主 常狗狗
 * @date 2021/5/1
 */
public class EndingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入过滤器");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
