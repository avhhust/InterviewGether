package com.interviewgether.service;

import com.interviewgether.model.User;
import com.interviewgether.model.UserAccount;
import com.interviewgether.repository.UserAccountRepository;
import com.interviewgether.service.implementation.UserAccountServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @InjectMocks
    private UserAccountServiceImpl userAccountService;

    @Test
    void shouldCreateUserAccountWhenValidUserProvided() {
        userAccountService.create(new User());
        verify(userAccountRepository, times(1))
                .save(any(UserAccount.class));
    }

    @Test
    void shouldCreateUserAccountWithAssignedUser() {
        User user = new User();
        when(userAccountRepository.save(any(UserAccount.class)))
                .thenAnswer(ans -> ans.getArgument(0));
        //when
        userAccountService.create(user);
        //then
        ArgumentCaptor<UserAccount> userAccountArgumentCaptor =
                ArgumentCaptor.forClass(UserAccount.class);
        verify(userAccountRepository).save(userAccountArgumentCaptor.capture());
        UserAccount captured = userAccountArgumentCaptor.getValue();
        assertThat(captured.getUser()).isEqualTo(user);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfUserIsNullWhenCreatingUserAccount() {
        assertThatThrownBy(() -> userAccountService.create(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User must not be null");

        verify(userAccountRepository, never()).save(any());
    }

    @Test
    void shouldInitializeCreatedAtWhenCreatingUserAccount(){
        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer(ans -> ans.getArguments()[0]);
        OffsetDateTime before = OffsetDateTime.now();
        //when
        userAccountService.create(new User());
        //then
        ArgumentCaptor<UserAccount> userAccountArgCaptor =
                ArgumentCaptor.forClass(UserAccount.class);
        verify(userAccountRepository).save(userAccountArgCaptor.capture());
        UserAccount captured = userAccountArgCaptor.getValue();
        assertThat(captured.getCreatedAt()).isAfterOrEqualTo(before).isBeforeOrEqualTo(OffsetDateTime.now());
    }

    @Test
    void shouldReadUserAccountByExistingId() {
        long id = 1L;
        UserAccount userAccount = new UserAccount();
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(userAccount));

        UserAccount retrieved = userAccountService.readById(id);

        ArgumentCaptor<Long> argCaptor =
                ArgumentCaptor.forClass(long.class);
        verify(userAccountRepository, times(1))
                .findById(argCaptor.capture());
        assertThat(argCaptor.getValue()).isEqualTo(id);
        assertThat(retrieved).isEqualTo(userAccount);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenIdDoesntExist() {
        long id = 1L;
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userAccountService.readById(id))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("UserAccount with id " + id + " is not found");
    }

    // ToDo: Implement later after method's implementation updated
    @Test
    @Disabled
    void shouldUpdateUserAccountWhenValidUserAccountProvided() {
        UserAccount oldAccount = new UserAccount();

    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenUpdatingUserWithNull() {
        assertThatThrownBy(() -> userAccountService.update(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("UserAccount must not be null");
        verify(userAccountRepository, never()).save(any(UserAccount.class));
    }

    @Test
    void shouldDeleteUserAccountByExistingId() {
        long id = 1L;
        when(userAccountRepository.findById(anyLong())).thenReturn(Optional.of(new UserAccount()));

        userAccountService.delete(id);

        ArgumentCaptor<Long> idArgCaptor =
                ArgumentCaptor.forClass(long.class);
        verify(userAccountRepository).deleteById(idArgCaptor.capture());
        long capturedId = idArgCaptor.getValue();

        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void shouldReturnUserAccountByValidUser() {
        long id = 1L;
        User user = new User();
        user.setUserId(id);
        UserAccount account = new UserAccount();
        account.setUser(user);
        when(userAccountRepository.findById(id)).thenReturn(Optional.of(account));

        UserAccount retrieved = userAccountService.readByUser(user);

        ArgumentCaptor<Long> idArgCaptor =
                ArgumentCaptor.forClass(long.class);
        verify(userAccountRepository).findById(idArgCaptor.capture());
        assertThat(idArgCaptor.getValue()).isEqualTo(user.getUserId());
        assertThat(retrieved).isEqualTo(account);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenReadingUserAccountByNull() {
        assertThatThrownBy(() -> userAccountService.readByUser(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User must not be null");
        verify(userAccountRepository, never()).findById(anyLong());
    }
}
