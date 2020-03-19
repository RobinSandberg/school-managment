package se.lexicon.robin;

public class Student {
    private final int id;
    private static int counter = 0;
    private String name;
    private String email;
    private String address;

    public Student(String name, String email , String address){
        this.name = name;
        this.email = email;
        this.address = address;
        this.id = ++counter;
    }

    public int getId(){
        return this.id;
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

    public void reset(){
        counter = 0;
    }

    @Override
    public String toString() {
        return "\nStudent id = " + id +
                "\nStudent name = " + name +
                "\nStudent email = " + email +
                "\nStudent address = " + address;
    }
}
