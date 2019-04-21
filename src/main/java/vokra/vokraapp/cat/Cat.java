package vokra.vokraapp.cat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = "uuid")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cat
{

    @JsonIgnore
    @Column(nullable = false)
    final UUID uuid = UUID.randomUUID();

    @Id
    String id;
    String name;
    String age;
    String gender;
    String colour;
    String foster;
    String city;
    String notes;
    String createdAt; /* TODO - convert to LocalDateTime */
//    String hairLength;
//    Cat buddy;

}
