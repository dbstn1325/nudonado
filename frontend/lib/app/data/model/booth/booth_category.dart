import 'package:get/get.dart';

class BoothCategory {
  var selectedCategory = '인생 네컷'.obs;

  void setSelectedCategory(String category) {
    selectedCategory.value = category;
  }
}
