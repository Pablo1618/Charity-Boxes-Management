package pablo.charity_boxes.box;

import jakarta.persistence.*;
import lombok.*;
import pablo.charity_boxes.common.Currency;
import pablo.charity_boxes.fundraising_event.FundraisingEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "boxes")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "fundraising_event_id")
    private FundraisingEvent fundraisingEvent;

    @ElementCollection
    @CollectionTable(name = "box_amounts", joinColumns = @JoinColumn(name = "box_id"))
    @MapKeyColumn(name = "currency")
    @Column(name = "amount")
    private Map<Currency, BigDecimal> amounts = new HashMap<>();

}
