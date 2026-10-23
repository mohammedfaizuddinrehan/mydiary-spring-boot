package com.example.practiceset.repository;

import com.example.practiceset.entity.DiaryEntry;
import com.example.practiceset.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository  extends JpaRepository<DiaryEntry,Long> {
List<DiaryEntry>findAllByUserOrderByCreatedAtDesc(User user);
Optional<DiaryEntry> findByIdAndUser(Long id , User user);
}
