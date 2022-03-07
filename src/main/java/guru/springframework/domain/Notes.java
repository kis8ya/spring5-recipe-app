package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
