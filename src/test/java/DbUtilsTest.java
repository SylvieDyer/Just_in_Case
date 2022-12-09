import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.csds393.Building;
import com.csds393.DbUtils;
import com.csds393.Facility;
import com.csds393.LiveAlertPost;
import com.csds393.Location;
import com.csds393.PostType;
import com.csds393.Status;
import com.csds393.User;
import com.mysql.cj.xdevapi.Result;

import org.apache.catalina.startup.UserConfig;
import org.hsqldb.map.BaseHashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbUtilsTest {

    private EmbeddedDatabase db;
    @BeforeEach
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .setName("testDb;MODE=MySQL;" +
                        "DATABASE_TO_UPPER=false;" +
                        "DB_CLOSE_DELAY=-1;")
            .addDefaultScripts()
            .build();
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void connectionInitTest() throws Exception {
        assertTrue(db.getConnection() != null);
    }

    @Test
    public void dbUtilsInitTest() throws Exception {
        DbUtils dbUtils = new DbUtils(db.getConnection());
        assertTrue(dbUtils != null); 
    }

    @Test
    public void testGetPost() throws Exception {
        long postID = 1;

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ postID + "'");
        rs.next();

        DbUtils dbUtils = new DbUtils(conn);
        LiveAlertPost post = dbUtils.getPost(postID);

        assertEquals(post, getLiveAlertPostFromResultSet(rs));
    }

    @Test
    public void testFeed() throws Exception {
        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.livealertpost");

        DbUtils dbUtils = new DbUtils(conn);
        List<LiveAlertPost> feed = dbUtils.getFeed();
        assertTrue(feed != null);

        for (int i = 0; i < feed.size(); i++) {
            rs.next();
            assertEquals(feed.get(i), getLiveAlertPostFromResultSet(rs));
        }
    }

    @Test
    public void testAddLiveAlertPostFromObjectDefaultUser() throws Exception {
        LiveAlertPost liveAlertPost = new LiveAlertPost(PostType.EXCESSIVE_SNOW, 
                Location.CLEVELAND_INSTITUTE_OF_ART, 4, 5);

        DbUtils dbUtils = new DbUtils(db.getConnection());
        LiveAlertPost returnedPost = dbUtils.addLiveAlertPost(liveAlertPost, null);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ returnedPost.getPostID() + "'");
        insertedResultSet.next();
        LiveAlertPost queriedPost = getLiveAlertPostFromResultSet(insertedResultSet);

        assertEquals(queriedPost.getPostType(), PostType.EXCESSIVE_SNOW);
        assertEquals(queriedPost.getLocation(), Location.CLEVELAND_INSTITUTE_OF_ART);
        assertEquals(queriedPost.getNumUpvotes(), 4);
        assertEquals(queriedPost.getNumDownvotes(), 5);

        conn.close();
    }

    @Test
    public void testAddLiveAlertPostFromObjectSetUser() throws Exception {
        LiveAlertPost liveAlertPost = new LiveAlertPost(PostType.EXCESSIVE_SNOW, 
                Location.CLEVELAND_INSTITUTE_OF_ART, 4, 5);
        User user = new User("def456", "Test2", 0, 1, "def456");

        DbUtils dbUtils = new DbUtils(db.getConnection());
        LiveAlertPost returnedPost = dbUtils.addLiveAlertPost(liveAlertPost, user);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ returnedPost.getPostID() + "'");
        insertedResultSet.next();
        LiveAlertPost queriedPost = getLiveAlertPostFromResultSet(insertedResultSet);

        assertEquals(queriedPost.getPostType(), PostType.EXCESSIVE_SNOW);
        assertEquals(queriedPost.getLocation(), Location.CLEVELAND_INSTITUTE_OF_ART);
        assertEquals(queriedPost.getNumUpvotes(), 4);
        assertEquals(queriedPost.getNumDownvotes(), 5);

        ResultSet postsResultSet = stmt.executeQuery(
            "SELECT * FROM just_in_case.posts WHERE postId='"+ returnedPost.getPostID() + "'");
        postsResultSet.next();

        assertEquals(postsResultSet.getString("caseID"), "def456");
        
        conn.close();
    }

    @Test
    public void testAddLiveAlertPostFromObjectSetUserID() throws Exception {
        LiveAlertPost liveAlertPost = new LiveAlertPost(PostType.EXCESSIVE_SNOW, 
                Location.CLEVELAND_INSTITUTE_OF_ART, 4, 5);
        String caseID = "def456";

        DbUtils dbUtils = new DbUtils(db.getConnection());
        LiveAlertPost returnedPost = dbUtils.addLiveAlertPostWithID(liveAlertPost, caseID);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ returnedPost.getPostID() + "'");
        insertedResultSet.next();
        LiveAlertPost queriedPost = getLiveAlertPostFromResultSet(insertedResultSet);

        assertEquals(queriedPost.getPostType(), PostType.EXCESSIVE_SNOW);
        assertEquals(queriedPost.getLocation(), Location.CLEVELAND_INSTITUTE_OF_ART);
        assertEquals(queriedPost.getNumUpvotes(), 4);
        assertEquals(queriedPost.getNumDownvotes(), 5);

        ResultSet postsResultSet = stmt.executeQuery(
            "SELECT * FROM just_in_case.posts WHERE postId='"+ returnedPost.getPostID() + "'");
        postsResultSet.next();

        assertEquals(postsResultSet.getString("caseID"), "def456");
        
        conn.close();
    }

    @Test
    public void testDeletePost() throws Exception {
        LiveAlertPost liveAlertPost = new LiveAlertPost(PostType.EXCESSIVE_SNOW, 
                Location.CLEVELAND_INSTITUTE_OF_ART, 4, 5);

        DbUtils dbUtils = new DbUtils(db.getConnection());
        dbUtils.addLiveAlertPost(liveAlertPost, null);
        long returnedPostID = dbUtils.deleteLiveAlertPost(liveAlertPost);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.livealertpost WHERE postId='"+ returnedPostID + "'");
        assertFalse(insertedResultSet.next());

        conn.close();
    }

    @Test
    public void testGetBuildingByID() throws Exception {
        long buildingID = 1;

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.building WHERE buildingId='"+ buildingID + "'");
        rs.next();

        DbUtils dbUtils = new DbUtils(conn);
        Building building = dbUtils.getBuildingByID(buildingID);
        Building queriedBuilding = getBuildingFromResultSet(rs);

        assertEquals(building.getBuildingID(), queriedBuilding.getBuildingID());
        assertEquals(building.getBuildingName(), queriedBuilding.getBuildingName());
        assertEquals(building.getDescription(), queriedBuilding.getDescription());
    }

    @Test
    public void testGetBuildingByName() throws Exception {
        String buildingName = "Building1";

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM just_in_case.building WHERE buildingName='" + buildingName + "'");
        rs.next();

        DbUtils dbUtils = new DbUtils(conn);
        Building building = dbUtils.getBuildingByName(buildingName);
        Building queriedBuilding = getBuildingFromResultSet(rs);

        assertEquals(building.getBuildingID(), queriedBuilding.getBuildingID());
        assertEquals(building.getBuildingName(), queriedBuilding.getBuildingName());
        assertEquals(building.getDescription(), queriedBuilding.getDescription());
    }

    @Test
    public void testBuildingHub() throws Exception {
        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.building");

        DbUtils dbUtils = new DbUtils(conn);
        List<Building> bh = dbUtils.getBuildingHub();
        assertTrue(bh != null);

        for (int i = 0; i < bh.size(); i++) {
            rs.next();
            Building queriedBuilding = getBuildingFromResultSet(rs);

            assertEquals(bh.get(i).getBuildingID(), queriedBuilding.getBuildingID());
            assertEquals(bh.get(i).getBuildingName(), queriedBuilding.getBuildingName());
            assertEquals(bh.get(i).getDescription(), queriedBuilding.getDescription());
        }
    }

    @Test
    public void testAddBuildingFromObjectDefaultFacilities() throws Exception {
        Building building = new Building("Building3", "Description3");

        DbUtils dbUtils = new DbUtils(db.getConnection());
        Building returnedBuilding = dbUtils.addBuilding(building, null);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.building WHERE buildingID='"+ returnedBuilding.getBuildingID() + "'");
        insertedResultSet.next();
        Building queriedBuilding = getBuildingFromResultSet(insertedResultSet);

        assertEquals(queriedBuilding.getBuildingName(), "Building3");
        assertEquals(queriedBuilding.getDescription(), "Description3");

        conn.close();
    }

    @Test
    public void testAddBuildingFromObjectSetFacilities() throws Exception {
        Building building = new Building("Building3", "Description3");
        Facility facility = new Facility(4, "B3just_in_case.facility1", Status.FAIRLY_BUSY, Timestamp.from(Instant.now()));
        List<Facility> facilities = new ArrayList<Facility>();
        facilities.add(facility);

        DbUtils dbUtils = new DbUtils(db.getConnection());
        Building returnedBuilding = dbUtils.addBuilding(building, facilities);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.building WHERE buildingID='"+ returnedBuilding.getBuildingID() + "'");
        insertedResultSet.next();
        Building queriedBuilding = getBuildingFromResultSet(insertedResultSet);

        assertEquals(queriedBuilding.getBuildingName(), "Building3");
        assertEquals(queriedBuilding.getDescription(), "Description3");


        ResultSet withinResultSet = stmt.executeQuery(
            "SELECT * FROM just_in_case.within WHERE buildingID='"+ returnedBuilding.getBuildingID() + "'");
        withinResultSet.next();

        assertNotNull(withinResultSet.getLong("facilityID"));
        
        conn.close();
    }

    @Test
    public void testDeleteBuilding() throws Exception {
        Building building = new Building("toDelete", "toDelete");

        DbUtils dbUtils = new DbUtils(db.getConnection());
        dbUtils.addBuilding(building, null);
        long returnedBuildingID = dbUtils.deleteBuilding(building);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.building WHERE buildingID='"+ returnedBuildingID + "'");
        assertFalse(insertedResultSet.next());

        conn.close();
    }

    @Test
    public void testGetFacilitiesByBuildingID() throws Exception {
        long buildingID = 1;

        DbUtils dbUtils = new DbUtils(db.getConnection());
        List<Facility> facilities = dbUtils.getFacilitiesByID(buildingID);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.within " +  
            "WHERE just_in_case.within.buildingID = '" + buildingID + "'");
        
        for(int i = 0; i < facilities.size(); i++) {
            rs.next();
            assertEquals(facilities.get(i).getFacilityID(), rs.getLong("facilityID"));
        }

        conn.close();

    }
    
    @Test
    public void testGetFacilitiesByBuildingName() throws Exception {
        String buildingName = "Building1";

        DbUtils dbUtils = new DbUtils(db.getConnection());
        List<Facility> facilities = dbUtils.getFacilitiesByName(buildingName);
        DbUtils dbUtils2 = new DbUtils(db.getConnection());
        Building building = dbUtils2.getBuildingByName(buildingName);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.within " +  
            "WHERE just_in_case.within.buildingID = '" + building.getBuildingID() + "'");
        
        for(int i = 0; i < facilities.size(); i++) {
            rs.next();
            assertEquals(facilities.get(i).getFacilityID(), rs.getLong("facilityID"));
        }

        conn.close();

    }

    @Test
    public void testAddFacility() throws Exception {
        Facility facility = new Facility("B1just_in_case.facility3", Status.SUPER_BUSY, Timestamp.from(Instant.now()));
        Building building = new Building(1, "Building1", "Description1");

        DbUtils dbUtils = new DbUtils(db.getConnection());
        Facility returnedFacility = dbUtils.addFacility(facility, building);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.facility WHERE facilityID='"+ returnedFacility.getFacilityID() + "'");
        insertedResultSet.next();
        Facility queriedFacility = getFacilityFromResultSet(insertedResultSet);

        assertEquals(queriedFacility.getFacilityName(), "B1just_in_case.facility3");
        assertEquals(queriedFacility.getStatus(), Status.SUPER_BUSY);

        ResultSet withinResultSet = stmt.executeQuery(
            "SELECT * FROM just_in_case.within WHERE facilityID='"+ returnedFacility.getFacilityID() + "'");
            withinResultSet.next();

        assertEquals(withinResultSet.getLong("buildingID"), 1);
        
        conn.close();
    }

    @Test
    public void testDeleteFacility() throws Exception {
        Facility facility = new Facility("toDelete", Status.CLOSED, Timestamp.from(Instant.now()));
        Building building = new Building(1, "Building1", "Description1");

        DbUtils dbUtils = new DbUtils(db.getConnection());
        dbUtils.addFacility(facility, building);
        long returnedFacilityID = dbUtils.deleteFacility(facility);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.facility WHERE facilityID='"+ returnedFacilityID + "'");
        assertFalse(insertedResultSet.next());

        conn.close();
    }

    @Test
    public void testAddFacilityByBuildingID() throws Exception {
        Facility facility = new Facility("B1just_in_case.facility3", Status.SUPER_BUSY, Timestamp.from(Instant.now()));
        long buildingID = 1;

        DbUtils dbUtils = new DbUtils(db.getConnection());
        Facility returnedFacility = dbUtils.addFacilityByID(facility, buildingID);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.facility WHERE facilityID='"+ returnedFacility.getFacilityID() + "'");
        insertedResultSet.next();
        Facility queriedFacility = getFacilityFromResultSet(insertedResultSet);

        assertEquals(queriedFacility.getFacilityName(), "B1just_in_case.facility3");
        assertEquals(queriedFacility.getStatus(), Status.SUPER_BUSY);

        ResultSet withinResultSet = stmt.executeQuery(
            "SELECT * FROM just_in_case.within WHERE facilityID='"+ returnedFacility.getFacilityID() + "'");
            withinResultSet.next();

        assertEquals(withinResultSet.getLong("buildingID"), 1);
        
        conn.close();
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Facility facility = new Facility(4, "B3just_in_case.facility1", Status.FAIRLY_BUSY, Timestamp.from(Instant.now()));
        Status newStatus = Status.CLOSED;

        DbUtils dbUtils = new DbUtils(db.getConnection());
        Facility returnedFacility = dbUtils.updateStatus(facility, newStatus);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
            "SELECT * FROM just_in_case.facility WHERE facilityID='"+ returnedFacility.getFacilityID() + "'");
        insertedResultSet.next();

        assertEquals(insertedResultSet.getString("status"), Status.CLOSED.toString());
    }

    @Test
    public void testGetUserByID() throws Exception {
        String caseID = "abc123";
        DbUtils dbUtils = new DbUtils(db.getConnection());
        User user = dbUtils.getUserByID(caseID);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.app_users " +  
            "WHERE caseID = '" + caseID + "'");
        rs.next();
        User queriedUser = getUserFromResultSet(rs);

        assertEquals(user.getCaseID(), queriedUser.getCaseID());
        assertEquals(user.getIsAdmin(), queriedUser.getIsAdmin());
        assertEquals(user.getPassword(), queriedUser.getPassword());
        assertEquals(user.getPostAnon(), queriedUser.getPostAnon());
        assertEquals(user.getUserName(), queriedUser.getUserName());

    }

    @Test
    public void testGetUserByPost() throws Exception {
        long postID = 1;
        DbUtils dbUtils = new DbUtils(db.getConnection());
        String caseID = dbUtils.getUserByPost(postID);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM just_in_case.posts " +  
            "WHERE postID = '" + postID + "'");
        rs.next();
        assertEquals(caseID, rs.getString("caseID"));

    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User("test", "test", 0, 1, "pass!23");

        DbUtils dbUtils = new DbUtils(db.getConnection());
        User returnedUser = dbUtils.addUser(user);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet insertedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.app_users WHERE caseID='"+ returnedUser.getCaseID() + "'");
        insertedResultSet.next();
        User queriedUser = getUserFromResultSet(insertedResultSet);

        assertEquals(queriedUser.getCaseID(), "test");
        assertEquals(queriedUser.getUserName(), "test");
        assertEquals(queriedUser.getIsAdmin(), 0);
        assertEquals(queriedUser.getPostAnon(), 1);
        assertEquals(queriedUser.getPassword(), "pass!23");

        conn.close();
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User("test", "test", 0, 1, "pass!23");
        DbUtils dbUtils = new DbUtils(db.getConnection());
        dbUtils.addUser(user);
        DbUtils dbUtils2 = new DbUtils(db.getConnection());
        boolean returnedCaseID = dbUtils2.deleteUser(user);
        assertTrue(returnedCaseID);

        final Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet deletedResultSet = stmt.executeQuery(
                    "SELECT * FROM just_in_case.app_users WHERE caseID='"+ "test" + "'");
        assertFalse(deletedResultSet.next());

        conn.close();
    }


    private LiveAlertPost getLiveAlertPostFromResultSet(ResultSet rs) {
        String postTypeString;
        try {
            postTypeString = rs.getString("postType");
            PostType postType = PostType.DEFAULT;
            for(PostType pt : PostType.values()) {
                if (postTypeString.equals(pt.toString())) {
                    postType = pt;
                }
            }
                
            String locationString = rs.getString("location");
            Location location = Location.DEFAULT;
            for (Location l : Location.values()) {
                if(locationString.equals(l.toString())) {
                    location = l;
                }
            }
            return new LiveAlertPost(rs.getLong("postID"), postType, location, 
                rs.getTimestamp("time"), rs.getInt("numUpvotes"), 
                rs.getInt("numDownvotes"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private Building getBuildingFromResultSet(ResultSet rs) {
        try {
            return new Building(rs.getLong("buildingID"), rs.getString("buildingName"),  
            rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Facility getFacilityFromResultSet(ResultSet rs) {
        try {
            String statusString =  rs.getString("status");
            Status status = Status.NOT_BUSY;
            for(Status s : Status.values()) {
                if(statusString.equals(s.toString())) {
                    status = s;
                }
            } 
            return new Facility(rs.getLong("facilityID"), rs.getString("facilityName"),  
                status, rs.getTimestamp("statusLastUpdated"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet rs) {
        try {
            return new User(rs.getString("caseID"), rs.getString("userName"),  
            rs.getInt("isAdmin"), rs.getInt("postAnon"), rs.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
