import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:get/get.dart';

Widget traceWriteButton(BuildContext context, int boothId) {
  final traceController = Get.find<TraceController>();

  return CupertinoButton(
    onPressed: () => traceController.createTrace(boothId),
    child: Container(
      margin: EdgeInsets.fromLTRB(30, 0, 40, 30),
      constraints: BoxConstraints(
        minWidth: MediaQuery.of(context).size.width,
        minHeight: 50,
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10.0),
        color: const Color(0xffFFD596),
      ),
      child: const Center(
        child: Text(
          "흔적 작성완료",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
      ),
    ),
  );
}