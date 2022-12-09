import java.util.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import com.csds393.Facility;
import com.csds393.Status;

public class FacilityTest {
    @Test
    public void testFacility_nonParam(){
        Facility facility = new Facility();
        assertEquals(0,facility.getFacilityID());
        assertNull(facility.getFacilityName());
        assertNull(facility.getStatus());
        assertNull(facility.getStatusLastUpdated());
    }

    @Test
    public void testFacility_threeParam(){
        Facility facility = new Facility("cafe",Status.NOT_BUSY,new Timestamp(System.currentTimeMillis()));
        assertEquals("cafe",facility.getFacilityName());
        assertEquals(Status.NOT_BUSY,facility.getStatus());
        assertEquals(new Timestamp(System.currentTimeMillis()), facility.getStatusLastUpdated());
    }

    @Test
    public void testFacility_fourParam(){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Facility facility = new Facility(100,"cafe",Status.NOT_BUSY,date);
        assertEquals(100,facility.getFacilityID());
        assertEquals("cafe",facility.getFacilityName());
        assertEquals(Status.NOT_BUSY,facility.getStatus());
        assertEquals(date, facility.getStatusLastUpdated());
    }

    @Test
    public void testGetFacilityID(){
        Facility facility = new Facility(100,"cafe",Status.NOT_BUSY,new Timestamp(System.currentTimeMillis()));
        assertEquals(100,facility.getFacilityID());
    }

    @Test
    public void testGetFacilityName(){
        Facility facility = new Facility(100,"cafe",Status.NOT_BUSY,new Timestamp(System.currentTimeMillis()));
        assertEquals("cafe",facility.getFacilityName());
    }

    @Test
    public void testGetStatus(){
        Facility facility = new Facility(100,"cafe",Status.NOT_BUSY,new Timestamp(System.currentTimeMillis()));
        assertEquals(Status.NOT_BUSY,facility.getStatus());
    }

    @Test
    public void testGetStatusLastUpdated(){
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Facility facility = new Facility(100,"cafe",Status.NOT_BUSY,date);
        assertEquals(date, facility.getStatusLastUpdated());
    }

    @Test
    public void testSetFacilityID(){
        Facility facility = new Facility();
        facility.setFacilityID(100);
        assertEquals(100,facility.getFacilityID());
    }

    @Test
    public void testSetFacilityName(){
        Facility facility = new Facility();
        facility.setFacilityName("cafe");
        assertEquals("cafe",facility.getFacilityName());
    }

    @Test
    public void testSetStatus(){
        Facility facility = new Facility();
        facility.setStatus(Status.NOT_BUSY);
        assertEquals(Status.NOT_BUSY,facility.getStatus());
    }

    @Test
    public void testSetStatusLastUpdated(){
        Facility facility = new Facility();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        facility.setStatusLastUpdated(date);
        assertEquals(date, facility.getStatusLastUpdated());
    }


}
