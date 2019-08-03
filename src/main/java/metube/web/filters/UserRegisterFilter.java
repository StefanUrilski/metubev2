package metube.web.filters;

import metube.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            UserRegisterBindingModel userRegister =  new UserRegisterBindingModel();

            userRegister.setUsername(req.getParameter("username"));
            userRegister.setPassword(req.getParameter("password"));
            userRegister.setConfirmPassword(req.getParameter("confirmPassword"));
            userRegister.setEmail(req.getParameter("email"));

            req.setAttribute("userRegister", userRegister);
        }

        chain.doFilter(req, resp);
    }
}
