package com.example.modelisation3dducorpshumain.repository;

import com.example.modelisation3dducorpshumain.beans.Note;
import com.example.modelisation3dducorpshumain.beans.Objet3d;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface Object3dRepository extends JpaRepository<Objet3d,Integer> {

    List<Objet3d> findAllByCategoryId(int id);
    List<Objet3d> findByNameContains(String name);
    @Query("SELECT o.category.id FROM Objet3d o WHERE o.id = :objectId")
    Integer findCategoryIdByObjectId(@Param("objectId") int objectId);

    @Query("SELECT COUNT(o.id) FROM Objet3d o WHERE o.category.id = :categoryId")
    Integer getNumberOfObjects(@Param("categoryId") int categoryId);


}
