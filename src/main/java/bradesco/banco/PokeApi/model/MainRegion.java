package bradesco.banco.PokeApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MainRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    @Override
    public String toString() {
        return "MainRegion [name=" + name + "]";
    }
}
