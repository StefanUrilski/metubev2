package metube.repository;

import metube.domain.entities.Tube;

import java.util.List;

public interface TubeRepository extends GenericRepository<Tube, String> {

    Tube update(Tube tube);

    Tube findByYouTubeId(String youtubeId);

    List<Tube> findAllByUsername(String userId);
}
