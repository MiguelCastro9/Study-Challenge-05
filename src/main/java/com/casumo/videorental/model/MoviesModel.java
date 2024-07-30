package com.casumo.videorental.model;

import com.casumo.videorental.enums.MovieTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Miguel Castro
 */
@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private MovieTypeEnum type;
    
    public MoviesModel(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.type = builder.type;
    }

    public static class Builder {

        private Long id;

        private String title;

        private MovieTypeEnum type;
        
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setType(MovieTypeEnum type) {
            this.type = type;
            return this;
        }        

        public MoviesModel build() {
            return new MoviesModel(this);
        }
    }
}
