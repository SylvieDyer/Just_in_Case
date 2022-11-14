import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.csds393.Building;

public class DemoControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
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
        Building buildingA = super.mapFromJson(content, Building.class);
        assertTrue(buildingA.getName().equals("Building A"));
        assertTrue(buildingA.getDescription().equals("This is building A, A is for Apple and Anaconda."));
        assertTrue(buildingA.getStatus("Study Room A").toString().equals("NOT_BUSY"));
    }
}
