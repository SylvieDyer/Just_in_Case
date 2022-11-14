import static org.junit.Assert.*;
import java.io.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csds393.Building;
import com.csds393.DemoController;
import com.csds393.LiveAlertPost;
import com.csds393.Location;
import com.csds393.PostType;
import com.csds393.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(locations = {"/test-context.xml"})
@WebAppConfiguration
public class DemoControllerTest {
    protected MockMvc mvc;

    @InjectMocks
    private DemoController controller = new DemoController();
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
     }

     protected <T> T mapFromJson(String json, Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
     }

    @Test
    public void testBuildingAGET() throws Exception{
        String uri = "/buildingA";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingBGET() throws Exception{
        String uri = "/buildingB";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingCGET() throws Exception{
        String uri = "/buildingC";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingDGET() throws Exception{
        String uri = "/buildingD";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingEGET() throws Exception{
        String uri = "/buildingE";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingFGET() throws Exception{
        String uri = "/buildingF";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingGGET() throws Exception{
        String uri = "/buildingG";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testBuildingHGET() throws Exception{
        String uri = "/buildingH";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void testGetIndex() throws Exception{
        String uri = "/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.ALL))
            .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    // @Test
    // public void testPostIndex() throws Exception{
    //     String uri = "/";
    //     LiveAlertPost post = new LiveAlertPost(PostType.CLOSED, Location.BINGHAM, new User("User", "P", "p"));
    //     String inputJson = mapToJson(post);
    //     MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    //         .contentType(MediaType.ALL)
    //         .content(inputJson))
    //         .andReturn();
    //     int status = mvcResult.getResponse().getStatus();
    //     assertEquals(201, status);
    // }

    // @Test
    // public void testPostBuilding() throws Exception {
    //     String uri = "/building/post";
    //     MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
    //         .contentType(MediaType.ALL))
    //         .andReturn();
    //     int status = mvcResult.getResponse().getStatus();
    //     assertEquals(200, status);
    // }

}
