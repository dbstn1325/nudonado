import 'package:flutter/cupertino.dart';
import 'package:frontend/app/controller/booth/checkbox_controller.dart';
import 'package:frontend/app/ui/android/booth/widget/booth_background_check_box.dart';
import 'package:frontend/app/ui/android/booth/widget/booth_check_box.dart';
import 'package:frontend/app/ui/android/booth/widget/booth_info.dart';
import 'package:frontend/app/ui/android/booth/widget/booth_location.dart';
import 'package:frontend/app/ui/android/booth/widget/booth_submit_button.dart';
import 'package:frontend/app/ui/android/booth/widget/category_drop_down.dart';
import 'package:get/get.dart';

Widget boothMainContent(BuildContext context) {
  final checkboxController = Get.put(CheckboxController());

  return Container(
    margin: EdgeInsets.only(top: 10),
    child: Column(
      children: [
        boothLocation(),
        boothInfo(),
        categoryDropdown(),
        boothCheckbox("타이머 유무", "네", "아니요", checkboxController.isTimer, checkboxController.toggleTimer),
        boothCheckbox("고데기 유무", "네", "아니요", checkboxController.isCurlingIron, checkboxController.toggleCurlingIron),
        boothBackgroundCheckbox(),
        boothSubmitButton(context),
      ],
    ),
  );
}