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

  logout(){
    Get.toNamed(Routes.INITIAL);
  }
}