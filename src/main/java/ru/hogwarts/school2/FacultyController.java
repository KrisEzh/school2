package ru.hogwarts.school2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        Faculty faculty = facultyService.findFaculty(id);
        if(faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false) String colour){
        if(name != null && !name.isBlank() && colour != null && !colour.isBlank()){
            return ResponseEntity.ok(facultyService.findByNameOrColourIgnoreCase(name, colour));
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty){
        return facultyService.createFaculty(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if(foundFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(path="sort")
    public List<Faculty> filterByColour(@RequestParam("colour") String colour){
        return facultyService.filterByColour(colour);
    }
    @GetMapping(path= "longWord")
    public Faculty findLongFaculty(){
       return facultyService.findLongFaculty();
    }

    @GetMapping(path="getNumber")
    public Integer getNumber(){
        return facultyService.getNumber();
    }
}

