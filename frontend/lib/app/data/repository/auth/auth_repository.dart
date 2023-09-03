import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend/app/data/provider/member/auth_api.dart';
import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:get/get.dart';
import 'package:meta/meta.dart';

class AuthRepository {
  final AuthApiClient authApiClient;

  AuthRepository({required this.authApiClient});

  login() async{
    final storage = FlutterSecureStorage();
    dynamic accessToken = await storage.read(key: 'accessToken');
    dynamic refreshToken = await storage.read(key: 'refreshToken');
    if(accessToken != null && refreshToken != null){
      return ;
    }

    await authApiClient.signIn();
  }

  Future<bool> validateAccessToken() async{
    final storage = FlutterSecureStorage();
    dynamic accessToken = await storage.read(key: 'accessToken');
    return authApiClient.validateAccessToken(accessToken);
  }

  logout(id) {
    return authApiClient.logout();
  }

}