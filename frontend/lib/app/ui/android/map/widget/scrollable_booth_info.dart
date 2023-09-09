import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/ui/android/map/widget/booth_record.dart';
import 'package:frontend/app/ui/android/map/widget/trace_content.dart';
import 'package:get/get.dart';

Widget ScrollableBoothInfo(BuildContext context) {
  final coordinateController = Get.find<CoordinateController>();

  return Obx(() {
    if (coordinateController.selectedRecord != null) {
      return Positioned(
        bottom: 20.0,
        left: 10.0,
        child: Container(
          width: MediaQuery.of(context).size.width,
          height: 140.0,
          child: ListView(
            scrollDirection: Axis.horizontal,
            children: [
              BoothRecord(),
              TraceContent()
            ],
          ),
        ),
      );
    }
    return SizedBox.shrink();
  });
}

