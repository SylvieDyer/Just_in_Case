package com.csds393;
public class User {
    private String caseID;
    private String userName;
    private int isAdmin;
    private int postAnon;
    private String password;

    public User(String caseID, String userName, int isAdmin, int postAnon, String password){
        this.caseID = caseID;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.postAnon = postAnon;
        this.password = password;
    }

    public String getCaseID() {
        return this.caseID;
    }

    public void setCaseID(String caseID) {
        this.caseID = caseID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getPostAnon() {
        return this.postAnon;
    }

    public void setPostAnon(int postAnon) {
        this.postAnon = postAnon;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

