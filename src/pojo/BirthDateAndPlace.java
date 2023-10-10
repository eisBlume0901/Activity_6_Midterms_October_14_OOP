package pojo;

import java.time.*;


public class BirthDateAndPlace
{
    private Month birthMonth;

    private int birthDay;
    private int birthYear;
    private String birthPlace;

    public Month getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Month birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public String toString() {
        String birthMonthProperCapitalization = birthMonth.toString().substring(0,1).toUpperCase() +
                birthMonth.toString().substring(1).toLowerCase();
        return birthMonthProperCapitalization + " " + birthDay + ", " + birthYear;
    }
}
