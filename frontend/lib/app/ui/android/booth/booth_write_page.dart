import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/booth/checkbox_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:get/get.dart';

import 'widget/booth_category_dropbox.dart';
import 'widget/booth_main_content.dart';
import 'widget/write_page_app_bar.dart';

class BoothWritePage extends StatelessWidget {
  final coordinateController = Get.find<CoordinateController>();
  final checkboxController = Get.put(CheckboxController());
  final boothController = Get.find<BoothController>();
  final textController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: [
            WritePageAppBar(title: "부스 정보"),
            boothMainContent(context)
          ],
        ),
      ),
    );
  }
}
