
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:frontend/env/env.dart';

import 'package:uuid/uuid.dart';
import 'package:http/http.dart' as http;

const serverUri = 'http://172.21.7.92:8080/api/auth/kakao/sign_in';

class AuthApiClient {
  final http.Client httpClient;
  AuthApiClient({required this.httpClient});

  login() async {
    try {
      final clientState = const Uuid().v4();

      final url = Uri.https('kauth.kakao.com', '/oauth/authorize', {
        'response_type': 'code',
        'client_id': Env.kakaoApiKey,
        'redirect_uri': serverUri,
        'state': clientState,
      });

      final result = await FlutterWebAuth.authenticate(
          url: url.toString(), callbackUrlScheme: "webauthcallback");
      final body = Uri.parse(result);
      final param = Uri
          .parse(result)
          .queryParameters;

    }
    catch(_) {

    }
  }

  logout() async {

  }


}