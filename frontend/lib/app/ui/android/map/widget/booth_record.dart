import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/ui/android/map/widget/booth_info_text.dart';
import 'package:get/get.dart';

Widget BoothRecord() {
  final coordinateController = Get.find<CoordinateController>();

  return Container(
    height: 130.0,
    width: 200.0,
    margin: const EdgeInsets.only(right: 20.0),
    decoration: _boxDecoration(),
    child: Padding(
      padding: const EdgeInsets.only(left: 15.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          BoothInfoText("매장 명 :", coordinateController.selectedRecord!.title.value),
          BoothInfoText("타이머 유무 :", coordinateController.selectedRecord!.isTimer.value),
          BoothInfoText("고데기 유무 :", coordinateController.selectedRecord!.isCurlingIron.value),
          BoothInfoText("배경색 다양 정도 :", coordinateController.selectedRecord!.backgroundColorDiversity.value)
        ],
      ),
    ),
  );
}

BoxDecoration _boxDecoration() {
  return BoxDecoration(
    color: Colors.white,
    borderRadius: BorderRadius.circular(40.0),
    boxShadow: [
      BoxShadow(color: Colors.black12, spreadRadius: 0, blurRadius: 5, offset: Offset(0, 3)),
    ],
  );
}