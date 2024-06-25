package com.interviewgether.service;

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
        long userId = 1L;
        userAccountService.create(userId);
        verify(userAccountRepository, times(1))
                .save(any(UserAccount.class));
    }

    @Test
    void shouldCreateUserAccountWithAssignedUser() {
        long userId = 1L;
        when(userAccountRepository.save(any(UserAccount.class)))
                .thenAnswer(ans -> ans.getArgument(0));
        //when
        userAccountService.create(userId);
        //then
        ArgumentCaptor<UserAccount> userAccountArgumentCaptor =
                ArgumentCaptor.forClass(UserAccount.class);
        verify(userAccountRepository).save(userAccountArgumentCaptor.capture());
        UserAccount captured = userAccountArgumentCaptor.getValue();
        assertThat(captured.getUserId()).isEqualTo(userId);
    }

    @Test
    void shouldInitializeCreatedAtWhenCreatingUserAccount(){
        long userId = 1L;
        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer(ans -> ans.getArguments()[0]);
        OffsetDateTime before = OffsetDateTime.now();
        //when
        userAccountService.create(userId);
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

    // ToDo: Implement shouldUpdateUserAccountWhenValidUserAccountProvided

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
    @Disabled
    void shouldReturnUserAccountByValidUserId() {
        //ToDo: implement test for method readByUserId
    }

}
