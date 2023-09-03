import 'package:flutter/material.dart';
import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:get/get.dart';

class AuthController extends GetxController {

  final AuthRepository authRepository;
  AuthController({required this.authRepository});

  Future<void> login() async {
    await authRepository.login();
    Get.toNamed(Routes.DETAILS);
  }

  Future<void> validate() async {
    bool accessTokenValid = await authRepository.validateAccessToken();
    if (accessTokenValid) {
      Get.toNamed(Routes.DETAILS);
      return ;
    }

    Get.dialog(
      AlertDialog(
        title: Text("토큰이 만료되었습니다.\n다시 로그인해주세요."),
        actions: <Widget>[
          ElevatedButton(
            child: Text("OK"),
            onPressed: () {
              Get.back();
            },
          )
        ],
      ),
    );
  }


  logout(){
    Get.toNamed(Routes.INITIAL);
  }
}