import 'package:flutter/material.dart';
import 'package:frontend/app/data/model/map/coordinate_model.dart';
import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/map/CoordinateRepository.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:frontend/app/ui/android/widgets/auth/expired_token_dialog.dart';
import 'package:get/get.dart';

class CoordinateController extends GetxController {

  final CoordinateRepository repository;
  CoordinateController({required this.repository});

  final _myCoordinate = Coordinate().obs;

  get myCoordinate => _myCoordinate.value;
  set myCoordinate(coordinate) => _myCoordinate.value = coordinate;

  getMyCoordinate() {
    repository.me().then((data) {
      myCoordinate = data;
    });
  }


}