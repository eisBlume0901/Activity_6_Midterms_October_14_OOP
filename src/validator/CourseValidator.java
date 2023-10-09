package validator;

import pojo.Course;
import java.util.*;
import static java.lang.System.*;

public class CourseValidator implements ValidationMethod, InputValidator, Searcher
{

    private Course course;
    private Scanner scanner = new Scanner(in);
    @Override
    public boolean validate() {
        System.out.println("Course: ");
        String courseName = scanner.nextLine();

        if (isSentenceValid(courseName))
        {
            course = new Course();
            course.setCourseName(courseName);
            return true;
        }
        return false;
    }
}
