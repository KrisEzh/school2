package ru.hogwarts.school2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository){
        this.facultyRepository=facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty){
        logger.debug("Факультет добавлен");
        return facultyRepository.save(faculty);
    }
    public Faculty findFaculty(long id){
        logger.debug("Факультет найден");
        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty){
        logger.debug("Факультет добавлен");
        return facultyRepository.save(faculty);
    }
    public Faculty deleteFaculty(long id){
        logger.debug("Факультет удален");
        return facultyRepository.getById(id);
    }
    public Collection<Faculty> getAllFaculties(){
        logger.debug("Получены все факультеты");
        return facultyRepository.findAll();
    }
    public List<Faculty> filterByColour(String colour){
        logger.debug("Факультеты по цвету получены");
        return getAllFaculties()
                .stream()
                .filter(faculty -> faculty.getColour().equals(colour))
                .collect(Collectors.toList());
    }
    public Collection<Faculty> findByNameOrColourIgnoreCase(String name, String colour){
        logger.debug("Факультеты по названию и цвету получены");
        return facultyRepository.findByNameOrColourIgnoreCase(name, colour);
    }
}
