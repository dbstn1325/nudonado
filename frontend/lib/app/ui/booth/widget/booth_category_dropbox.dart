import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/booth_category_controller.dart';
import 'package:get/get.dart';

const List<String> list = <String>['인생 네컷', '셀플릭스', '포토 매틱', '하루 필름'];

class BoothCategoryDropBox extends StatefulWidget {
  const BoothCategoryDropBox({super.key});

  @override
  State<BoothCategoryDropBox> createState() => _BoothCategoryDropBoxState();
}

class _BoothCategoryDropBoxState extends State<BoothCategoryDropBox> {
  final BoothCategoryController categoryController = Get.put(BoothCategoryController());
  String dropdownValue = list.first;


  @override
  Widget build(BuildContext context) {
    return DropdownMenu<String>(
      initialSelection: list.first,
      onSelected: (String? value) {
        setState(() {
          dropdownValue = value!;
          categoryController.categoryModel.setSelectedCategory(value);
        });
      },
      dropdownMenuEntries: list.map<DropdownMenuEntry<String>>((String value) {
        return DropdownMenuEntry<String>(value: value, label: value);
      }).toList(),
    );
  }
}