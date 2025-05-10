package pablo.charity_boxes.fundraising_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundraisingEventService {

    @Autowired
    private FundraisingEventRepository repository;

    public FundraisingEvent createFundraisingEvent(FundraisingEvent event) {
        return repository.save(event);
    }

    public List<String> getAllFundraisingEvents() {
        List<FundraisingEvent> events = repository.findAll();
        System.out.println("All events: " + events);
        return events.stream().map(FundraisingEvent::getName).collect(Collectors.toList());
    }

    public List<String> getFundraisingReport() {
        return repository.findAll().stream().map(event -> {
            BigDecimal total = event.getBoxes().stream()
                    .map(box -> box.getAmount() != null ? box.getAmount() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return event.getName() + " " + total;
        }).collect(Collectors.toList());
    }

}

