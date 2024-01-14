import 'package:flutter/services.dart';
import 'package:my_app/models/object3d.dart';

import 'package:path/path.dart';
import 'dart:io';
import 'dart:math';

import 'package:sqflite/sqflite.dart';

import 'package:path_provider/path_provider.dart';

class DatabaseService {
  static const _databaseName = "objects3d";
  static const _databaseVersion = 2;
  DatabaseService._privateConstructor();
  static final DatabaseService instance = DatabaseService._privateConstructor();

  late Database _db;
  Future<Database> get database async {
    if (_db != null) return _db;
    // lazily instantiate the db the first time it is accessed
    _db = await init();
    return _db;
  }

  Database get db => _db;
  // this opens the database (and creates it if it doesn't exist)
  Future<Database> init() async {
    final documentsDirectory = await getApplicationDocumentsDirectory();
    final path = join(documentsDirectory.path, _databaseName);

    _db = await openDatabase(
      path,
      version: _databaseVersion,
      onCreate: _onCreate, // Call the onUpgrade method
    );
    return _db;
  }

  // SQL code to create the database table
  Future _onCreate(Database db, int version) async {
    await db.execute('''
          CREATE TABLE objects (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            image TEXT NOT NULL,
            description TEXT NOT NULL,
            data TEXT NOT NULL,
            isAnimated INTEGER NOT NULL
          );
          ''');
    print("creating tables!!!!!!!!");
  }

  // user service !!

  Future<int> insertObject(Map<String, dynamic> row) async {
    await init();
    row["isAnimated"] == "true" ? row["isAnimated"] = 1 : row["isAnimated"] = 0;
    
    return await _db.insert("objects", row);
  }

  Future<List<Map<String, dynamic>>> getUserById(int id) async {
    await init();
    return _db.query('user', orderBy: "id", where: 'id = ?', whereArgs: [id]);
  }

  Future<List<Object3d>> getAllSavedObjects() async {
    await init();
    List<Object3d> objects = [];
    List<Map<String, dynamic>> result =
        await _db.query('objects', orderBy: "id");
    for (var item in result) {
      objects.add(Object3d.fromMap(item));
    }
    return objects;
  }

  Future<int> deleteObject(int id) async {
    await init();
    return await _db.delete(
      "objects",
      where: 'id = ?',
      whereArgs: [id],
    );
  }
}
