import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:frontend/app/data/model/trace/traceInfoSection.dart';
import 'package:frontend/app/data/model/trace/trace_info.dart';
import 'package:frontend/app/data/model/trace/trace_write_button.dart';
import 'package:frontend/app/ui/android/booth/widget/write_page_app_bar.dart';
import 'package:frontend/app/ui/android/map/widget/trace_info.dart';
import 'package:frontend/app/ui/android/map/widget/trace_write_paint.dart';
import 'package:get/get.dart';

class TraceWritePage extends StatefulWidget {
  const TraceWritePage({Key? key}) : super(key: key);

  @override
  State<TraceWritePage> createState() => _TraceWritePageState();
}


class _TraceWritePageState extends State<TraceWritePage> {

  final int boothId = (Get.arguments?['boothId'] as int?) ?? 0;


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Column(
          children: [
            WritePageAppBar(title: "흔적 작성"),
            traceMemoSection(context),
            traceWriteButton(context, boothId)
          ],
        )
    );
  }
}
