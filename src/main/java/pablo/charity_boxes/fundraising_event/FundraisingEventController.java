package pablo.charity_boxes.fundraising_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fundraising-events")
public class FundraisingEventController {

    @Autowired
    private FundraisingEventService service;

    @PostMapping
    public FundraisingEvent createFundraisingEvent(@RequestBody FundraisingEvent event) {
        return service.createFundraisingEvent(event);
    }

    @GetMapping
    public List<String> getAllFundraisingEvents() {
        return service.getAllFundraisingEvents();
    }

    @GetMapping("/report")
    public List<String> getFundraisingReport() {
        return service.getFundraisingReport();
    }


}

