package com.lizhi.filter;

import com.lizhi.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            // 提交事务
            JdbcUtils.submitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            JdbcUtils.rollbackAndClose();
            throw new RuntimeException(e);//把异常抛给 TomCat 统一展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
