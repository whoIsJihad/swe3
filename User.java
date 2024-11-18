import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    List<String> genres;
    String name;
    List<String>notifications;
    public User(String name){
        this.name=name;
        this.notifications = Collections.synchronizedList(new ArrayList<>());
        this.genres = Collections.synchronizedList(new ArrayList<>());
    }
    public void addGenre(String genre ){
        genres.add(genre);
    }
    public void remove(String genre){
        genres.remove(genre);
    }
    public synchronized void update(Movie movie){
        notifications.add("Hey " +this.name +" , a new movie is available in the genre: "+movie.genre+".The name of the movie is : "+movie.name);
    }
    public void displayNotifications(){
        for(String notification:notifications){
            System.out.println(notification);
        }
    }
}
