
import 'package:frontend/app/controller/home/home_controller.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:http/http.dart' as http;
import 'package:get/get.dart';

class HomeBinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut<HomeController>(() {
      return HomeController(
          homeRepository:
          HomeRepository(homeApiClient: HomeApiClient(httpClient: http.Client())));
    });
  }
}