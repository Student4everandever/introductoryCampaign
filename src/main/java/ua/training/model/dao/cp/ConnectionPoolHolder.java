package ua.training.model.dao.cp;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;

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
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return dataSource;
    }
}
