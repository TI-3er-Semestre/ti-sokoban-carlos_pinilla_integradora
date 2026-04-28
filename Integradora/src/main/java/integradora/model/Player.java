package integradora.model;

public class Player {

    private String fullName;
    private String email;
    private String username;
    private String avatar;
    private String experienceLevel; // "beginner", "advanced", "expert"
    private int totalMoves;
    private int totalPushes;
    private int currentLevel;
    private long elapsedTime; // in seconds

    public Player(String fullName, String email, String username, String avatar, String experienceLevel) {
        this.fullName        = fullName;
        this.email           = email;
        this.username        = username;
        this.avatar          = avatar;
        this.experienceLevel = experienceLevel;
        this.totalMoves      = 0;
        this.totalPushes     = 0;
        this.currentLevel    = 1;
        this.elapsedTime     = 0;
    }

    public void incrementMoves() {
        totalMoves++;
    }

    public void incrementPushes() {
        totalPushes++;
    }

    public void resetStats() {
        totalMoves  = 0;
        totalPushes = 0;
        elapsedTime = 0;
    }

    public void nextLevel() {
        currentLevel++;
        resetStats();
    }

    // Getters
    public String getFullName()        {

        return fullName;
    }
    public String getEmail()           {
        return email; }
    public String getUsername()        { return username;

    }
    public String getAvatar()          {

        return avatar; }
    public String getExperienceLevel() { return experienceLevel;

    }
    public int getTotalMoves()         {

        return totalMoves;

    }
    public int getTotalPushes() {


        return totalPushes;


    }
    public int getCurrentLevel()  {


        return currentLevel;


    }
    public long getElapsedTime() {
        return elapsedTime;



    }

    // Setters
    public void setFullName(String fullName) {


        this.fullName = fullName;

    }
    public void setEmail(String email)  {

        this.email = email;

    }
    public void setUsername(String username){


        this.username = username;

    }
    public void setAvatar(String avatar) {


        this.avatar = avatar;


    }
    public void setExperienceLevel(String experienceLevel) {


        this.experienceLevel = experienceLevel;

    }
    public void setElapsedTime(long elapsedTime){


        this.elapsedTime = elapsedTime;

    }
    public void setCurrentLevel(int currentLevel){


        this.currentLevel = currentLevel; }

}