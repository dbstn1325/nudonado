
import 'package:frontend/app/controller/auth/auth_controller.dart';
import 'package:frontend/app/controller/home/home_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/data/provider/map/coordinate_api.dart';
import 'package:frontend/app/data/provider/member/auth_api.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/map/CoordinateRepository.dart';
import 'package:http/http.dart' as http;
import 'package:get/get.dart';

class AuthBinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut<CoordinateController>(() {
      return CoordinateController(
          repository:
          CoordinateRepository(coordinateClient: CoordinateClient()));
    });
  }
}