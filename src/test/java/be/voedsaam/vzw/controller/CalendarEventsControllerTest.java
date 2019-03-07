package be.voedsaam.vzw.controller;


//
//import be.voedsaam.vzw.service.DriveService;
//import be.voedsaam.vzw.service.dto.EventDTO;
//import be.voedsaam.vzw.service.mapper.EventMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.Assert.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebMvcTest(CalendarEventsController.class)
//public class CalendarEventsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DriveService driveService;
//    @MockBean
//    private EventMapper eventMapper;
//
//    @Test
//    public void contexLoads() throws Exception{
//       // assertNotNull(classUnderTest);
//    }
//
//    @Test
//    public void setDriveService() {
//
//    }
//
//    @Test
//    public void setEventMapper() {
//    }
//
//
//    @Test
//    public void getEvents() throws Exception{
//
//        this.mockMvc.perform(get("/api/events")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("")));
//    }
//}