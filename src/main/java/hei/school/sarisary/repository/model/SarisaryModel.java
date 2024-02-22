package hei.school.sarisary.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "sarisary")
@Entity
public class SarisaryModel {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
