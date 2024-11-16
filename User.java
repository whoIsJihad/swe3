import java.util.ArrayList;
import java.util.List;

public class User {
    List<String> genres;
    String name;
    public User(String name){
        this.name=name;
        this.genres=new ArrayList<>();
    }
    public void addGenre(String genre ){
        genres.add(genre);
    }
    public void remove(String genre){
        genres.remove(genre);
    }
    public synchronized void update(Movie movie){

        System.out.println("Hey " +this.name +" , a new movie is available in the genre: "+movie.genre);
        System.out.println("The name of the movie is : "+movie.name);
    }
}
