package pojo;

public class Person
{
    private Name name;
    private Address address;
    private Course course;
    private Movie movie;
    private Food food;

    private UserNumber userNumber;
    private BirthDateAndPlace birthDatePlace;

    public Name getName() {
        return name;
    }
    public BirthDateAndPlace getBirthDatePlace() {
        return birthDatePlace;
    }

    public Address getAddress() {
        return address;
    }

    public Course getCourse() {
        return course;
    }

    public Movie getMovie() {
        return movie;
    }

    public Food getFood() {
        return food;
    }

    public UserNumber getUserNumber() {
        return userNumber;
    }



}
