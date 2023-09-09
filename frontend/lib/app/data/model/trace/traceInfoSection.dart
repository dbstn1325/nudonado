import 'package:flutter/cupertino.dart';
import 'package:frontend/app/ui/android/map/widget/trace_info.dart';

Widget traceMemoSection(BuildContext context) {
  return Column(
    children: [
      SizedBox(height: 370,),
      traceMemo(context),
    ],
  );
}