package com.thoughtworks.grads;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        Contact douqing = new Contact(1, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);
        return user;
    }

    @Test
    void should_update_contacts_when_given_user() throws Exception {
        User user = initUsersAndContacts();

        int originSize = ContactStorage.getSize();

        Contact modifyContact = new Contact(1, "doudou", 18);
        mockMvc.perform(put("/api/users/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(modifyContact)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$['contacts'][0]['name']").value("doudou"))
                .andExpect(jsonPath("$['contacts'][0]['age']").value(18));

        int currentSize = ContactStorage.getSize();
        assertEquals(originSize, currentSize);
    }

    @Test
    void should_delete_contacts_when_given_user() throws Exception {
        Contact douqing = new Contact(1, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        int originContactSize = UserStorage.getUserById(user.getId()).getContacts().size();

        int originSize = ContactStorage.getSize();
        mockMvc.perform(delete("/api/users/{userId}/contacts/{contactId}", user.getId(), douqing.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());

        int currentSize = ContactStorage.getSize();
        int currentContactSize = UserStorage.getUserById(user.getId()).getContacts().size();

        assertEquals(originSize - 1, currentSize);
        assertEquals(originContactSize - 1, currentContactSize);

    }

    @Test
    void should_query_contact() throws Exception {
        Contact douqing = new Contact(1, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, "huanglizhen", 20, "15091671302", FEMALE);

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
        Contact douqing = new Contact(1, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        mockMvc.perform(get("/api/users/{userName}/contacts/{contactName}", "hhhhhhhh", douqing.getName()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_fail_when_query_contact_not_found() throws Exception {
        Contact douqing = new Contact(1, "douqing", 20, "15091671302", FEMALE);
        Contact huanglizhen = new Contact(2, "huanglizhen", 20, "15091671302", FEMALE);

        User user = new User(5, "huanglizhen", Arrays.asList(douqing, huanglizhen));
        UserStorage.put(user);

        mockMvc.perform(get("/api/users/{userName}/contacts/{contactName}", user.getName(), "xxxxxx"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
