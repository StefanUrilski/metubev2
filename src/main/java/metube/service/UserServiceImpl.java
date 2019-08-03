package metube.service;

import metube.domain.entities.User;
import metube.domain.models.service.UserLoginServiceModel;
import metube.domain.models.service.UserRegisterServiceModel;
import metube.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean save(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userRegisterServiceModel.getPassword()));

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean userExist(String name, String pass) {
        pass = DigestUtils.sha256Hex(pass);

        return userRepository.findByUsernameAndPassword(name, pass) != null;
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }
}
