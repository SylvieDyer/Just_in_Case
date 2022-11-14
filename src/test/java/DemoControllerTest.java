import static org.junit.Assert.*;
import java.io.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csds393.Building;
import com.csds393.DemoController;
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
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Building buildingA = mapFromJson(content, Building.class);
        assertTrue(buildingA.getName().equals("Building A"));
        assertTrue(buildingA.getDescription().equals("This is building A, A is for Apple and Anaconda."));
        assertTrue(buildingA.getStatus("Study Room A").toString().equals("NOT_BUSY"));
    }

}
