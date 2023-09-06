import 'package:flutter/cupertino.dart';
import 'package:frontend/app/ui/booth/widget/booth_category_dropbox.dart';

Widget categoryDropdown() {
  return Column(
    children: [
      Text("카테고리"),
      BoothCategoryDropBox(),
    ],
  );
}