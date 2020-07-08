package com.example.sklepZKwiatami.repositories;

import com.example.sklepZKwiatami.entity.History;
import com.example.sklepZKwiatami.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {
    List<History> findByUser(User user);
    Optional<History> findByLocalDateTime(LocalDateTime localDateTime);
    List<History> findByLocalDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
