package pablo.charity_boxes.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pablo.charity_boxes.Currency;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    @Autowired
    private BoxService service;

    @PostMapping
    public ResponseEntity<String> createFundraisingEvent(@RequestBody Box event) {
        service.createBox(event);
        return ResponseEntity.ok("Box created successfully");
    }

    @GetMapping
    public List<String> getAllBoxes() {
        return service.getAllBoxes();
    }

    @PutMapping("/assign")
    public ResponseEntity<String> assignBoxToFundraisingEvent(@RequestParam String boxName, @RequestParam String eventName) {
        service.assignBoxToFundraisingEvent(boxName, eventName);
        return ResponseEntity.ok("Box assigned successfully");
    }

    @PutMapping("/add-money")
    public ResponseEntity<String> addMoneyToBox(@RequestParam String boxName, @RequestParam BigDecimal amount, @RequestParam Currency currency) {
        service.addMoneyToBox(boxName, amount, currency);
        return ResponseEntity.ok("Money added successfully");
    }

    @PutMapping("/empty")
    public ResponseEntity<String> emptyBox(@RequestParam String boxName) {
        service.emptyBox(boxName);
        return ResponseEntity.ok("Box emptied successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeBox(@RequestParam String boxName) {
        service.removeBox(boxName);
        return ResponseEntity.ok("Box removed successfully");
    }


}

