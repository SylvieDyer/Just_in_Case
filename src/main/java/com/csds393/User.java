package com.csds393;
public class User {
    private String caseID;
    private String userName;
    private int isAdmin;
    private int postAnon;

    public User(String caseID, String userName, int isAdmin, int postAnon){
        this.caseID = caseID;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.postAnon = postAnon;
    }

    public String getCaseID() {
        return this.caseID;
    }
    
    public void setCaseId(String caseID) {
        this.caseID = caseID;
    }
    
    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getPostAnon(){
        return postAnon;
    }

    public void setPostAnon(int anon) {
        postAnon = anon;
    }
}

