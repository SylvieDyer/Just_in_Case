// package com.csds393;
// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.List;

// import com.csds393.DbUtils;
// import com.csds393.LiveAlertPost;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
// import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
// import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// public class DbUtilsTest {

//   private EmbeddedDatabase db;

//   private DbUtils dbUtils;
//   @BeforeEach
//   public void setUp() {
//     db = new EmbeddedDatabaseBuilder()
//         .setType(EmbeddedDatabaseType.HSQL)
//         .setName("testDb;MODE=MySQL;" +
//                  "INIT=create schema if not exists just_in_case;" +
//                  "DB_CLOSE_DELAY=-1;")
//         .addDefaultScripts()
//         .build();
//   }

//   @AfterEach
//   public void tearDown() {
//     db.shutdown();
//   }

//   @Test
//   public void testTest() throws Exception {
//     final Connection connection = db.getConnection();
//     Statement stmt = connection.createStatement();

//     ResultSet rset = stmt.executeQuery("select description from just_in_case.building limit 1");
//     while (rset.next()) {
//       String name = rset.getString(2);
//       System.out.println("Do we have the name?" + name);
//     }

//   }

//   @Test
//   public void testFeed() throws Exception {
//     dbUtils = new DbUtils(db.getConnection());
//     final List<LiveAlertPost> feed = dbUtils.getFeed();
//     assertTrue(feed != null);
//   }
// }
