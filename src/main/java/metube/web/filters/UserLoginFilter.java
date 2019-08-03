package metube.web.filters;

import metube.domain.models.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            UserLoginBindingModel userLogin = new UserLoginBindingModel();

            userLogin.setUsername(req.getParameter("username"));
            userLogin.setPassword(req.getParameter("password"));

            req.setAttribute("userLogin", userLogin);
        }

        chain.doFilter(req, resp);

    }
}
