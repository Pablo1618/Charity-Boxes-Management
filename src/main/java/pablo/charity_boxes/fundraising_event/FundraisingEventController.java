package pablo.charity_boxes.fundraising_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fundraising-events")
public class FundraisingEventController {

    @Autowired
    private FundraisingEventService service;

    @PostMapping
    public ResponseEntity<String> createFundraisingEvent(@RequestBody FundraisingEvent event) {
        service.createFundraisingEvent(event);
        return ResponseEntity.ok("Fundraising event created successfully");
    }

    @GetMapping("/report")
    public List<String> getFundraisingReport() {
        return service.getFundraisingReport();
    }


}

