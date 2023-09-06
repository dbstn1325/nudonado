import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';


Widget boothCheckbox(String title, String option1, String option2, RxBool valueController, Function onChanged) {
  return Column(
    children: [
      Text(title),
      Row(
        children: [
          Expanded(
            child: Obx(() => CheckboxListTile(
              title: Text(option1),
              value: valueController.value,
              onChanged: (value) => onChanged(value),
            )),
          ),
          Expanded(
            child: Obx(() => CheckboxListTile(
              title: Text(option2),
              value: !valueController.value,
              onChanged: (value) => onChanged(value),
            )),
          ),
        ],
      ),
    ],
  );
}
