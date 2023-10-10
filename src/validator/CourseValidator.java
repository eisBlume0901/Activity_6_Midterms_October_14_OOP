package validator;

import pojo.Course;
import pojo.Person;

import java.util.*;
import static java.lang.System.*;

public class CourseValidator implements ValidationMethod, InputValidator, Searcher
{

    private Course course;
    private Scanner scanner = new Scanner(in);

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean validate() {
        System.out.println(ANSI_GREEN + "Course: " + ANSI_RESET);
        String courseName = scanner.nextLine();

        if (isSentenceValid(courseName))
        {
            course = new Course();
            course.setCourseName(courseName);
            setCourse(course);
            return true;
        }
        return false;
    }
}
