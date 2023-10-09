package validator;

import pojo.Movie;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class MovieValidator implements ValidationMethod, InputValidator
{
    private Scanner scanner = new Scanner(in);
    private Movie movie;

    @Override
    public boolean validate() {
        // Favorites sector
        out.println("Movie title: ");
        String movieName = scanner.nextLine();
        out.println("Movie character: ");
        String movieCharacter = scanner.nextLine();

        // Add a database of movies
        if (isMovieCharacter(movieName, movieCharacter))
        {
            movie = new Movie();
            movie.setMovieTitle(movieName);
            movie.setMovieCharacter(movieCharacter);

            // For debugging, have to remove
            out.println(movie.getMovieTitle() + " " + movie.getMovieCharacter());
            return true;
        }
        return false;
    }
}
