package pablo.charity_boxes.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    @Autowired
    private BoxService service;

    @PostMapping
    public Box createFundraisingEvent(@RequestBody Box event) {
        return service.createBox(event);
    }

    @GetMapping
    public List<String> getAllBoxes() {
        return service.getAllBoxes();
    }

    @PutMapping("/assign")
    public Box assignBoxToFundraisingEvent(@RequestParam String boxName, @RequestParam String eventName) {
        return service.assignBoxToFundraisingEvent(boxName, eventName);
    }

    @PutMapping("/add-money")
    public Box addMoneyToBox(@RequestParam String boxName, @RequestParam BigDecimal amount) {
        return service.addMoneyToBox(boxName, amount);
    }


}

