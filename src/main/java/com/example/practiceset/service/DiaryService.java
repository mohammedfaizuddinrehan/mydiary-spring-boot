package com.example.practiceset.service;
import com.example.practiceset.entity.DiaryEntry;
import com.example.practiceset.entity.User;
import com.example.practiceset.repository.DiaryRepository;
import com.example.practiceset.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public DiaryService(DiaryRepository diaryRepository,
                        UserRepository userRepository){

        this.diaryRepository= diaryRepository;
        this.userRepository = userRepository;
    }

public List<DiaryEntry> getMyEntries(String email) {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("user not found "));
      return diaryRepository.findAllByUserOrderByCreatedAtDesc(user);
}
@Transactional
    public void createEntry(String email,String title ,String content) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("user not found"));
    DiaryEntry entry = new DiaryEntry();
    entry.setTitle(title);
    entry.setContent(content);
    entry.setUser(user);

    diaryRepository.save(entry);
}

    public DiaryEntry getMyEntry (Long id, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
        return diaryRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("dairy entery not found"));
    }

    @Transactional
    public void updateEntry (Long id,String email, String title, String content){
        DiaryEntry entry = getMyEntry(id,email);
        entry.setTitle(title);
        entry.setContent(content);
        diaryRepository.save(entry);
    }

    @Transactional
    public void deleteEntry(Long id ,String email){
        DiaryEntry entry = getMyEntry(id, email);
        diaryRepository.delete(entry);
    }

}