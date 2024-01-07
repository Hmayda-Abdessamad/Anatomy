package com.example.modelisation3dducorpshumain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.modelisation3dducorpshumain.beans.Note;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire. // hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
    @Query("SELECT n FROM Note n WHERE n.user.id = :userId AND n.objet.id = :object3dId")
    List<Note> findNotesByUserIdAndObject3dId(
            @Param("userId") int userId,
            @Param("object3dId") int object3dId
    );

}

