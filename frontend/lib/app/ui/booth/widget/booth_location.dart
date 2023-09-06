import 'package:flutter/cupertino.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:get/get.dart';

Widget boothLocation() {
  final coordinateController = Get.find<CoordinateController>();

  return Column(
    children: [
      Text("부스 위치"),
      Text("위도: ${coordinateController.myCoordinate?.latitude ?? 'Not available'}"),
      Text("경도: ${coordinateController.myCoordinate?.longitude ?? 'Not available'}"),
    ],
  );
}