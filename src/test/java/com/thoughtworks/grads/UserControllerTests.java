package com.thoughtworks.grads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grads.controller.UserController;
import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.thoughtworks.grads.domain.Sex.FEMALE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void should_add_contact_to_one_user() throws Exception {
        User user = new User(5, "huanglizhen");
        UserStorage.put(user);

        Contact contact = new Contact(1, "douqing", 20, "15091671302", FEMALE);

        mockMvc.perform(post("/api/users/{id}", user.getId())
                .content(new ObjectMapper().writeValueAsString(contact))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("huanglizhen"))

                .andExpect(jsonPath("$.contacts.[0].id").value(1))
                .andExpect(jsonPath("$.contacts.[0].name").value("douqing"))
                .andExpect(jsonPath("$.contacts.[0].sex").value("FEMALE"));
    }

    @Test
    void should_query_all_contacts() throws Exception {
        Contact douqing = new Contact(1, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        mockMvc.perform(get("/api/users/{id}", user.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("douqing"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("huanglizhen"));
    }
}
