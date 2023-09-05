
import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';

import 'package:frontend/app/data/model/auth/access_token_response.dart';
import 'package:frontend/app/data/model/booth/booth_request.dart';
import 'package:frontend/app/data/model/booth/booth_response.dart';
import 'package:frontend/app/data/model/booth/location.dart';
import 'package:frontend/app/data/provider/map/coordinate_api.dart';
import 'package:frontend/app/data/repository/map/CoordinateRepository.dart';
import 'package:get/get.dart';

import 'package:uuid/uuid.dart';
import 'package:http/http.dart' as http;

const SERVER_URL = 'http://172.17.236.158:8080/api/';

class BoothApiClient {
  BoothApiClient({required this.httpClient});
  final http.Client httpClient;
  final cooridnateController = Get.put(CoordinateController(repository: CoordinateRepository(coordinateClient: CoordinateClient())));

  postBooth() async {
    final storage = FlutterSecureStorage();

    print("booth 실행");
    var url = Uri.parse(SERVER_URL + 'booths');
    BoothRequest request = BoothRequest(
      categoryType: "selflex",
      location: Location(
        latitude: 35.19754414695399,
        longitude: 128.1671744126717,
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