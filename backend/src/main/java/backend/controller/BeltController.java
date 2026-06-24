package backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/belts")
public class BeltController {

    @GetMapping
    public List<String> getAllBelts() {

        return List.of(
                "White Belt",
                "Yellow Belt",
                "Orange Belt",
                "Green Belt",
                "Blue Belt",
                "Brown Belt",
                "Black Belt"
        );
    }
}