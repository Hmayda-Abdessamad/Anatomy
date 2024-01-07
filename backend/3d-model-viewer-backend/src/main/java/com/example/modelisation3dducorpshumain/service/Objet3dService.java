package com.example.modelisation3dducorpshumain.service;

import com.example.modelisation3dducorpshumain.beans.*;
import com.example.modelisation3dducorpshumain.repository.CategoryRepository;
import com.example.modelisation3dducorpshumain.repository.Object3dRepository;
import com.example.modelisation3dducorpshumain.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Objet3dService {
    private Object3dRepository object3dRepository;
    private CategoryRepository categoryRepository;
    private UploadDowloadservice uploadDowloadservice;
    private NoteService noteService;
    private TokenRepository tokenRepository;


    public Objet3d getById(int id){
        try{
            return object3dRepository.findById(id).get();
        }catch (HibernateException e){
            throw new IllegalStateException("categoryNotExist");
        }
    }
    public List<Objet3d> getAll(){
        return object3dRepository.findAll();
    }

    public String addCObjet3d(int idCat ,MultipartFile img , Objet3d objet3d , MultipartFile data){
        Optional<Category> category = categoryRepository.findById(idCat);
        if (category.isPresent()){
            try {
                String imgPath = uploadDowloadservice.uploadFile(img,"img").replaceFirst("/","-");;
                String dataPath = uploadDowloadservice.uploadFile(data,"glb").replaceFirst("/","-");
                objet3d.setImage(imgPath);
                objet3d.setData(dataPath);
                objet3d.setCategory(category.get());
                object3dRepository.save(objet3d);
                return "success";
            }catch (HibernateException e){
                return "failed";
            }
        }
        return "categoryNotExist";

    }
    public ResponseEntity<String> updateObjet3d(Objet3d objet3d){
        try {
                 Objet3d editedObject = object3dRepository.findById(objet3d.getId()).get();
                 editedObject.setName(objet3d.getName());
                 editedObject.setDescription(objet3d.getDescription());
                 editedObject.setCategory(objet3d.getCategory());
                 object3dRepository.save(editedObject);
                return ResponseEntity.ok("{\"message\": \"success\"}");


        }catch (HibernateException e){
            return ResponseEntity.ok("{\"message\": \"faild\"}");
        }
    }
    public String deleteObjet3d(int id){
        try {
            Optional<Objet3d> objet3d = object3dRepository.findById(id);
            if(objet3d.isPresent()){
                object3dRepository.delete(objet3d.get());
                return "seccuss";
            }
            return "objet3dNotExist";
        }catch (HibernateException e){
            return "faild";
        }
    }
    public String updateImageObejt3d(MultipartFile img , int id) {
        try {
            Optional<Objet3d> objet3d = object3dRepository.findById(id);
            if(objet3d.isPresent()){
                String imgPath = uploadDowloadservice.uploadFile(img,"img").replaceFirst("/","-");;
                objet3d.get().setImage(imgPath);
                object3dRepository.save(objet3d.get());
                return "seccuss";
            }
            return "objet3dNotExist";
        }catch (HibernateException e){
            return "faild";
        }
    }
    public String updateDataObejt3d(MultipartFile data , int id) {
        try {
            Optional<Objet3d> objet3d = object3dRepository.findById(id);
            if(objet3d.isPresent()){
                String dataPath = uploadDowloadservice.uploadFile(data,"glb").replaceFirst("/","-");;
                objet3d.get().setData(dataPath);
                objet3d.get().setTaille(data.getSize());
                object3dRepository.save(objet3d.get());
                return "seccuss";
            }
            return "objet3dNotExist";
        }catch (HibernateException e){
            return "faild";
        }
    }

    public List<Objet3d> searchByObjectName(String objectName) {
        return object3dRepository.findByNameContains(objectName);
    }
    public Map<String, List<? extends Object>> search(String partialName) {
        Map<String, List<? extends Object>> result = new HashMap<>();

        List<Objet3d> objects = searchByObjectName(partialName);
        List<Category> categories = categoryRepository.findByNameContains(partialName);

        result.put("objects", objects);
        result.put("categories", categories);

        return result;
    }

    public List<Note> getObjet3dNotes(int id, String header) {
        final String userToken = header.substring(7);
        Optional<Token> token = tokenRepository.findByToken(userToken);
        User user = token.get().getUser();
        return noteService.getNotesByObjectIdAndUserId(user.getId(),id);
    }

    public int getCatgoryIdByObjectID(int ObjectID){
        return object3dRepository.findCategoryIdByObjectId(ObjectID);
    }

    public List<Objet3d> getAllByCategoryId(int cat) {
        return object3dRepository.findAllByCategoryId(cat);
    }
}
