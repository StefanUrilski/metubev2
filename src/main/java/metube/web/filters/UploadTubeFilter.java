package metube.web.filters;

import metube.domain.models.binding.UploadTubeBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class UploadTubeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().toLowerCase().equals("post")) {
            UploadTubeBindingModel uploadTube = new UploadTubeBindingModel();

            uploadTube.setTitle(req.getParameter("title"));
            uploadTube.setAuthor(req.getParameter("author"));
            uploadTube.setYoutubeId(req.getParameter("youtube-link"));
            uploadTube.setDescription(req.getParameter("description"));
            uploadTube.setUploader((String) req.getSession().getAttribute("username"));

            req.setAttribute("uploadTube", uploadTube);
        }

        chain.doFilter(req, resp);
    }
}
