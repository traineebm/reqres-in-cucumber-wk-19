package in.reqres.model;


public class UserPojo {

    private String name;
    private String lastName;
    private String job;
    private String email;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob(){
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public static UserPojo getUserPojo(String name, String job, String email,
                                          String pwd){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setJob(job);
        userPojo.setEmail(email);
        userPojo.setPassword(pwd);
        return userPojo;
    }
}
