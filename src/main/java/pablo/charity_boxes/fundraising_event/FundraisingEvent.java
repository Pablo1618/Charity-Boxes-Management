package pablo.charity_boxes.fundraising_event;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

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

}
