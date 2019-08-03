package metube.service;

import metube.domain.entities.User;
import metube.domain.models.service.UserLoginServiceModel;
import metube.domain.models.service.UserRegisterServiceModel;

public interface UserService {

    boolean save(UserRegisterServiceModel userRegisterServiceModel);

    boolean userExist(String name, String pass);

    User findByName(String username);

}
