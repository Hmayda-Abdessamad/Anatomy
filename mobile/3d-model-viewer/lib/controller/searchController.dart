import 'package:flutter/material.dart';
import '../../network/repository/searchRepository.dart';
import '../models/category.dart';
import '../models/object3d.dart';
import '../service/serviceLocator.dart';

class SearchController {
  final searchRepository = getIt.get<SearchRepository>();

  // --------------Search controller-----------
  final searchController = TextEditingController();
  // -------------- Methods ---------------

  Future<List> getAllResult() async {
      final allLabs = await searchRepository.getAllSearchResult(searchController.text);
      return allLabs;
    
  }
  Future<List<Category>> getAllCategorys() async {
      final allLabs = await searchRepository.getSearchParCategoryResult(searchController.text);
      return allLabs;
    
  }

  Future<List<Object3d>> getAllObject3d() async {
    final allLabs =await searchRepository.getSearchParObject3dResult(searchController.text);
    return allLabs;
  }

  
}
