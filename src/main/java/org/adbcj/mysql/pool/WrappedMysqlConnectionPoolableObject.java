package org.adbcj.mysql.pool;

import org.adbcj.Connection;
import org.adbcj.DbFuture;
import org.adbcj.mysql.netty.MysqlConnectionManager;
import org.adbcj.mysql.pool.org.apache.commons.pool2.PoolableObjectFactory;

public class WrappedMysqlConnectionPoolableObject implements PoolableObjectFactory<Connection> {

    private MysqlConnectionManager connectionManager = null;

    public WrappedMysqlConnectionPoolableObject(MysqlConnectionManager connectionManager){
        super();
        this.connectionManager = connectionManager;
    }

    public Connection makeObject() throws Exception {
        DbFuture<Connection> conn = connectionManager.connect();
        return conn.get();
    }

    public void destroyObject(Connection conn) throws Exception {
        conn.close(true);
    }

    public boolean validateObject(Connection obj) {
        Connection conn = (Connection) obj;
        return !conn.isClosed();
    }

    public void activateObject(Connection obj) throws Exception {

    }

    public void passivateObject(Connection obj) throws Exception {

    }

}
