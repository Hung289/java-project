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
    public static String password = "1234$"; // Ubuntu - 12345678
    
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
    public static final String MSG_SUCCESS_INSERT_USERS = "Thêm mới thành công USER";
    public static final String MSG_SUCCESS_UPDATE_USER = "Cập nhật thành công USER";
    public static final String MSG_SUCCESS_DELETE = "Xóa Thất bại";
    
    
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
    public static String SQL_GET_ID_DEPARTMENT = "SELECT Id FROM department WHERE Name=?";
    
    //Thêm mới dữ liệu vào bảng USER
    public static String SQL_INSERT_INTO_USERS = "INSERT INTO users(Username, Password, Img, Name, Phone, Email, Note, Id_department, Id_role_group, Birthday, Gender, Address, Workday, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    //Lấy ra tất cả user và role_group 
//    public static String SQL_USER_AND_USER_GROUP = "SELECT u.*, rg.* FROM users u, role_group rg WHERE u.Id_role_group = rg.Id and rg.Id =?";

    //cách đăng nhập không phải chọn quyền trước
    public static String SQL_ROLE_BY_USER_PASS = "SELECT * FROM users WHERE Username=? and Password=?";
    
    //update USERS Call
    public static String SQL_UPDATE_USER = "{call user_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    
    //delete user Call
    public static String SQL_DELETE_USER = "{call user_delete(?)}";
    
    //Lấy ra tất cả user theo Vai trò
    public static String SQL_USERS_BY_ROLE = "{call user_search_role(?)}";
    //Lấy ra tất cả user theo phòng ban
    public static String SQL_USERS_BY_DEPARTMENT = "{call user_search_department(?)}";
    //Lấy ra tất cả user theo trạng thái
    public static String SQL_USERS_BY_STATUS = "{call user_search_status(?)}";
    
    
    //Lấy ra tất cả department
    public static String SQL_GET_ALL_DEPARTMENT = "{call get_all_department}";
    //Insert department
    public static String SQL_INSERT_DEPARTMENT = "{call insert_department}";
    
    
    //    -------------------service--------------------------------------
//    insert service 
    public static String SQL_INSERT_INTO_SERVICE ="{call service_insert(?,?)}";
//    select service 
    public static String SQL_SELECT_SERVICE = "{call service_select()}";
//    select service by id 
    public static String SQL_SELECT_SERVICE_BY_ID ="{call service_selectById(?)}";
//    update service 
    public static String SQL_UPDATE_SERVICE ="{call service_update(?,?,?)}";
//    delete service 
    public static String SQL_DELETE_SERVICE ="{call service_delete(?)}";
    
    //    -------------------Infrastructure--------------------------------------
//    insert service 
    public static String SQL_INSERT_INTO_INFRASTRUCTURE ="{call infrastructure_insert(?,?)}";
//    select service 
    public static String SQL_SELECT_INFRASTRUCTURE = "{call infrastructure_select()}";
//    select service by id 
    public static String SQL_SELECT_INFRASTRUCTURE_BY_ID ="{call infrastructure_selectById(?)}";
//    update service 
    public static String SQL_UPDATE_INFRASTRUCTURE ="{call infrastructure_update(?,?,?)}";
//    delete service 
    public static String SQL_DELETE_INFRASTRUCTURE ="{call infrastructure_delete(?)}";
    
//    ----------------------------category rooom-----------------------------------
    // insert categoryroom call
    public static String SQL_INSERT_CATEGORYROOM = "{call insertcrb(?,?)}";
    
    // select categoryroom call
    public static String SQL_SELECT_CATEGORYROOM = "{call getdatacrb}";
    
    // update categoryroom call
    public static String SQL_UPDATE_CATEGORYROOM = "{call updatecrb(?,?,?)}";
    
    // delete categoryroom call
    public static String SQL_DELETE_CATEGORYROOM = "{call deletecrb(?)}";
}

