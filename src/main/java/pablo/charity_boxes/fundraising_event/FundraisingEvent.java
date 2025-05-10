package pablo.charity_boxes.fundraising_event;

import jakarta.persistence.*;
import lombok.*;
import pablo.charity_boxes.box.Box;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "fundraisingEvents")
public class FundraisingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private BigDecimal amount;
    private String currency;

//    @OneToMany(mappedBy = "fundraisingEvent", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Box> boxes = new ArrayList<>();

}
