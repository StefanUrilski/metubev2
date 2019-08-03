package metube.web.servlets;

import metube.domain.models.binding.UserLoginBindingModel;
import metube.domain.models.service.UserLoginServiceModel;
import metube.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Inject
    public LoginServlet(ModelMapper modelMapper,
                        UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingModel userBindingModel = (UserLoginBindingModel) req.getAttribute("userLogin");

        UserLoginServiceModel userServiceModel = modelMapper.map(userBindingModel, UserLoginServiceModel.class);

        if(! userService.userExist(userServiceModel.getUsername(), userServiceModel.getPassword())) {
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("username", userServiceModel.getUsername());

        resp.sendRedirect("/");
    }
}
