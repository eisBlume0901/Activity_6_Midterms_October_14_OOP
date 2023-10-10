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

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public UserNumber getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(UserNumber userNumber) {
        this.userNumber = userNumber;
    }

    public BirthDateAndPlace getBirthDatePlace() {
        return birthDatePlace;
    }

    public void setBirthDatePlace(BirthDateAndPlace birthDatePlace) {
        this.birthDatePlace = birthDatePlace;
    }
}
