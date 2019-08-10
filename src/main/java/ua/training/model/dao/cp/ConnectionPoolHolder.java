package ua.training.model.dao.cp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {

    private final static Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);


    private static volatile DataSource dataSource;

    /**
     * Manages connection to database
     * @return datasource object (singleton) with connection properties
     */
    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {

                    try {
                        Properties properties = new Properties();
                        properties.load(ConnectionPoolHolder.class.getClassLoader()
                                .getResourceAsStream("db.properties"));
                        BasicDataSource ds = new BasicDataSource();
                        ds.setDriverClassName(properties.getProperty("db_connection_driver"));
                        ds.setUrl(properties.getProperty("url"));
                        ds.setUsername(properties.getProperty("username"));
                        ds.setPassword(properties.getProperty("password"));
                        ds.setMinIdle(Integer.valueOf((properties.getProperty("min_idle"))));
                        ds.setMaxIdle(Integer.valueOf((properties.getProperty("max_idle"))));
                        ds.setMaxOpenPreparedStatements(Integer.valueOf(properties.getProperty("max_open_prepared_statements")));
                        dataSource = ds;

                    } catch (IOException e) {
                        logger.error(Messages.CONNECTION_POOL_DATA_FAIL);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return dataSource;
    }
}
