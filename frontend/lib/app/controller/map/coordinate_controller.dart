import 'package:flutter/material.dart';
import 'package:frontend/app/data/model/map/coordinate_model.dart';
import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/map/CoordinateRepository.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:frontend/app/ui/android/map/widget/Record.dart';
import 'package:get/get.dart';

class CoordinateController extends GetxController {
  final CoordinateRepository repository;
  CoordinateController({required this.repository});

  final _myCoordinate = Coordinate().obs;
  final _selectedRecord = Record().obs;
  final _isEmpty = true.obs;

  set selectedRecord(Record? record) {
    if (record != null) {
      _selectedRecord.value = record;
      _isEmpty.value = false;
    } else {
      _isEmpty.value = true;
    }
  }

  Record? get selectedRecord => _isEmpty.value ? null : _selectedRecord.value;

  void clearSelectedRecord() {
    _isEmpty.value = true;
  }

  void updateSelectedRecord(Record record) {
    _selectedRecord.value = record;
    _isEmpty.value = false;
  }


  get myCoordinate => _myCoordinate.value;
  set myCoordinate(coordinate) => _myCoordinate.value = coordinate;

  void updateMyCoordinate(Coordinate coord) {
    myCoordinate.value = coord;
  }

  getMyCoordinate() {
    repository.me().then((data) {
      myCoordinate = data;
    });
  }

  void updateBoothCoordinate(double newLatitude, double newLongitude) {
    _myCoordinate.value.setCooridnate(newLatitude, newLongitude);
  }



}