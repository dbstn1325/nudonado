import 'package:get/get.dart';

class CheckboxController extends GetxController {
  RxBool isCurlingIron = true.obs;
  RxBool isTimer = true.obs;
  RxString selectedBackgroundOption = RxString("상");

  void toggleCurlingIron(bool? value) {
    if (value != null) {
      isCurlingIron.value = !isCurlingIron.value;
    }
  }

  void toggleTimer(bool? value) {
    if (value != null) {
      isTimer.value = !isTimer.value;
    }
  }

  void setBackgroundOption(String value) {
    selectedBackgroundOption.value = value;
  }


}