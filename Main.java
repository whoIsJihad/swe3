import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String outputFilePath = "notifications.txt";
        PrintStream consoleOut = System.out;
        // Redirect System.out to a file
        try {
            PrintStream fileOut = new PrintStream(new File(outputFilePath));
            System.setOut(fileOut);
        } catch (FileNotFoundException e) {
            System.err.println("Error redirecting output to file: " + e.getMessage());
            return;
        }

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
        System.setOut(consoleOut);
        System.out.println("Notifications have been written to " + outputFilePath);
    }
}
