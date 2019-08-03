package metube.web.servlets;

import metube.domain.models.view.UserTubesViewModel;
import metube.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private final ModelMapper modelMapper;
    private final TubeService tubeService;

    @Inject
    public HomeServlet(ModelMapper modelMapper,
                       TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserTubesViewModel[] allTubes = modelMapper.map(
                tubeService.findAll(),
                UserTubesViewModel[].class
        );

        req.getSession().setAttribute("allTubes", Arrays.asList(allTubes));

        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
