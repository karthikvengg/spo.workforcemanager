package spo.workforcemanager.controllers;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spo.workforcemanager.model.Contract;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkforceControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private WorkforceController workforceController;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(workforceController)
                .build();
    }

    @Test
    public void createWorkforceTest1() throws Exception {
        List<Integer> rooms = Arrays.asList(35,21,17);
        int senior = 10;
        int junior = 6;
        Contract contract = new Contract(rooms,senior,junior);

        Gson gson = new Gson();
        String json = gson.toJson(contract);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    public void createWorkforceTest2() throws Exception {
        List<Integer> rooms = Arrays.asList(24,28);
        int senior = 11;
        int junior = 6;
        Contract contract = new Contract(rooms,senior,junior);

        Gson gson = new Gson();
        String json = gson.toJson(contract);

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
}