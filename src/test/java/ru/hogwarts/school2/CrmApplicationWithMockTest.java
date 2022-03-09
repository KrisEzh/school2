package ru.hogwarts.school2;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FacultyController.class)
public class CrmApplicationWithMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    FacultyController facultyController;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void saveFacultyTest() throws Exception {
        final long id = 1;
        final String name = "Griffindor";
        final String colour = "red";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColour(colour);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.colour").value(colour));

    }

    @Test
    public void findFacultyTest() throws Exception{
        final long id = 1;
        final String name = "Griffindor";
        final String colour = "red";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColour(colour);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("colour", colour);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.colour").value(colour));


    }

    @Test
    public void deleteFacultyTest() throws Exception {
        long facultyId = 1L;
        doNothing().when(facultyRepository).deleteById(any());
        mockMvc.perform(MockMvcRequestBuilders.delete("/faculty/" + facultyId))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultyByColorOrNameIgnoreCaseTest() throws Exception{
        Long id1 = 1L;
        String name1 = "Griffindor";
        String color1 = "red";
        String color1IgnoreCase = "rEd";

        Long id2 = 2L;
        String name2 = "Hufflepuf";
        String name2IgnoreCase = "HuFFlepuf";
        String color2 = "blue";

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColour(color1);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColour(color2);

        when(facultyRepository.findByNameOrColourIgnoreCase(name2IgnoreCase, color1IgnoreCase)).thenReturn(Set.of(faculty1, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("colour",color1IgnoreCase)
                        .queryParam("name", name2IgnoreCase)
                        .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1,faculty2))));
    }

    @Test
    public void updateFacultyTest() throws Exception{
        Long id = 1L;
        String oldName = "Griffindor";
        String oldColor = "Blue";

        String newName =  "Hufflepuf";
        String newColor = "Red";

        JSONObject facultyObj = new JSONObject();
        facultyObj.put("id",id);
        facultyObj.put("name", newName);
        facultyObj.put("color", newColor);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(oldName);
        faculty.setColour(oldColor);

        Faculty upFaculty = new Faculty();
        upFaculty.setId(id);
        upFaculty.setName(newName);
        upFaculty.setColour(newColor);

        when(facultyRepository.findById(id)).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn((upFaculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObj.toString())
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName))
                .andExpect(jsonPath("$.colour").value(newColor));


    }

}
