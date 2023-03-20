package kz.evo.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hello")
@SpringBootApplication
@RestController
public class SimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }

    @GetMapping("/{firstName}")
    public String getMethod(@PathVariable String firstName, @RequestParam String lastName) {
        return String.format("Hello from get, %s %s", firstName, lastName);
    }

    @PostMapping
    public String postMethod(@RequestBody SimpleRequest simpleRequest) {
        return String.format("Hello from post, %s %s", simpleRequest.getFirstName(), simpleRequest.getLastName());
    }

}
