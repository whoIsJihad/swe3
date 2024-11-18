import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.Collections;

public class DesiFlix {
    List<Movie> movies;
    List<User> users;
    private final ExecutorService executor;

    public DesiFlix() {
        this.movies = Collections.synchronizedList(new ArrayList<>());
        this.users = Collections.synchronizedList(new ArrayList<>());
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        this.notify(movie);
    }

    public void addUser(User user) {
        synchronized (this) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        synchronized (this) {
            users.remove(user);
        }
    }

    public void notify(Movie movie) {
        executor.submit(() -> {
            synchronized (users) {
                for (User user : users) {
                    if (user.genres.contains(movie.genre)) {
                        user.update(movie);
                    }
                }
            }
        });
    }
    public void showUserNotifications(){
        for(User user:users){
            user.displayNotifications();
        }
    }
    public void shutdown() {
        executor.shutdown(); // Stop accepting new tasks
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) { // Wait for tasks to finish
                System.out.println("Forcing shutdown...");
                executor.shutdownNow(); // Force stop if tasks take too long
            }
        } catch (InterruptedException e) {
            System.out.println("Shutdown interrupted. Forcing immediate shutdown...");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}

class Main {
    public static void main(String[] args) {
        DesiFlix desiFlix = new DesiFlix();
        List<User> users = DataInitialiser.generateUsers();
        List<Movie> movies = DataInitialiser.generateMovies();
        // Thread for adding users
        Thread userThread = new Thread(() -> {
            for (User user : users) {
                desiFlix.addUser(user);
            }
        });
        // Thread for adding movies
        Thread movieThread = new Thread(() -> {
            for (Movie movie : movies) {
                desiFlix.addMovie(movie);
            }
        });

        // Start both threads
        userThread.start();
        movieThread.start();

        // Wait for both threads to complete
        try {
            userThread.join();
            movieThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Shutdown the executor service
        desiFlix.shutdown();
        desiFlix.showUserNotifications();
        
    }
}
