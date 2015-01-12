package model;

public class User {

    private int userid;
    private String userName;
    private String password;
    private String rol;
    
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
    	return rol;
    }
    public void setRol(String rol) {
    	this.rol = rol;
    }
    @Override
    public String toString() {
        return "User [userid=" + userid + ", userName=" + userName
                + ", password=" + password + ", rol=" + rol + "]";
    }    
}
