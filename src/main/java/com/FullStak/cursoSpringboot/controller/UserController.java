package com.FullStak.cursoSpringboot.controller;

import com.FullStak.cursoSpringboot.entity.User;
import com.FullStak.cursoSpringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //Create a new user
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    //Leer usuarios
    @GetMapping("/{id}")
    public ResponseEntity<?> read (@PathVariable(value = "id") Long UserId){
        Optional<User> optionalUser = userService.findByid(UserId);

        if(!optionalUser.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optionalUser);
    }

    //Actualiza un usuario
    @PutMapping("/{id}")
    public  ResponseEntity<?> Update(@RequestBody User userDetail,@PathVariable(value = "id") Long UserId){
        Optional<User> user = userService.findByid(UserId);

        if(!user.isPresent())
            return ResponseEntity.notFound().build();

        user.get().setName(userDetail.getName());
        user.get().setSurname(userDetail.getSurname());
        user.get().setEmail(userDetail.getEmail());
        user.get().setEnable(userDetail.getEnable());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
    }

    //Borrar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable(value = "id") long userId){
        if(!userService.findByid(userId).isPresent())
            return ResponseEntity.notFound().build();
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    //Leer todos los usuarios
    @GetMapping
    public List<User> readAll (){
        List<User> users = StreamSupport
                .stream(userService.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return users;
    }
}
