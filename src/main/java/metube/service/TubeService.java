package metube.service;

import metube.domain.models.service.TubeDetailServiceModel;
import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.service.UploadTubeServiceModel;

public interface TubeService {

    boolean save(UploadTubeServiceModel uploadTube);

    TubeServiceModel[] findAllTubesByUsername(String username);

    TubeDetailServiceModel findByYoutubeId(String youtubeId);

    TubeServiceModel[] findAll();
}
