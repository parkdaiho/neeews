package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.domain.user.Person;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<Person, String> {
}
