package com.wolox.test.infrastructure.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolox.test.application.port.input.AssignPrivilegeForUserCommand;
import com.wolox.test.application.port.input.FindAllAlbumsQuery;
import com.wolox.test.application.port.input.FindUsersByAlbumAndPrivilegeQuery;
import com.wolox.test.domain.Privilege;
import com.wolox.test.infrastructure.faker.AlbumFaker;
import com.wolox.test.infrastructure.faker.UserFaker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Album Controller Test")
@WebMvcTest(AlbumRestController.class)
class AlbumRestControllerTest {

    private static final String URL = "/api/v1/albums/";

    private static final String USER_PRIVILEGE_URL = URL + AlbumFaker.ID + "/users";

    private static final String PRIVILEGE_PARAM_KEY = "privilege";

    private static final String ASSIGN_PRIVILEGE_URL = URL + AlbumFaker.ID + "/assign-privilege-user";

    @MockBean
    private FindAllAlbumsQuery findAllAlbumsQuery;

    @MockBean
    private AssignPrivilegeForUserCommand assignPrivilegeForUserCommand;

    @MockBean
    private FindUsersByAlbumAndPrivilegeQuery findUsersByAlbumAndPrivilegeQuery;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Find all almbums test")
    void findAllAlbumsTest() throws Exception {
        when(findAllAlbumsQuery.execute()).thenReturn(AlbumFaker.getALbums());
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(AlbumFaker.getALbumsResponse())));
    }

    @Test
    @DisplayName("Find users by almbum and privilege test")
    void findUsersByAlbumAndPrivilegeTest() throws Exception {
        when(findUsersByAlbumAndPrivilegeQuery.execute(AlbumFaker.ID,
                Privilege.fromString(AlbumFaker.READ_PRIVILEGE)))
                .thenReturn(UserFaker.getUsers());
        mockMvc.perform(get(USER_PRIVILEGE_URL)
                .param(PRIVILEGE_PARAM_KEY, AlbumFaker.READ_PRIVILEGE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(UserFaker.getUsersResponse())));
    }


    @Test
    @DisplayName("find Users By Album And Privilege return BadRequest")
    void findUsersByAlbumAndPrivilegeBadRequestTest() throws Exception {
        this.mockMvc.perform(
                get(USER_PRIVILEGE_URL)
                        .param(PRIVILEGE_PARAM_KEY, AlbumFaker.BAD_PRIVILEGE)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("assign Privilege For User ")
    void assignPrivilegeForUserTest() throws Exception {
        doNothing().when(assignPrivilegeForUserCommand).execute(AlbumFaker.ID,
                AlbumFaker.ID,
                AlbumFaker.getPrivileges());
        var body = objectMapper.writeValueAsString(AlbumFaker.getAlbumPrivilegeRequest());
        this.mockMvc.perform(
                post(ASSIGN_PRIVILEGE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .characterEncoding("utf-8")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("assign Privilege For User BadRquest")
    void assignPrivilegeForUserBadRquestTest() throws Exception {
        var body = objectMapper.writeValueAsString(AlbumFaker.getBadAlbumPrivilegeRequest());
        this.mockMvc.perform(
                post(ASSIGN_PRIVILEGE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .characterEncoding("utf-8")
        ).andDo(print())
                .andExpect(status().isBadRequest());
    }


}
