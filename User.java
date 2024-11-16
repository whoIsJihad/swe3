import java.util.ArrayList;
import java.util.List;

public class User {
    List<String> genres;
    public User(){
        this.genres=new ArrayList<>();
    }
    public void addGenre(String genre ){
        genres.add(genre);
    }
    public void remove(String genre){
        genres.remove(genre);
    }
    public void update(Movie movie){
        System.out.println("Hey, a new movie is available in the genre: "+movie.genre);
        System.out.println("The name of the movie is : "+movie.name);
    }
}
