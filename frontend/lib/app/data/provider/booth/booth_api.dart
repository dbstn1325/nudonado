
import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:frontend/app/controller/booth/booth_category_controller.dart';
import 'package:frontend/app/controller/booth/checkbox_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';

import 'package:frontend/app/data/model/auth/access_token_response.dart';
import 'package:frontend/app/data/model/booth/booth.dart';
import 'package:frontend/app/data/model/booth/booth_request.dart';
import 'package:frontend/app/data/model/booth/booth_response.dart';
import 'package:frontend/app/data/model/booth/location.dart';
import 'package:frontend/app/data/provider/map/coordinate_api.dart';
import 'package:frontend/app/data/repository/map/CoordinateRepository.dart';
import 'package:get/get.dart';

import 'package:uuid/uuid.dart';
import 'package:http/http.dart' as http;

const SERVER_URL = 'http://192.168.0.29:8080/api/';

class BoothApiClient {
  BoothApiClient({required this.httpClient});
  final http.Client httpClient;
  final coordinateController = Get.put(CoordinateController(repository: CoordinateRepository(coordinateClient: CoordinateClient())));
  final checkboxController = Get.find<CheckboxController>();
  final categoryController = Get.find<BoothCategoryController>();

  postBooth(Booth booth) async {
    final storage = FlutterSecureStorage();

    var url = Uri.parse(SERVER_URL + 'booths');
    BoothRequest request = BoothRequest(
      title: booth.storeName.value,
      isTimer: checkboxController.isTimer.value,
      isCurlingIron: checkboxController.isCurlingIron.value,
      backgroundColorDiversity: checkboxController.selectedBackgroundOption.value,
      categoryType: categoryController.categoryModel.selectedCategory.value,
      location: Location(
        latitude: coordinateController.myCoordinate!.latitude.value,
        longitude: coordinateController.myCoordinate!.longitude.value
      ),
    );

    final accessToken = await storage.read(key: 'accessToken');
    try {
      final response = await httpClient.post(url,
          headers: {
            "content-type" : "application/json",
            'Authorization': 'Bearer $accessToken',
          },
        body: jsonEncode(request.toJson()),
      );

      if (response.statusCode != 201) {
        // print(jsonDecode(utf8.decode(response.bodyBytes)));
        print(utf8.decode(response.bodyBytes));
        throw Exception("액세스 토큰 발급에 대해 에러가 발생하였습니다.");
      }

      BoothResponse boothResponse = BoothResponse.fromJson(jsonDecode(response.body));
      print("Received booth ID: ${boothResponse.id}");

    }
    catch(e) {
      print(e);
    }
  }


}