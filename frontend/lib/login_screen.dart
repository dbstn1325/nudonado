

import 'package:frontend/app/controller/auth/auth_controller.dart';
import 'package:frontend/app/ui/android/widgets/auth/login_button.dart';
import 'package:frontend/login_platform.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_core/src/get_main.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:flutter_web_auth/flutter_web_auth.dart';
import 'package:uuid/uuid.dart';


class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  LoginPlatform _loginPlatform = LoginPlatform.none;
  final controller = Get.find<AuthController>();

  void signOut() async {
    switch (_loginPlatform) {
      case LoginPlatform.kakao:
        break;
      case LoginPlatform.google:
        await GoogleSignIn().signOut();
        break;
      case LoginPlatform.none:
        break;
    }

    setState(() {
      _loginPlatform = LoginPlatform.none;
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("부스덕", style: TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),),
            Text("부스 덕후들만의 공간", style: TextStyle(
                color: Colors.grey
            ),
            ),
            SizedBox(height: 30,),
            LoginButton(path: "kakao_logo", onTap: () {controller.login(); })
          ],
        ),
      ),
    );
  }




}