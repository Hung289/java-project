/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.utils;
import bkap.entity.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;

/**
 *
 * @author admi
 */
public class DatabaseHelper {
    private Connection connection;
    
    public DatabaseHelper() {
        try {
            connectMicrosoftSQL();
            System.out.println("Kết nối thành công");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void connectMicrosoftSQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.connection = DriverManager.getConnection(
                "jdbc:sqlserver://" + Constant.host + ":" + Constant.port + ";databaseName=" + Constant.schema, Constant.username, Constant.password);
    }
    
    private <E> PreparedStatement getPreparedStatement(boolean inSert, String sql, E...args) throws SQLException {
        PreparedStatement ps;
        if(inSert) {
            //1. tạo PreprảedStatemnet với tùy chọn lấy về danh sách Id của dòng trong câu lệnh
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        }else {
            //2. Tao preprareStatement voi tuy chon cho phep cuon va cap nhat du lieu
            ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof Integer) {
                ps.setInt(i+1, (Integer) args[i]);
            }else if (args[i] instanceof Float) {
                ps.setFloat(i + 1, (Float) args[i]);
            } else if (args[i] instanceof Double) {
                ps.setDouble(i + 1, (Double) args[i]);
            } else if (args[i] instanceof Long) {
                ps.setLong(i + 1, (Long) args[i]);
            } else if (args[i] instanceof String) {
                ps.setString(i + 1, (String) args[i]);
            }
        }
        return ps;
    }
    
    private <E> CallableStatement getCallableStatement(String sql, E...args) throws SQLException{
        CallableStatement cs;
        
            cs = connection.prepareCall(sql);
        
        
        for (int i = 0; i < args.length; i++) {
            if(args[i] instanceof Integer) {
                cs.setInt(i+1, (Integer) args[i]);
            }else if (args[i] instanceof Float) {
                cs.setFloat(i + 1, (Float) args[i]);
            } else if (args[i] instanceof Double) {
                cs.setDouble(i + 1, (Double) args[i]);
            } else if (args[i] instanceof Long) {
                cs.setLong(i + 1, (Long) args[i]);
            } else if (args[i] instanceof String) {
                cs.setString(i + 1, (String) args[i]);
            }
        }
        return cs;
    }
    
    /**
     * Hàm lấy về dữ liệu: SELECT
     */
    public <E> ResultSet selectData(String sql, E...args) throws SQLException {
        PreparedStatement ps = getPreparedStatement(false, sql, args);
        return ps.executeQuery();
    }
    
    public <E> ResultSet selectDataCall(String sql, E...args) throws SQLException {
        CallableStatement cs = getCallableStatement(sql, args);
        return cs.executeQuery();
    }
    
    public <E> int updateData(String sql, E... args) throws SQLException {
        PreparedStatement ps = getPreparedStatement(false, sql, args);
        return ps.executeUpdate();
    }
    
    public <E> int updateDataCall(String sql, E...args) throws SQLException {
        CallableStatement cs = getCallableStatement(sql, args);
        return cs.executeUpdate();
    }
    
    public <E> ResultSet insertData(String sql, E... args) throws SQLException {
        PreparedStatement ps = getPreparedStatement(true, sql, args);
        if(ps.executeUpdate() > 0) {
            return ps.getGeneratedKeys();
        } else {
            return null;
        }
//        return ps.executeUpdate();
    }
    
    public <E> int insertDataCall(String sql, E... args) throws SQLException {
        CallableStatement cs = getCallableStatement(sql, args);
        return cs.executeUpdate();
//        return ps.executeUpdate();
    }
    
    public <E> int deleteDataCall(String sql, E...args) throws SQLException {
        CallableStatement cs = getCallableStatement(sql, args);
        return cs.executeUpdate();
    }
    
    public int insertRoom(String sql, Room room) throws SQLException {
        CallableStatement cs = connection.prepareCall(sql);
        cs.setString(1, room.getName());
        cs.setFloat(2, room.getPrice());
        cs.setFloat(3, room.getAcreage());
        cs.setString(4, room.getImg());
        cs.setInt(5, room.getPeople());
        cs.setString(6, room.getNote());
        cs.setInt(7, room.getStatus());
        cs.setInt(8, room.getCategoryRoom());
        cs.registerOutParameter(9, java.sql.Types.INTEGER);
        cs.executeUpdate();
        return cs.getInt(9);
    }
}
