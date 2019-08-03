package metube.web.servlets;

import metube.domain.models.service.TubeDetailServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/details/*")
public class DetailsServlet extends HttpServlet {

    private final ModelMapper modelMapper;
    private final TubeService tubeService;

    @Inject
    public DetailsServlet(ModelMapper modelMapper,
                          TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] tokens = req.getRequestURI().split("/");
        TubeDetailServiceModel tubeByYoutubeId= tubeService.findByYoutubeId(tokens[tokens.length - 1]);
        TubeDetailsViewModel viewModel = modelMapper.map(tubeByYoutubeId, TubeDetailsViewModel.class);

        viewModel.setViews(
                String.format("%,d", tubeByYoutubeId.getViews())
                        .replace(",", " ")
        );
        req.getSession().setAttribute("tubeView", viewModel);
        req.getRequestDispatcher("/jsp/details.jsp").forward(req, resp);
    }

}
