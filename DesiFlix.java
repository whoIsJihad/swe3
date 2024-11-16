import java.util.ArrayList;
import java.util.List;

public class DesiFlix {
    List<Movie> movies;
    List<User> users;

    public DesiFlix(){
        this.movies=new ArrayList<>();
        this.users=new ArrayList<>();
    }

    public void addMovie(Movie movie){
        movies.add(movie);
        this.notify(movie);
    }

    public void notify(Movie movie){
        
        for(User user:users){
            if(user.genres.contains(movie.genre)){
                user.update(movie);
            }
        }
    }
    
}
