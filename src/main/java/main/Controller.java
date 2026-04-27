package main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/home")
public class Controller {
    @GetMapping
    public String home(){
        return "home";
    }
}
