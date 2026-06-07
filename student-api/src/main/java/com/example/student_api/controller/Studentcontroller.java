package com.example.student_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api.entity.Student;

@RestController
@RequestMapping("/api")
public class Studentcontroller {
    ArrayList<Student> list = new ArrayList<>();

    @PostMapping("/add")
    public String addstd(@RequestBody Student std)
    {
      list.add(std);
      return "Student Added Sucessfully";
    }
    @GetMapping("/get")
    public List<Student> getstd()
    {
        return list;
    }
    @DeleteMapping("/delete/{id}")
    public String deletestd(@PathVariable int id)
    {
          boolean remove =list.removeIf(std -> std.getId() == id);
          if(remove)
          {
            return "Student " + id + " Deleted";
          }
          else
          {
            return "Student " + id + " Not found";
          }  
    }
}
