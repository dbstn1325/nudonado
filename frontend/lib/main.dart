import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:get/get.dart';

void main() {
  // debugPaintSizeEnabled = true;
  runApp(GetMaterialApp(
    debugShowCheckedModeBanner: false,
    initialRoute: Routes.LOGIN,
    getPages: AppPages.pages,
  ));
}
