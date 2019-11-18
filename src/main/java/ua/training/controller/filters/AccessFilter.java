package ua.training.controller.filters;

import ua.training.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        if (path.contains("master")) {
            if (request.getSession().getAttribute("role").equals(Role.ROLE_MASTER)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.setAttribute("error", true);
                request.setAttribute("message", "AccessDenied");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }else if (path.contains("manager")) {
            if (request.getSession().getAttribute("role").equals(Role.ROLE_MANAGER)){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.setAttribute("error", true);
                request.setAttribute("message", "AccessDenied");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
