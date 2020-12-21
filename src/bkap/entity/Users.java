/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.entity;

/**
 *
 * @author admi
 */
public class Users {
    private int id;
    private String username;
    private String password;
    private String img;
    private String name;
    private String phone;
    private String email;
    private String note;
    private int idDepartment;
    private int idRoleGroup;
    private String birthday;
    private int gender;
    private String address;
    private int workDay;
    private int status;

    /**
     * Hàm khởi tạo nhân viên dùng cho đăng nhập
     * @param username
     * @param password
     */
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(int id, String username, String password, String img, String name, String phone, String email, String note, int idDepartment, int idRoleGroup, String birthday, int gender, String address, int workDay, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.img = img;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.idDepartment = idDepartment;
        this.idRoleGroup = idRoleGroup;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.workDay = workDay;
        this.status = status;
    }
    

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Users() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getIdRoleGroup() {
        return idRoleGroup;
    }

    public void setIdRoleGroup(int idRoleGroup) {
        this.idRoleGroup = idRoleGroup;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    
    
}
