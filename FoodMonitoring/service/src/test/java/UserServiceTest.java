import com.senla.courses.dto.UserDtoInput;
import com.senla.courses.dto.UserDtoOutput;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    private final UserDtoInput userInput = new UserDtoInput(1, "Александр", "Тихонов", "user1",
            "password", "+375297769755");

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
        Page<User> page = new PageImpl<>(list);

        when(userRepository.findAll(PageRequest.of(0, 3))).thenReturn(page);

        List<UserDtoOutput> empList = userService.getAllUsers(PageRequest.of(0, 3));

        assertEquals(3, empList.size());
        verify(userRepository, times(1)).findAll(PageRequest.of(0, 3));
    }

    @Test
    public void saveUserTest() {
        userService.saveUser(userInput);

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
