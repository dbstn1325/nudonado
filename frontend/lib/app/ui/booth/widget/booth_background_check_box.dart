import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/checkbox_controller.dart';
import 'package:get/get.dart';

Widget boothBackgroundCheckbox() {
  final checkboxController = Get.put(CheckboxController());

  return Column(
    children: [
      Text("배경색 다양 정도"),
      Row(
        children: ["상", "중", "하"].map((option) {
          return Expanded(
            child: Obx(() => CheckboxListTile(
              title: Text(option),
              value: checkboxController.selectedBackgroundOption.value == option,
              onChanged: (value) {
                if (value == true) {
                  checkboxController.setBackgroundOption(option);
                }
              },
            )),
          );
        }).toList(),
      ),
    ],
  );
}