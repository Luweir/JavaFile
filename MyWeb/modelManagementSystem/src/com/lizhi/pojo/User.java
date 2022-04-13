package com.lizhi.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer permissions; // 1 普通用户  2 开发者  3 管理员
    private String department; // 所属单位
    private String realName; //真实姓名
    private String telephone; // 电话
    private String email; // 邮箱

    public User(Integer id, String username, String password, Integer permissions, String department, String realName, String telephone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        this.department = department;
        this.realName = realName;
        this.telephone = telephone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permissions='" + permissions + '\'' +
                ", department='" + department + '\'' +
                ", realName='" + realName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }
}
