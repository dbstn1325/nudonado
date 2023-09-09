import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/booth/checkbox_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/ui/booth/widget/booth_main_content.dart';
import 'package:get/get.dart';

import 'widget/booth_category_dropbox.dart';
import 'widget/booth_write_appbar.dart';

class BoothWritePage extends StatelessWidget {
  final coordinateController = Get.find<CoordinateController>();
  final checkboxController = Get.put(CheckboxController());
  final boothController = Get.find<BoothController>();
  final textController = TextEditingController();


  // BoothWritePage() {
  //   textController.addListener(() {
  //     boothController.booth.setStoreName(textController.text);
  //   });
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: [
            BoothWriteAppBar(),
            boothMainContent(context)
          ],
        ),
      ),
    );
  }
}
