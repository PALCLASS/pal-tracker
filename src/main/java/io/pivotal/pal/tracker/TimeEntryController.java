package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry);

    }
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long nonExistentTimeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(nonExistentTimeEntryId);
        if( timeEntry ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(timeEntry);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();
        return ResponseEntity.ok(timeEntries);
    }
    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if(updatedTimeEntry==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTimeEntry);
    }
    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }

}
