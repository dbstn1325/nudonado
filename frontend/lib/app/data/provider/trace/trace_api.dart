import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:frontend/app/data/model/trace/trace_model.dart';
import 'package:frontend/app/data/provider/map/coordinate_api.dart';
import 'package:frontend/app/data/repository/map/CoordinateRepository.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;


const SERVER_URL = 'http://172.21.3.241:8080/api/';

class TraceApiClient {
  final http.Client httpClient;
  TraceApiClient({required this.httpClient});

  final coordinateController = Get.put(CoordinateController(repository: CoordinateRepository(coordinateClient: CoordinateClient())));


  getAll() async {
    final storage = FlutterSecureStorage();
    var url = Uri.parse(SERVER_URL + coordinateController.selectedRecord!.boothId.value.toString() + '/traces');
    final accessToken = await storage.read(key: 'accessToken');
    try {
      final response = await httpClient.get(url,
        headers: {
          'Authorization': 'Bearer $accessToken',
        },
      );

      if (response.statusCode != 200) {
        // print(jsonDecode(utf8.decode(response.bodyBytes)));
        print(utf8.decode(response.bodyBytes));
        throw Exception("액세스 토큰 발급에 대해 에러가 발생하였습니다.");
      }

      final List<dynamic> jsonResponse = json.decode(utf8.decode(response.bodyBytes));
      final List<Trace> fetchedTraces = jsonResponse.map((data) => Trace.fromJson(data)).toList();


      for (Trace trace in fetchedTraces) {
        print("ID: ${trace.id}, BoothID: ${trace.boothId}, Category: ${trace.category}, Memo: ${trace.memo}");
      }

      return fetchedTraces;

    }
    catch(e) {
      print(e);
    }
  }

}