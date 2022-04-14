package service.user;

import model.User;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;


import java.util.List;

public class UserServiceMySQL implements UserService{

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public UserServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean removeById(long id) {
        return userRepository.removeById(id);
    }
}
