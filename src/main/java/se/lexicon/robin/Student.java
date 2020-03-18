package se.lexicon.robin;

public class Student {
    private int id;
    private static int counter = 0;
    private String name;
    private String email;
    private String address;

    public int getId(){
        return this.id;
    }

    public void setId(){
        counter++;
        this.id = counter;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
