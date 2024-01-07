package com.example.modelisation3dducorpshumain.controller;


import org.springframework.web.bind.annotation.*;
import com.example.modelisation3dducorpshumain.beans.Note;
import com.example.modelisation3dducorpshumain.service.NoteService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@CrossOrigin("*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/all")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping("/add")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @PutMapping("/update/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        updatedNote.setId(id);
        return noteService.updateNote(updatedNote);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}

