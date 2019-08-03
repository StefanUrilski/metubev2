package metube.service;

import metube.domain.entities.Tube;
import metube.domain.entities.User;
import metube.domain.models.service.TubeDetailServiceModel;
import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.service.UploadTubeServiceModel;
import metube.repository.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, UserService userService, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean save(UploadTubeServiceModel uploadTube) {
        Tube tube = modelMapper.map(uploadTube, Tube.class);
        tube.setId(null);

        try {
            this.tubeRepository.save(tube);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        User uploader = userService.findByName(uploadTube.getUploader());
        tube.setUploader(uploader);
        tubeRepository.update(tube);
        return true;
    }

    @Override
    public TubeServiceModel[] findAllTubesByUsername(String username) {
        List<Tube> allTubes = tubeRepository.findAllByUsername(username);
        return modelMapper.map(allTubes, TubeServiceModel[].class);
    }

    @Override
    public TubeDetailServiceModel findByYoutubeId(String youtubeId) {
        return modelMapper.map(tubeRepository.findByYouTubeId(youtubeId), TubeDetailServiceModel.class);
    }

    @Override
    public TubeServiceModel[] findAll() {
        return modelMapper.map(tubeRepository.findAll(), TubeServiceModel[].class);
    }
}
