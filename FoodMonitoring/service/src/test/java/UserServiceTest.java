import com.senla.courses.model.User;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    private final User userOne = new User(1, "Александр", "Тихонов", "user1",
            "password", "+375297769755");
    private final User userTwo = new User(2, "Иван", "Иванов", "user2",
            "123", "+375447335435");
    private final User userThree = new User(3, "Дмитрий", "Смирнов", "user3",
            "password3", "+375336985474");

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersTest() {
        List<User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);

        when(userRepository.findAll()).thenReturn(list);

        List<User> empList = userService.getAllUsers();

        assertEquals(3, empList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {
        when(userRepository.getById(1)).thenReturn(userOne);

        User user = userService.getUserById(1);

        assertEquals(1, user.getId());
        assertEquals("Александр", user.getName());
        assertEquals("Тихонов", user.getSurname());
        assertEquals("user1", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals("+375297769755", user.getPhone());
    }

    @Test
    public void saveUserTest() {
        userService.saveUser(userOne);

        verify(userRepository, times(1)).save(userOne);
    }

    @Test
    public void deleteUserIfUserExistsTest() {
        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(userOne));
        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void loadUserByUsernameIfUserNotFoundTest() {
        when(userRepository.findUserByLogin("login")).thenReturn(null);

        Throwable thrown = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("login"));
        assertNotNull(thrown.getMessage());
    }
}
