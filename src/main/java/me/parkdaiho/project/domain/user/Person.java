package me.parkdaiho.project.domain.user;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "person", timeToLive = 1800)
public class Person {

    @Id
    private String id;

    private String name;

    public Person(String name) {
        this.name = name;
    }
}
