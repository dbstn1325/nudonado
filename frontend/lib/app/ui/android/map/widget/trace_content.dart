import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:get/get.dart';

Widget TraceContent() {
  final traceController = Get.find<TraceController>();

  if (traceController.traces.isNotEmpty) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: traceController.traces.map((trace) => Container(
        width: 200.0,
        margin: const EdgeInsets.only(right: 20.0),
        child: Container(
          decoration: _boxDecoration(),
          padding: EdgeInsets.all(20.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text("사진"),
              SizedBox(height: 45.0,),
              Text('ㄴ ${trace.memo}')
            ],
          ),
        ),
      )).toList(),
    );
  }
  return Container(
      width: 170.0,
      margin: const EdgeInsets.only(right: 20.0),
      child: Container(
        decoration: _boxDecoration(),
        child: Center(child: Text("현재 남긴 추억이 없어요ㅜㅠ\n친구들과 추억을 남겨봐요!!"))
      )
  );
}

BoxDecoration _boxDecoration() {
  return BoxDecoration(
    color: Colors.white,
    borderRadius: BorderRadius.circular(40.0),
    boxShadow: [
      BoxShadow(color: Colors.black12, spreadRadius: 0, blurRadius: 5, offset: Offset(0, 3)),
    ],
  );
}