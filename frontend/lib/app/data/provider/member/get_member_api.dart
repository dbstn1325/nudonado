import 'dart:convert';

import 'package:frontend/app/data/model/member.dart';
import 'package:http/http.dart' as http;

const baseUrl = 'http://localhost:8080/api/member/';

class MemberApiClient {
  final http.Client httpClient;
  MemberApiClient({required this.httpClient});

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
      var response = await httpClient.get('baseUrlid' as Uri);
      if (response.statusCode == 200) {
        //Map<String, dynamic> jsonResponse = json.decode(response.body);
      } else
        print('erro -get');
    } catch (_) {}
  }
}