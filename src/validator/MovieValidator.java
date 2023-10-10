package validator;

import pojo.Movie;
import pojo.Person;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class MovieValidator implements ValidationMethod, InputValidator
{
    private Scanner scanner = new Scanner(in);
    private Movie movie;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean validate() {

        out.println(ANSI_BLUE + "\nFavorites");
        out.println("Movie title: ");
        String movieName = scanner.nextLine();
        out.println("Movie character: " + ANSI_RESET);
        String movieCharacter = scanner.nextLine();

        if (isMovieCharacter(movieName, movieCharacter))
        {
            movie = new Movie();
            movie.setMovieTitle(movieName);
            movie.setMovieCharacter(movieCharacter);
            setMovie(movie);
            return true;
        }
        return false;
    }

}
