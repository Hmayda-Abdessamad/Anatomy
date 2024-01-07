package com.example.modelisation3dducorpshumain.repository;

import com.example.modelisation3dducorpshumain.beans.Category;
import com.example.modelisation3dducorpshumain.beans.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByNameContains(String name);

    @Query("SELECT c.name, COUNT(o) FROM Category c LEFT JOIN c.objects o GROUP BY c.id")
    List<Object[]> findCategoryNamesWithObjectCount();


}
