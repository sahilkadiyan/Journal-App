package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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
    public ResponseEntity<?> getAll(){
        List<JournalEntry>all=journalEntryService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** Here request body is something like this hey spring please take the ddta from the request and turn it
     into a Java object so that i can use it in my code **/

    @PostMapping
    public ResponseEntity<JournalEntry>  createEntry(@RequestBody JournalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // to get the details according to the specific ID
    // in this we have used ResponseEntity
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // uPdate req
    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){

        JournalEntry oldId=journalEntryService.findById(id).orElse(null);
        if(oldId!=null){
            oldId.setContent(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : oldId.getTitle());
            oldId.setTitle(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldId.getContent());
            journalEntryService.saveEntry(oldId);
            return new ResponseEntity<>(oldId,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
