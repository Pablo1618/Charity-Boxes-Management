package pablo.charity_boxes.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoxService {

    @Autowired
    private BoxRepository repository;

    public Box createBox(Box event) {
        return repository.save(event);
    }

    public List<String> getAllBoxes() {
        List<Box> events = repository.findAll();
        System.out.println("All events: " + events);
        return events.stream().map(Box::getName).collect(Collectors.toList());
    }
}

