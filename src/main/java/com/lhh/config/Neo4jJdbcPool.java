package com.lhh.config;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class Neo4jJdbcPool implements DataSource{

    /**
    * @Field: listConnections
    *         使用LinkedList集合来存放数据库链接，
    *        由于要频繁读写List集合，所以这里使用LinkedList存储数据库连接比较合适
    */ 
    private static LinkedList<Connection> listConnections = new LinkedList<Connection>();
    private static Neo4jJdbcPool pool;
    
    static{
        for (int i = 0; i < Neo4jJdbcUtils.JDBCPOOLINITSIZE; i++) {
            Connection conn = new Neo4jJdbcUtils().getConnection();
            listConnections.add(conn);
        }
        pool = new Neo4jJdbcPool();
    }
    
    private Neo4jJdbcPool(){
    }
    public static Neo4jJdbcPool getPool(){
    	return pool;
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        //如果数据库连接池中的连接对象的个数大于0
        if (listConnections.size()>0) {
            //从listConnections集合中获取一个数据库连接
            final Connection conn = listConnections.removeFirst();
            //System.out.println("listConnections数据库连接池大小是" + listConnections.size());
            //返回Connection对象的代理对象
            return (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler(){
                @Override
                public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                    if(!method.getName().equals("close")){
                        return method.invoke(conn, args);
                    }else{
                        //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
                        listConnections.add(conn);
                        //System.out.println(conn + "被还给listConnections数据库连接池了！！");
                        //System.out.println("listConnections数据库连接池大小为" + listConnections.size());
                        return null;
                    }
                }
            });
        }else {
            //throw new RuntimeException("对不起，数据库忙");
        	return null;
        }
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }


    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        return null;
    }

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
