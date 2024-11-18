import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataInitialiser {
    private static final String[] GENRES = { "Comedy", "Thriller", "Horror" };
    private static final String[] USER_NAMES = {
            "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Hank",
            "Ivy", "Jack", "Karen", "Leo", "Mona", "Nina", "Oscar", "Paul", "Quincy", "Rachel", "Steve", "Tina"
    };
    private static final String[][] MOVIES = {
            { "The Dark Knight", "Thriller" },
            { "The Conjuring", "Horror" },
            { "Mr. Bean", "Comedy" },
            { "Inception", "Thriller" },
            { "Scary Movie", "Comedy" },
            { "Get Out", "Horror" },
            { "Shutter Island", "Thriller" },
            { "It", "Horror" },
            { "Superbad", "Comedy" },
            { "A Quiet Place", "Horror" },
            { "The Hangover", "Comedy" },
            { "Gone Girl", "Thriller" },
            { "Us", "Horror" },
            { "Pulp Fiction", "Thriller" },
            { "Home Alone", "Comedy" }
    };

    public static List<User> generateUsers() {
        List<User> users = Collections.synchronizedList(new ArrayList<>());

        Random random = new Random();

        for (int i = 0; i < 20; i++) {

            // Cycle through USER_NAMES based on the index
            String name = USER_NAMES[i % USER_NAMES.length];
            User user = new User(name);

            // Assign 1 to 3 random genres to the user
            int numberOfGenres = random.nextInt(3) + 1; // 1 to 3 genres
            for (int j = 0; j < numberOfGenres; j++) {
                String genre = GENRES[random.nextInt(GENRES.length)];
                if (!user.genres.contains(genre)) { // Avoid duplicate genres for the same user
                    user.addGenre(genre);
                }
            }
            users.add(user);
        }

        return users;
    }

    public static List<Movie> generateMovies() {
        // Create a synchronized list
        List<Movie> movies = Collections.synchronizedList(new ArrayList<>());
    
        // Add movies to the synchronized list
        for (String[] movieData : MOVIES) {
            String name = movieData[0];
            String genre = movieData[1];
            movies.add(new Movie(name, genre));
        }
    
        return movies;
    }
    
}
