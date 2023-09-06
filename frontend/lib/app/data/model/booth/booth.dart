import 'package:get/get.dart';

class Booth {
  var storeName = ''.obs;
  // Add any other fields here that are relevant to a Booth

  void setStoreName(String name) {
    storeName.value = name;
  }
}
