package csapat1.codingmentor.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Director extends Person implements Serializable {
    
    @Column(name="ORIGINAL_NAME")
    private String originalName;
    
    @ManyToMany(mappedBy = "directors")
    //private Set<Movie> movies;
    private List<Movie> movies;

    public Director() {
        //it is bean
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

//    public Set<Movie> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(Set<Movie> movies) {
//        this.movies = movies;
//    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }  
}
