// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'user.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

User _$UserFromJson(Map<String, dynamic> json) => User(
      id: json['id'] as int,
      lastName: json['lastname'] as String,
      firstName: json['firstname'] as String,
      email: json['email'] as String,
      
    );

Map<String, dynamic> _$UserToJson(User instance) => <String, dynamic>{
      'id': instance.id,
      'lastname': instance.lastName,
      'firstname': instance.firstName,
      
      // 'role': instance.role,
      // 'token': instance.token,
    };
