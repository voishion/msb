package com.meishubao.jsondoc.repository;

import com.meishubao.jsondoc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserRepository {

    public User findOne(Long id) {
        return User.builder().id(id).username("HelloWorld").build();
    }

    public List<User> findAll() {
        return Stream.iterate(0, (x) -> x + 1).limit(10).map(x -> findOne(x.longValue())).collect(Collectors.toList());
    }
}
