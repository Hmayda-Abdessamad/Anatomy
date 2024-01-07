package com.example.modelisation3dducorpshumain.controller;


import com.example.modelisation3dducorpshumain.beans.Objet3d;

import com.example.modelisation3dducorpshumain.service.Objet3dService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/objet3d")
@CrossOrigin("*")
public class Objet3dController {
    private Objet3dService objet3dService;

    public Objet3dController(Objet3dService objet3dService) {
        this.objet3dService = objet3dService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getObjet3d(@PathVariable("id") int id){
        try{
            return ResponseEntity.ok(objet3dService.getById(id));
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public ResponseEntity getAllObjet3d(){
        return ResponseEntity.ok(objet3dService.getAll());
    }
        @PostMapping("/add")
        public ResponseEntity<String> addObjet3d(@RequestParam("idCat") int idCat
                ,@RequestParam("img") MultipartFile img
                ,@RequestParam("name") String name,
                @RequestParam("data") MultipartFile data
                , @RequestParam("description") String description
                , @RequestParam("isAnimated") Boolean isAnimated
        ){
        Objet3d objet3d = Objet3d.builder()
                .name(name)
                .description(description)
                .taille(data.getSize())
                .isAnimated(isAnimated)
                .build();
        return ResponseEntity.ok(objet3dService.addCObjet3d(idCat,img,objet3d,data));
    }
    @PutMapping("/updateImg")
    public ResponseEntity<String> updateImageObjet3d(@RequestParam("img") MultipartFile img
            ,@RequestParam("id") int id){
        return ResponseEntity.ok(objet3dService.updateImageObejt3d(img,id));
    }
    @PutMapping("/updateData")
    public ResponseEntity<String> updateDataObejt3d(@RequestParam("data") MultipartFile data
            ,@RequestParam("id") int id){
        return ResponseEntity.ok(objet3dService.updateDataObejt3d(data,id));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateObjet3d(@RequestBody Objet3d objet3d){
        return objet3dService.updateObjet3d(objet3d);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteObjet3d(@PathVariable("id") int id){
        return ResponseEntity.ok(objet3dService.deleteObjet3d(id));
    }
    @GetMapping("/{id}/notes")
    public ResponseEntity<List> getObjet3dNotes(@PathVariable("id") int id,@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(objet3dService.getObjet3dNotes(id,token));}

        @GetMapping("/searchByObject")
    public ResponseEntity<List<Objet3d>> searchByObject(@RequestParam("name") String partialName) {
        List<Objet3d> result = objet3dService.searchByObjectName(partialName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Map<String, List<? extends Object>>> search(@RequestParam("name") String partialName) {
        Map<String, List<? extends Object>> response = objet3dService.search(partialName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/category")
    public ResponseEntity<Integer> findCategory(@RequestParam("ObjectId") int ObjectId) {
        return  ResponseEntity.ok(objet3dService.getCatgoryIdByObjectID(ObjectId));
    }

    @GetMapping("/category/{Cat}")
    public ResponseEntity findAllByCategory(@PathVariable("Cat") int cat){
        return ResponseEntity.ok(objet3dService.getAllByCategoryId(cat));
    }
}
