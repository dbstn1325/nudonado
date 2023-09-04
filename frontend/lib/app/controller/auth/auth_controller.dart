import 'package:flutter/material.dart';
import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:frontend/app/ui/android/widgets/auth/expired_token_dialog.dart';
import 'package:get/get.dart';

class AuthController extends GetxController {

  final AuthRepository authRepository;
  AuthController({required this.authRepository});

  Future<void> login() async {
    await authRepository.login();
    Get.toNamed(Routes.MAP);
  }

  Future<void> validate() async {
    bool accessTokenValid = await authRepository.validateAccessToken();
    if (!accessTokenValid) {
      Get.dialog(const ExpiredTokenDialog());
      return ;
    }

    Get.toNamed(Routes.MAP);
  }


  logout(){
    Get.toNamed(Routes.INITIAL);
  }
}