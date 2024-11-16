import java.util.ArrayList;
import java.util.List;

public class DesiFlix {
    List<Movie> movies;
    List<User> users;

    public DesiFlix() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        this.notify(movie);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void notify(Movie movie) {

        new Thread(() -> {
            for (User user : users) {
                if (user.genres.contains(movie.genre)) {
                    user.update(movie);
                }
            }
        }).start();
    }

}

class Main {
    public static void main(String[] args) {
        User spongebob = new User("Spongebob");
        spongebob.addGenre("Comedy");
        spongebob.addGenre("Thriller");

        User patrick = new User("Patrick");
        patrick.addGenre("Comedy");
        patrick.addGenre("Horror");

        User squidward = new User("Squidward");
        squidward.addGenre("Horror");
        squidward.addGenre("Thriller");

        DesiFlix desiFlix = new DesiFlix();
        desiFlix.addUser(spongebob);
        desiFlix.addUser(patrick);
        desiFlix.addUser(squidward);

        Movie movie1 = new Movie("The Dark Night", "Thriller");
        Movie movie2 = new Movie("Mr. Bean", "Comedy");
        Movie movie3 = new Movie("The Conjuring", "Horror");

        desiFlix.addMovie(movie1);
        desiFlix.addMovie(movie2);
        desiFlix.addMovie(movie3);

    }
}
// new Thread(() -> {
// for(User user : users) {
// if(user.genres.contains(movie.genre)) {
// user.update(movie);
// }
// }
// }).start();