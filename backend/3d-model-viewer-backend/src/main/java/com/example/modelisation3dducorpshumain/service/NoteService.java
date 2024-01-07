package com.example.modelisation3dducorpshumain.service;


import com.example.modelisation3dducorpshumain.repository.NoteRepository;


import org.springframework.stereotype.Service;
import com.example.modelisation3dducorpshumain.beans.Note;
import java.util.List;

@Service

public class NoteService {
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    private  NoteRepository noteRepository;

//

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Note addNote(Note note) {
            return noteRepository.save(note);
    }

    public Note updateNote(Note updatedNote) {
        return noteRepository.save(updatedNote);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
    public List<Note> getNotesByObjectIdAndUserId(int userId,int objectId){
        return noteRepository.findNotesByUserIdAndObject3dId(userId,objectId);
    }
}

