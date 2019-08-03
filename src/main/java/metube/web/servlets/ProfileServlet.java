package metube.web.servlets;

import metube.domain.entities.User;
import metube.domain.models.view.UserProfileViewModel;
import metube.domain.models.view.UserTubesViewModel;
import metube.service.TubeService;
import metube.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final TubeService tubeService;

    @Inject
    public ProfileServlet(ModelMapper modelMapper,
                          UserService userService,
                          TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getSession().getAttribute("username");
        User user = userService.findByName((String) req.getSession().getAttribute("username"));
        UserProfileViewModel userProfileViewModel = modelMapper.map(
                user,
                UserProfileViewModel.class
        );

        UserTubesViewModel[] tubes = modelMapper.map(
                tubeService.findAllTubesByUsername(user.getUsername()),
                UserTubesViewModel[].class
        );

        userProfileViewModel.setTubes(Arrays.asList(tubes));

        if (req.getSession().getAttribute("model") != null) {
            req.getSession().setAttribute("model", null);
        }
        req.getSession().setAttribute("model", userProfileViewModel);

        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }

}
