package com.booklib.booklib;

import com.booklib.booklib.Entities.Patron;
import com.booklib.booklib.Service.PatronService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PatronServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @Test
    public void testGetPatronById() throws Exception {

        when(patronService.getPatronById(1L)).thenReturn(new Patron("ahmed","01121166608"));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ahmed"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contactInformation").value("01121166608"));
    }

}
