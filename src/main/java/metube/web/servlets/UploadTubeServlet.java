package metube.web.servlets;


import metube.domain.models.binding.UploadTubeBindingModel;
import metube.domain.models.service.UploadTubeServiceModel;
import metube.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class UploadTubeServlet extends HttpServlet {

    private final ModelMapper modelMapper;
    private final TubeService tubeService;

    @Inject
    public UploadTubeServlet(ModelMapper modelMapper,
                             TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/tube-upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UploadTubeBindingModel tube = (UploadTubeBindingModel) req.getAttribute("uploadTube");

        UploadTubeServiceModel uploadTube = modelMapper.map(tube, UploadTubeServiceModel.class);
        uploadTube.setYoutubeId(tube.getYoutubeId().split("=")[1]);

        if (! tubeService.save(uploadTube)) {
            req.getRequestDispatcher("/jsp/tube-upload.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/home");
    }
}
