import 'dart:convert';

import 'package:frontend/app/data/model/member.dart';
import 'package:http/http.dart' as http;

const baseUrl = 'http://localhost:8080/api/members';

class HomeApiClient {
  final http.Client httpClient;
  HomeApiClient({required this.httpClient});

  getAll() async {
    try {
      var response = await httpClient.get(baseUrl as Uri);
      if (response.statusCode == 200) {
        Iterable jsonResponse = json.decode(response.body);
        List<Member> listMyModel =
        jsonResponse.map((member) => Member.fromJson(member)).toList();
        return listMyModel;
      } else
        print('error');
    } catch (_) {}
  }

  getId(id) async {
    try {
      var response = await httpClient.get(Uri.parse(baseUrl + "/me/" + id.toString()));
      print("heelo");
      print(Uri.parse(baseUrl + id.toString()));
      if (response.statusCode == 200) {
        return Member.fromJson(jsonDecode(utf8.decode(response.bodyBytes)));
      } else {
        print('error - home');
        return null;
      }
    } catch (_) {
      return null;
    }
  }

}