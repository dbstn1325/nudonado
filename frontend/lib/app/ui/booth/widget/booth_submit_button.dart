import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:get/get.dart';

Widget boothSubmitButton(BuildContext context) {
  final boothController = Get.find<BoothController>();

  return CupertinoButton(
    onPressed: () async {
      await boothController.createBooth();
    },
    child: Container(
      margin: EdgeInsets.fromLTRB(30, 0, 30, 30),
      constraints: BoxConstraints(
        minWidth: MediaQuery.of(context).size.width,
        minHeight: 40,
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10.0),
        color: Colors.orange,
      ),
      child: Center(
        child: Text(
          "제보 하기",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
      ),
    ),
  );
}
