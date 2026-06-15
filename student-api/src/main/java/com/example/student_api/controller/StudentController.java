package com.example.student_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api.entity.Student;
import com.example.student_api.security.StudentNotFoundException;

@RestController
@RequestMapping("/api")
public class StudentController {
    ArrayList<Student> list = new ArrayList<>();

    @PostMapping("/add")
    public String addStudent(@RequestBody Student std)
    {
      list.add(std);
      return "Student Added Sucessfully";
    }
    @GetMapping("/get")
    public List<Student> getStudents()
    {
        return list;
    }
    @GetMapping("/get/{id}")
       public ResponseEntity<Student> getStudentById(@PathVariable int id) throws StudentNotFoundException {
         Student student = list.stream()
        .filter(s -> s.getId() == id)
        .findFirst()
        .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
         return ResponseEntity.ok(student);
      } 

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id)
    {
          boolean remove =list.removeIf(std -> std.getId() == id);
          if(remove)
          {
            return ResponseEntity.ok("Student " + id + " Deleted");
          }
          else
          {
            return ResponseEntity.status(404).body( "Student " + id + " Not found");
          }  
    }
    @GetMapping("/search")
    public List<Student> searchStudent(@RequestParam String name)
    {
      return list.stream()
      .filter(s->s.getName()
      .toLowerCase()
      .contains(name.toLowerCase()))
      .toList();
    }
}
