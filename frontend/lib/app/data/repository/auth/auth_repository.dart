import 'package:frontend/app/data/provider/member/auth_api.dart';
import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:meta/meta.dart';

class AuthRepository {
  final AuthApiClient authApiClient;

  AuthRepository({required this.authApiClient});

  login() {
    return authApiClient.login();
  }

  logout(id) {
    return authApiClient.logout();
  }

}