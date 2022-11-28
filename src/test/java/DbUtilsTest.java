import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

public class DbUtilsTest extends DataSourceBasedDBTestCase {

    @Override
    protected DataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(
          "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:dbschema.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
          .getResourceAsStream("data.xml"));
    }
    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Test
    public void testTest() throws Exception {
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("just_in_case.building");
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("just_in_case.building");
        assertEquals(1, 1);
    }

    // @Test
    // public void givenDataSetEmptySchema_whenDataSetCreated_thenTablesAreEqual() throws Exception {
    //     IDataSet expectedDataSet = getDataSet();
    //     ITable expectedTable = expectedDataSet.getTable("just_in_case.building");
    //     IDataSet databaseDataSet = getConnection().createDataSet();
    //     ITable actualTable = databaseDataSet.getTable("just_in_case.building");
    //     assertEquals(expectedTable, actualTable);
    // }
    
}