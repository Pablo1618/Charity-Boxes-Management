package pablo.charity_boxes.box;

import jakarta.persistence.*;
import lombok.*;
import pablo.charity_boxes.fundraising_event.FundraisingEvent;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "boxes")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "fundraising_event_id")
    private FundraisingEvent fundraisingEvent;

}
