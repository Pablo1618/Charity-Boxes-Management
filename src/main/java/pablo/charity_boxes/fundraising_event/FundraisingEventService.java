package pablo.charity_boxes.fundraising_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pablo.charity_boxes.Currency;
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
            Currency eventCurrency = event.getCurrency();
            String amount = event.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            return event.getName() + " " + amount + " " + eventCurrency;
        }).collect(Collectors.toList());
    }

}

