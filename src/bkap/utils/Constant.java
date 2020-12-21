/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.utils;

/**
 *
 * @author admi
 */
public class Constant {
    public static String host = "localhost"; // localhost\\MINHVUFC-PC\\SQLEXPRESS2014
    public static String port = "1433"; // 3306
    public static String schema = "HotelManager";
    public static String username = "sa"; // root
    public static String password = "1236540"; // Ubuntu - 12345678
    
    // Cú pháp SQL
    public static String SQL_LOGIN = "SELECT * FROM users WHERE Email = ? OR Username = ? AND Password = ?";
    
    // Thông điệp
    public static final String MSG_ERROR_UPDATE = "Cập nhật dữ liệu không thành công";
    public static final String MSG_SUCCESS_UPDATE = "Cập nhật dữ liệu thành công";
    public static String MSG_THONG_BAO_DANG_NHAP = "Thông báo đăng nhập";
    public static String MSG_SAI_TEN_MAT_KHAU = "Sai tên hoặc mật khẩu, vui lòng thử lại!";
    public static String MSG_DANG_NHAP_THANH_CONG = "Đăng nhập thành công";
    public static String MSG_XAC_NHAN_XOA = "Bạn chắc chắn muốn xóa";
    public static final String MSG_ERROR_PASSWORD = "Đổi mật khẩu thất bại";
    public static final String MSG_SUCCESS_PASSWORD = "Đổi mật khẩu thành công";
    
    //update password
    public static String SQL_UPDATE_PASSWORD = "UPDATE users SET Password=? WHERE Username=?";
    
    //lấy ra tất cả Users
    public static String SQL_ALL_USERS = "SELECT * FROM users";
    
    //Lấy ra tất cả Quyền
    public static String SQL_ALL_ROLES = "SELECT * FROM role_group";
    
    //Lấy ra tất cả phòng ban
    public static String SQL_ALL_DEPARTMENT = "SELECT * FROM department";
    
    //Truyền vào tên quyền và lấy ra ID của quyền phục vụ cho việc thêm mới
    public static String SQL_GET_ID_GROUP_ROLE = "SELECT Id FROM role_group WHERE Id_group_role=?";
    
    //Truỳen vào tên phòng ban và lấy ra Id phòng ban phục vụ cho việc Thêm mới User
    public static String SQL_GET_ID_DEPARTMENT = "SELECT Id FROM department WHERE Id_department=?";
}

