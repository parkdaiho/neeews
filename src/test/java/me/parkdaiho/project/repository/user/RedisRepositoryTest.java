package me.parkdaiho.project.repository.user;

import me.parkdaiho.project.domain.user.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisRepositoryTest {

    @Autowired
    RedisRepository redisRepository;

    @Test
    public void redisTest() {
        // given
        Person person = new Person("PARK DAIHO");

        // when
        redisRepository.save(person);
        Person savedPerson = redisRepository.findById(person.getId())
                .orElseThrow(() -> new IllegalArgumentException("data is not saved."));

        // given
        assertThat(savedPerson.getName()).isEqualTo(person.getName());
    }
}