package com.example.modelisation3dducorpshumain.controller;

import com.example.modelisation3dducorpshumain.beans.Category;
import com.example.modelisation3dducorpshumain.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")

public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}")
    public ResponseEntity getCategory(@PathVariable("id") int id){
        try{
            return ResponseEntity.ok(categoryService.getById(id));
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public ResponseEntity getAllCategorys(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestParam("img") MultipartFile img,@RequestParam("name") String name,@RequestParam("desc") String desc
    ){
        Category category = Category.builder()
                .name(name)
                .description(desc)
                .build();
        return ResponseEntity.ok(categoryService.addCategory(img,category));
    }


    @PutMapping("/updateImg")
    public ResponseEntity<String> updateImageCategory(@RequestParam("img") MultipartFile img
                                                        ,@RequestParam("id") int id){
        return categoryService.updateImageCategory(img,id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id){
        return categoryService.deleteCategory(id);
    }
    @GetMapping("/{id}/objects")
    public ResponseEntity getAllObjectsByCategory(@PathVariable("id") int id){
        return ResponseEntity.ok(categoryService.getAllObjs(id));
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Category>> searchByCategory(@RequestParam("name") String name) {
        List<Category> result = categoryService.searchObjectsByCategory(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/bar")
    public ResponseEntity<List<Object[]>> CountObjectByCategory(){
        return ResponseEntity.ok(categoryService.CountOrganInEachCategory());
    }
    @GetMapping("/objs/{idCat}")
    public ResponseEntity CountObjectByCategoryId(@PathVariable("idCat") int idCat){
        Map<String,Integer> map = new HashMap<>();
        map.put("totale",categoryService.countOrganByCategory(idCat));
        return ResponseEntity.ok(map);
    }
}
