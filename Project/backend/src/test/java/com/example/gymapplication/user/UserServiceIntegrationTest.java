package com.example.gymapplication.user;

import com.example.gymapplication.TestCreationFactory;
import com.example.gymapplication.training.LocationRepository;
import com.example.gymapplication.training.TrainingRepository;
import com.example.gymapplication.training.model.Location;
import com.example.gymapplication.training.model.Training;
import com.example.gymapplication.user.model.Role;
import com.example.gymapplication.user.model.User;
import com.example.gymapplication.user.model.dto.UserDTO;
import com.example.gymapplication.user.model.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

import static com.example.gymapplication.TestCreationFactory.randomString;
import static com.example.gymapplication.user.model.ERole.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        //userRepository.deleteAll();
        roleRepository.deleteAll();
        roleRepository.save(new Role(1,ADMIN));
        roleRepository.save(new Role(2,TRAINER));
        roleRepository.save(new Role(3,REGULAR_USER));
    }

    @Test
    void findAll() {
        List<User> users = TestCreationFactory.listOf(User.class);
        List<User> users1 = userRepository.saveAll(users);

        List<UserListDTO> all = userService.allUsersForList();

        assertEquals(users.size(), all.size());
    }

    @Test
    void create(){
        Location location = locationRepository.save(Location.builder()
                .id(1L)
                .address(randomString())
                .build());

        Role role = roleRepository.save(new Role(1,ADMIN));

        Training training = trainingRepository.save(Training.builder()
                .id(1L)
                .title(randomString())
                .type(randomString())
                .date(randomString())
                .location(location)
                .build());

        User user1 = User.builder()
                .id(1L)
                .username(randomString())
                .email("email@email.com")
                .password(passwordEncoder.encode(randomString()))
                .roles(Set.of(role))
                .trainings(List.of(training))
                .regularTrainings(List.of(training))
                .build();

        User user2 = userRepository.save(user1);

        System.out.println(user2.getUsername());

        //UserDTO userDTO1 = userService.create(user);

        //assertEquals(user.getUsername(), userDTO1.getUsername());
        assertTrue(userRepository.findById(user1.getId()).isPresent());
    }
}
