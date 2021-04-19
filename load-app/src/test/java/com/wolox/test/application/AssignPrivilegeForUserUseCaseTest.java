package com.wolox.test.application;

import com.wolox.test.application.port.output.AlbumGateway;
import com.wolox.test.application.port.output.AlbumRepository;
import com.wolox.test.application.port.output.UserGateway;
import com.wolox.test.domain.exception.RecordNotFoundException;
import com.wolox.test.domain.exception.UserIsOwnerException;
import com.wolox.test.infrastructure.faker.AlbumFaker;
import com.wolox.test.infrastructure.faker.UserFaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AssignPrivilegeForUserUseCase Test")
@ExtendWith(MockitoExtension.class)
public class AssignPrivilegeForUserUseCaseTest {

    private static final String RECORD_NOT_FOUND = "No se encontró";

    private static final String USER_IS_OWNER_MESSAGE = "es dueño del album";

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumGateway albumGateway;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private AssignPrivilegeForUserUseCase assignPrivilegeForUserUseCase;


    @Test
    @DisplayName("when find album by id return Exception")
    void whenfindAlbumByIdReturnException() {
        when(albumGateway.findById(AlbumFaker.ID)).thenThrow(new RecordNotFoundException(RECORD_NOT_FOUND));
        assertThatThrownBy(() -> assignPrivilegeForUserUseCase.execute(AlbumFaker.ID, AlbumFaker.ID, AlbumFaker.getPrivileges()))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining(RECORD_NOT_FOUND);
        verify(albumGateway, times(1)).findById(any());
    }

    @Test
    @DisplayName("when find user by id return Exception")
    void whenfindUserByIdReturnException() {
        when(albumGateway.findById(AlbumFaker.ID)).thenReturn(AlbumFaker.getALbums().get(0));
        when(userGateway.findById(AlbumFaker.ID)).thenThrow(new RecordNotFoundException(RECORD_NOT_FOUND));
        assertThatThrownBy(() -> assignPrivilegeForUserUseCase.execute(AlbumFaker.ID, AlbumFaker.ID, AlbumFaker.getPrivileges()))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining(RECORD_NOT_FOUND);

        verify(userGateway, times(1)).findById(any());
    }

    @Test
    @DisplayName("when execute assign privilege for user Ok")
    void whenExecuteAssignPrivilegeForUserOk() {
        when(albumGateway.findById(AlbumFaker.ID)).thenReturn(AlbumFaker.getALbums().get(0));
        when(userGateway.findById(AlbumFaker.ID)).thenReturn(UserFaker.getUsers().get(0));
        doNothing().when(albumRepository).deleteByUserIdAndAlbumId(AlbumFaker.ID, AlbumFaker.ID);
        doNothing().when(albumRepository).save(any());
        assignPrivilegeForUserUseCase.execute(AlbumFaker.ID, AlbumFaker.ID, AlbumFaker.getPrivileges());
        verify(albumRepository, times(2)).save(any());
    }

    @Test
    @DisplayName("when execute assign privilege for user exception")
    void whenExecuteAssignPrivilegeForUserException() {
        when(albumGateway.findById(AlbumFaker.ID)).thenReturn(AlbumFaker.getALbums().get(0));
        assertThatThrownBy(() -> assignPrivilegeForUserUseCase.execute(AlbumFaker.ID, AlbumFaker.ID, AlbumFaker.getPrivileges()))
                .isInstanceOf(UserIsOwnerException.class)
                .hasMessageContaining(USER_IS_OWNER_MESSAGE);
        verify(albumGateway, times(1)).findById(any());
    }

}
