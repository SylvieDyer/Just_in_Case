package Just_in_Case.backend;



public class User {
    private String userType;
    private String name;
    private String caseID;
    private boolean postAnon;

    public User() {
        userType = null;
        name = null;
        caseID = null;
        postAnon = true;
    }

    public User(String userType, String name, String caseID){
        this.userType = userType;
        this.name = name;
        this.caseID = caseID;
        postAnon =true;
    }

    public void changePostSettings(boolean b) {
        postAnon = b;
    }

    public String getUserType(){
        return userType;
    }
    
    public String getName(){
        return name;
    }

    public String getCaseID(){
        return caseID;
    }
    
    public boolean getPostAnon(){
        return postAnon;
    }
}

