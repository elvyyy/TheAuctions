package com.epam.auctions.service.impl;

import com.epam.auctions.entity.User;
import com.epam.auctions.entity.UserRole;
import com.epam.auctions.entity.UserStatus;
import com.epam.auctions.repository.impl.LotRepository;
import com.epam.auctions.repository.impl.UserRepository;
import com.epam.auctions.repository.specification.SqlSpecification;
import com.epam.auctions.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@PrepareForTest(LotRepository.class)
public class UserServiceImplTest {

    private UserService userService;
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void init() {
        user = new User();
        user.setId(0);
        user.setFirstName("Test");
        user.setMiddleName("User");
        user.setLastName("Instance");
        user.setUsername("test_user_instance");
        user.setUserRole(UserRole.USER);
        user.setUserStatus(UserStatus.REGISTERED);
        user.setEmail("test_user_instance@junit.org");
        user.setPassword("password");
        user.setLotNumbers(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    public UserServiceImplTest() {
        userService = UserServiceImpl.INSTANCE;
        repository = mock(UserRepository.class);
        Whitebox.setInternalState(userService, "repository", repository);
    }

    @Test
    public void verifyThatInsertWillBeCalledByUserRepositoryOneTime_When_RegisterCalled_With_User() {
        userService.register(user);

        verify(repository, times(1))
                .insert(eq(user));
    }

    @Test
    public void verifyThatSelectWillBeCalledByUserRepositoryOneTime_When_FindByIdCalled_With_UserId() {
        doReturn(Optional.of(user))
                .when(repository)
                .select(any(SqlSpecification.class), eq(user.getId()));

        Optional<User> actualUser = userService.findById(user.getId());

        verify(repository, times(1))
                .select(any(SqlSpecification.class), eq(user.getId()));

        assertTrue(actualUser.isPresent());
        assertEquals(user, actualUser.get());
    }

    @Test
    public void verifyThatSelectWillBeCalledByUserRepositoryOneTime_When_FindByUsernameCalled_With_Username() {
        doReturn(Optional.of(user))
                .when(repository)
                .select(any(SqlSpecification.class), eq(user.getUsername()));

        Optional<User> actualUser = userService.findByUsername(user.getUsername());

        verify(repository, times(1))
                .select(any(SqlSpecification.class), eq(user.getUsername()));

        assertTrue(actualUser.isPresent());
        assertEquals(user, actualUser.get());
    }

    @Test
    public void verifyThatSelectWillBeCalledByUserRepositoryOneTime_WhenFindByEmailCalled_With_UserEmail() {
        doReturn(Optional.of(user))
                .when(repository)
                .select(any(SqlSpecification.class), eq(user.getEmail()));

        Optional<User> actualUser = userService.findByEmail(user.getEmail());

        verify(repository, times(1))
                .select(any(SqlSpecification.class), eq(user.getEmail()));

        assertTrue(actualUser.isPresent());
        assertEquals(user, actualUser.get());
    }

    @Test
    public void verifyThatUpdateWillBeCalledByUserRepositoryOneTime_When_UpdateCalled_With_User() {
        userService.update(user);

        verify(repository, times(1))
                .update(eq(user));
    }

}