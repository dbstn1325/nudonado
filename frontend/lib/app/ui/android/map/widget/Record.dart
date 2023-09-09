import 'package:get/get.dart';

class Record {
  RxInt boothId = (0).obs;
  RxString title = ('').obs;
  RxString isTimer = ('').obs;
  RxString isCurlingIron = ('').obs;
  RxString backgroundColorDiversity = ('').obs;

  Record({
    int? boothId,
    String? title,
    String? isTimer,
    String? isCurlingIron,
    String? backgroundColorDiversity,
  }) {
    this.boothId.value = boothId ?? 0;
    this.title.value = title ?? '';
    this.isTimer.value = isTimer ?? '';
    this.isCurlingIron.value = isCurlingIron ?? '';
    this.backgroundColorDiversity.value = backgroundColorDiversity ?? '';
  }
}
