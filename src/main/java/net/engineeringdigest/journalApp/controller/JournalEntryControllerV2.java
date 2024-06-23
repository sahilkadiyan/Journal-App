package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Autowired is used for dependency injection
// Application context is a way to implement IOC container
// it will contain special type of classes/ components
@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {


    /** Methods inside a controller class should be public so that they can be accessed
   and invoked by the Spring framework or external HTTP requests **/

    @GetMapping
    public List<JournalEntry> getAll(){
        return null;
    }
    /** Here request body is something like this hey spring please take the ddta from the request and turn it
     into a Java object so that i can use it in my code **/
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        return true;
    }
    // to get the details accoroding to the specific ID
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
            return null;

    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId){
        return null;

    }
    // uPdate req
    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id,@RequestBody JournalEntry myEntry){
            return null;
    }



}
