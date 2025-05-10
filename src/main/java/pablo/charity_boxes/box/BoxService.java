package pablo.charity_boxes.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pablo.charity_boxes.fundraising_event.FundraisingEvent;
import pablo.charity_boxes.fundraising_event.FundraisingEventRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class BoxService {

    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private FundraisingEventRepository fundraisingEventRepository;

    public Box createBox(Box event) {
        return boxRepository.save(event);
    }

    public List<String> getAllBoxes() {
        List<Box> events = boxRepository.findAll();
        System.out.println("All events: " + events);
        return events.stream().map(Box::getName).collect(Collectors.toList());
    }

    public Box assignBoxToFundraisingEvent(String boxName, String eventName) {

        Optional<Box> boxOptional = boxRepository.findByName(boxName);
        if (boxOptional.isEmpty()) {
            throw new RuntimeException("Box not found");
        }
        Box box = boxOptional.get();

        Optional<FundraisingEvent> fundraisingEventOptional = fundraisingEventRepository.findByName(eventName);
        if (fundraisingEventOptional.isEmpty()) {
            throw new RuntimeException("FundraisingEvent not found");
        }
        FundraisingEvent fundraisingEvent = fundraisingEventOptional.get();

        box.setFundraisingEvent(fundraisingEvent);
        return boxRepository.save(box);
    }

    public Box addMoneyToBox(String boxName, BigDecimal amount) {
        Optional<Box> boxOptional = boxRepository.findByName(boxName);
        if (boxOptional.isEmpty()) {
            throw new RuntimeException("Box not found");
        }

        Box box = boxOptional.get();
        BigDecimal currentAmount = box.getAmount() != null ? box.getAmount() : BigDecimal.ZERO;
        box.setAmount(currentAmount.add(amount));

        return boxRepository.save(box);
    }
}

