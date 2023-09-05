
import 'package:frontend/app/controller/auth/auth_controller.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/home/home_controller.dart';
import 'package:frontend/app/data/provider/booth/booth_api.dart';
import 'package:frontend/app/data/provider/member/auth_api.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/booth/booth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:http/http.dart' as http;
import 'package:get/get.dart';

class BoothBinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut<BoothController>(() {
      return BoothController(
          boothRepository:
          BoothRepository(boothApiClient: BoothApiClient(httpClient: http.Client())));
    });
  }
}