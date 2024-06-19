package net.engineeringdigest.journalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Car {

    @Autowired
    private Dog dog;
    // field injection here we inject the dog object using dependency injection

    @GetMapping("/ok")
    public String ok(){
        return dog.fun();
    }
}
