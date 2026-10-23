package com.example.practiceset.controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.example.practiceset.service.DiaryService;
import com.example.practiceset.entity.DiaryEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService){
        this.diaryService= diaryService;
    }
    @GetMapping("/write-diary")
    public String showWritediary(){
        return "write-diary";
    }

    @GetMapping("/diary")
    public String showDiary(Authentication authentication , Model model){
        String email = authentication.getName();
        List<DiaryEntry> entries = diaryService.getMyEntries(email);
        model.addAttribute("entries",entries);
        return "diary";
    }
    @PostMapping("/diary/new")
    public String createEntry(
            @RequestParam String title,
            @RequestParam String content,
            Authentication authentication,
            RedirectAttributes redirectAttributes){

        String email = authentication.getName();
        diaryService.createEntry(email,title,content);

        redirectAttributes.addFlashAttribute(
                "message","diary entry saved succesfully re");
        return "redirect:/diary";
    }
    @GetMapping("/diary/edit/{id}")
    public String showEditPage(
            @org.springframework.web.bind.annotation.PathVariable Long id,
            Authentication authentication,
            Model model){
        String email = authentication.getName();
        DiaryEntry entry = diaryService.getMyEntry(id,email);
        model.addAttribute("entry",entry);
        return "edit-diary";
    }
    @PostMapping("/diary/edit/{id}")
    public String updateEntry(
            @org.springframework.web.bind.annotation.PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            Authentication authentication,
            RedirectAttributes redirectAttributes){

        String email = authentication.getName();
        diaryService.updateEntry(id,email,title,content);
        redirectAttributes.addFlashAttribute(
                "message","Diary entry updated succesfully"
        );
        return "redirect:/diary";
    }
    @PostMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/login")
    public String showLoginnPage() {
        return "login";
    }




}
