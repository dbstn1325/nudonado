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

    // print(refreshToken);
    // if(accessToken != null && refreshToken != null){
    //   return ;
    // }

    await authApiClient.signIn();
  }

  Future<bool> refresh() async {
    final storage = FlutterSecureStorage();
    dynamic refreshToken = await storage.read(key: 'refreshToken');

    int refreshTokenStatus = await authApiClient.postRefreshToken(refreshToken);

    if(refreshTokenStatus == 200) {
      print("리프레시 토큰을 통해 액세스 토큰 발급에 성공하였습니다.");
      return true;
    }

    if(refreshTokenStatus == 401) {
      print("토큰이 만료되었습니다. \n 다시 로그인을 진행해주세요.");
      return false;
    }

    return false;
  }

  Future<bool> validateAccessToken() async{
    final storage = FlutterSecureStorage();
    dynamic accessToken = await storage.read(key: 'accessToken');
    int accessTokenStatus = await authApiClient.validateAccessToken("asddasd");

    if(accessTokenStatus == 200) {
      print("he");
      return true;
    }
    if(accessTokenStatus == 401) {
      bool refreshTokenValid = await refresh();

      return refreshTokenValid;
    }

    return false;
  }

  logout(id) {
    return authApiClient.logout();
  }

}