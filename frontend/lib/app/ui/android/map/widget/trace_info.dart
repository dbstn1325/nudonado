import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:frontend/app/ui/android/map/widget/trace_write_paint.dart';
import 'package:get/get.dart';

Widget traceMemo(BuildContext context) {
  final traceController = Get.find<TraceController>();
  final textController = TextEditingController();

  textController.addListener(() {
    traceController.traceInfo.setMemo(textController.text);
  });

  return Column(
    children: [
      const Text("흔적 메모"),
      SingleChildScrollView(
        child: Stack(
            children: [
              Container(
              margin: EdgeInsets.only(left: 50, right: 50, top: 23),
              child: CustomPaint(
                painter: TraceWritePainter(totalLines: 6),
                child: Container(
                  width: MediaQuery.of(context).size.width,
                  height: MediaQuery.of(context).size.height/5,
                  child: Text(""),
                ),
              ),
            ),
              Positioned(
                bottom: 0,
                left: 0,
                right: 0,
                top: 7,
                child: Container(
                  margin: EdgeInsets.fromLTRB(60, 0, 60, 0),
                  child: Form(
                    child: TextFormField(
                      validator: (value) {
                        if(value!.isEmpty){
                          return "한 글자 이상 입력해주세요.";
                        }else {
                          return null;
                        }
                      },
                      maxLines: 30,
                      maxLength: 150,
                      minLines: 1,
                      style: Theme.of(context).textTheme.bodyMedium!.copyWith(
                        height: 2,
                      ),
                      decoration: InputDecoration(
                        enabledBorder: InputBorder.none,
                        focusedBorder: InputBorder.none,
                      ),
                      controller: textController,
                      keyboardType: TextInputType.text,
                    ),
                  ),
                ),
              ),
            ]
        ),
      ),
    ],
  );
}