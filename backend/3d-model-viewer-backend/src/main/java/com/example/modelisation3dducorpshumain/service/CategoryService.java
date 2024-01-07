package com.example.modelisation3dducorpshumain.service;

import com.example.modelisation3dducorpshumain.beans.Category;
import com.example.modelisation3dducorpshumain.beans.Objet3d;
import com.example.modelisation3dducorpshumain.repository.CategoryRepository;
import com.example.modelisation3dducorpshumain.repository.Object3dRepository;
import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;
    private UploadDowloadservice uploadDowloadservice;
    private Object3dRepository object3dRepository;

    public Category getById(int id){
        try{
            return categoryRepository.findById(id).get();
        }catch (HibernateException e){
            throw new IllegalStateException("categoryNotExist");
        }
    }
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    public List<Objet3d> getAllObjs(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return object3dRepository.findAllByCategoryId(id);
        }
        return null;
    }

    public String addCategory(MultipartFile img , Category category){
        try {
            String imgPath = uploadDowloadservice.uploadFile(img,"img");
            imgPath = imgPath.replaceFirst("/", "-");
            category.setImage(imgPath);
            categoryRepository.save(category);
            return "seccuss";
        }catch (HibernateException e){
            return "faild";
        }
    }
    public ResponseEntity<String> updateCategory(Category category){
        try {
            if(categoryRepository.findById(category.getId()).isPresent()){
                Category oldCategorie =categoryRepository.findById(category.getId()).get();
                category.setImage(oldCategorie.getImage());
                categoryRepository.save(category);
                return ResponseEntity.ok("{\"message\": \"seccuss\"}");

            }
            return ResponseEntity.ok("{\"message\": \"categoryNotExist\"}");

        }catch (HibernateException e){
            return ResponseEntity.ok("{\"message\": \"faild\"}");

        }
    }
    public ResponseEntity<String> deleteCategory(int id){

        try {
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent()){
                categoryRepository.delete(category.get());
                return ResponseEntity.ok("{\"message\": \"success\"}");
            }else{
                return ResponseEntity.ok("{\"message\": \"categoryNotExist\"}");
            }

        }catch (HibernateException e){
            return ResponseEntity.ok("{\"message\": \"faild\"}");
        }
        }

    public ResponseEntity<String> updateImageCategory(MultipartFile img , int id) {
        try {
            Optional<Category> categoryById = categoryRepository.findById(id);
            if(categoryById.isPresent()){
                String imgPath = uploadDowloadservice.uploadFile(img,"img");
                imgPath = imgPath.replaceFirst("/", "-");
                categoryById.get().setImage(imgPath);
                categoryRepository.save(categoryById.get());
                return ResponseEntity.ok("{\"message\": \"success\"}");
            }
            return ResponseEntity.ok("{\"message\": \"categoryNotExist\"}");
        }catch (HibernateException e){
            return ResponseEntity.ok("{\"message\": \"faild\"}");
        }
    }
    public List<Category> searchObjectsByCategory(String name) {
        return categoryRepository.findByNameContains(name);
    }

    public List<Object[]> CountOrganInEachCategory(){
        return  categoryRepository.findCategoryNamesWithObjectCount();
    }

    public int countOrganByCategory(int idCat){
        return  object3dRepository.getNumberOfObjects(idCat);
    }


}
