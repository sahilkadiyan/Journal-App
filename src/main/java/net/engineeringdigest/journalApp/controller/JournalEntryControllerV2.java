package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }
    /** Here request body is something like this hey spring please take the ddta from the request and turn it
     into a Java object so that i can use it in my code **/

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }
    // to get the details accoroding to the specific ID
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
            return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;

    }
    // uPdate req
    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){

        JournalEntry oldId=journalEntryService.findById(id).orElse(null);
        if(oldId!=null){
            oldId.setContent(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : oldId.getTitle());
            oldId.setTitle(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldId.getContent());

        }
        journalEntryService.saveEntry(oldId);
        return oldId;
    }



}
