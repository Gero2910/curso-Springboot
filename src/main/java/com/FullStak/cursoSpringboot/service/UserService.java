package com.FullStak.cursoSpringboot.service;

import com.FullStak.cursoSpringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface UserService {

    public Iterable<User> findAll();
    public Page<User> findAll(Pageable pageable);
    public Optional<User> findByid(long id);
    public User save(User user);
    public void deleteById(long id);
}
