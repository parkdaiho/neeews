package me.parkdaiho.project.repository.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void testString() {
        // given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        String key = "string-key";
        String value = "string-value";

        // when
        valueOperations.set(key, value);

        // then
        String savedValue = valueOperations.get(key);

        assertThat(savedValue).isEqualTo(value);
    }

    @Test
    public void testSet() {
        // given
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();

        String key = "set-key";
        String[] members = {"s", "e", "t", "-", "k", "e", "y"};

        // when
        setOperations.add(key, members);

        // then
        Set<String> savedMembers = setOperations.members(key);
        Long size = setOperations.size(key);

        assertThat(savedMembers).containsOnly(members);
        assertThat(size).isEqualTo(new HashSet<>(Arrays.asList(members)).size());
    }

    @Test
    public void hashTest() {
        // given
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String key = "hash-key";

        // when
        hashOperations.put(key, "PARK", "DAIHO");

        // then
        Object value = hashOperations.get(key, "PARK");
        assertThat(value).isEqualTo("DAIHO");

        Map<Object, Object> entries = hashOperations.entries(key);
        assertThat(entries.keySet()).containsOnly("PARK");
        assertThat(entries.values()).containsOnly("DAIHO");

        Long size = hashOperations.size(key);
        assertThat(size).isEqualTo(entries.size());
    }
}
