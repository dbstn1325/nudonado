import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:get/get.dart';

Widget boothInfo() {
  final boothController = Get.find<BoothController>();
  final textController = TextEditingController();

  textController.addListener(() {
    boothController.booth.setStoreName(textController.text);
  });

  return Column(
    children: [
      Text("부스 정보"),
      Text("가게 이름"),
      Padding(
        padding: EdgeInsets.symmetric(horizontal: 8, vertical: 16),
        child: TextField(
          controller: textController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: 'Enter a search term',
          ),
        ),
      ),
    ],
  );
}