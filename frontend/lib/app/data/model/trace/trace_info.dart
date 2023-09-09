import 'package:get/get_rx/get_rx.dart';

class TraceInfo {
  RxString memo = ''.obs;

  void setMemo(String content) {
    memo.value = content;
  }

  Map<String, dynamic> toJson() => {
    'memo': memo,
  };
}
