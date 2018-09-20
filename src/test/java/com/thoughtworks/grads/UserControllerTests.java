package com.thoughtworks.grads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grads.controller.ContactController;
import com.thoughtworks.grads.controller.UserController;
import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.ContactStorage;
import com.thoughtworks.grads.repository.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.thoughtworks.grads.domain.Sex.FEMALE;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class UserControllerTests {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new UserController()).build();
        UserStorage.clear();
    }

    @Test
    void should_query_all_contacts() throws Exception {
        User user = initUsersAndContacts();

        mockMvc.perform(get("/api/users/{id}", user.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("douqing"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("huanglizhen"));
    }

    private User initUsersAndContacts() {
        Contact douqing = new Contact(1, 5, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, 5, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);
        return user;
    }

    @Test
    void should_query_contact() throws Exception {
        Contact douqing = new Contact(1, 5, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, 5, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        mockMvc.perform(get("/api/users/{userName}/contacts/{contactName}", user.getName(), douqing.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("douqing"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void should_fail_when_query_user_not_found() throws Exception {
        Contact douqing = new Contact(1,5, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2,5, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        mockMvc.perform(get("/api/users/{userName}/contacts/{contactName}", "hhhhhhhh", douqing.getName()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_fail_when_query_contact_not_found() throws Exception {
        Contact douqing = new Contact(1,5, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2,5, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        mockMvc.perform(get("/api/users/{userName}/contacts/{contactName}", user.getName(), "xxxxxx"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
