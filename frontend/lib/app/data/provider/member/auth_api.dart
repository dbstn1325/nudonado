
import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:frontend/app/data/model/auth/access_token_response.dart';
import 'package:frontend/env/env.dart';

import 'package:uuid/uuid.dart';
import 'package:http/http.dart' as http;

const authServerUri = 'http://172.21.7.92:8080/api/auth';

class AuthApiClient {
  final http.Client httpClient;
  AuthApiClient({required this.httpClient});

  signIn() async {
    // AndroidOptions _getAndroidOptions() => const AndroidOptions(
    //   encryptedSharedPreferences: true,
    // );
    // final storage = new FlutterSecureStorage(aOptions: _getAndroidOptions());
    final storage = new FlutterSecureStorage();

    try {
      final clientState = const Uuid().v4();
      print("signIn 실행");

      final url = Uri.https('kauth.kakao.com', '/oauth/authorize', {
        'response_type': 'code',
        'client_id': Env.kakaoApiKey,
        'redirect_uri': '$authServerUri/kakao/sign_in',
        'state': clientState,
      });

      final result = await FlutterWebAuth.authenticate(
          url: url.toString(), callbackUrlScheme: "webauthcallback");
      final body = Uri.parse(result);
      final param = Uri
          .parse(result)
          .queryParameters;

      await storage.write(key: 'accessToken', value: param['accessToken']);
      await storage.write(key: 'refreshToken', value: param['refreshToken']);


    }
    catch(e) {
      print(e);
    }
  }

  postAccessToken(dynamic refreshToken) async{
    try {
      final response = await httpClient.post(
        Uri.parse('$authServerUri/token/access'),
        headers: {
          'Content-Type': 'application/json',
        },
        body: jsonEncode({'refreshToken': refreshToken}),
      );

      if (response.statusCode != 200) {
        // print(jsonDecode(utf8.decode(response.bodyBytes)));
        throw Exception("액세스 토큰 발급에 대해 에러가 발생하였습니다.");
      }

      AccessTokenResponse.fromJson(jsonDecode(response.body));
    } catch(e) {
      print(e);
    }
    
  }
  Future<bool> validateAccessToken(dynamic accessToken) async {
    print("자동 로그인을 위한 유효성 검증 start!");
    final storage = FlutterSecureStorage();
    try {
      final response = await httpClient.get(
        Uri.parse('$authServerUri/validate/token'),
        headers: {
          'Authorization': 'Bearer $accessToken',
        },
      );

      if (response.statusCode != 200) {
        print(response.statusCode);
        print(utf8.decode(response.bodyBytes));
        return false;
      }

      print(response.statusCode);
      storage.deleteAll();

      return true;

    } catch (e) {
      print(e);
      return false;
    }
  }


  logout() async {

  }


}