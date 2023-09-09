import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:get/get.dart';

class ExpiredTokenDialog extends StatelessWidget {
  const ExpiredTokenDialog({super.key});

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text("토큰이 만료되었습니다.\n다시 로그인해주세요."),
      actions: <Widget>[
        ElevatedButton(
          child: Text("확인"),
          onPressed: () {
            Get.back();
          },
        )
      ],
    );
  }
}
