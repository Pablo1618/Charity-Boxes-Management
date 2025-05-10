package pablo.charity_boxes.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

