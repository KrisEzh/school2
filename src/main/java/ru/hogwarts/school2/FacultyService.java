package ru.hogwarts.school2;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository){
        this.facultyRepository=facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id){
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public Faculty deleteFaculty(long id){
        return facultyRepository.getById(id);
    }
    public Collection<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
    public List<Faculty> filterByColour(String colour){
        return getAllFaculties()
                .stream()
                .filter(faculty -> faculty.getColour().equals(colour))
                .collect(Collectors.toList());
    }
    public Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour){
        return facultyRepository.findByNameOrColourIgnoreCase(name, colour);
    }
}
