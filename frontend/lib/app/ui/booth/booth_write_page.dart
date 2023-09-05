import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:get/get.dart';

class BoothWritePage extends StatelessWidget {
  final coordinateController = Get.find<CoordinateController>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          children: [
            // AppBar 주변에 그림자 추가
            Material(
              elevation: 2.0, // 그림자의 깊이를 설정. 원하는 값으로 조정 가능.
              borderRadius: BorderRadius.vertical(bottom: Radius.circular(15.0)),
              child: ClipRRect(
                borderRadius: BorderRadius.vertical(bottom: Radius.circular(15.0)),
                child: AppBar(
                  backgroundColor: Colors.white,
                  title: Text(
                    "부스 정보",
                    style: TextStyle(
                      color: Colors.black,
                      fontSize: 17,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  leading: IconButton(
                    icon: Icon(Icons.arrow_back_ios, color: Colors.black),
                    onPressed: () => Navigator.of(context).pop(),
                  ),
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(top: 10),
              child: Column(
                children: [
                  Text("부스 위치"),
                  Text("위도: ${coordinateController.myCoordinate?.latitude ?? 'Not available'}"),
                  Text("경도: ${coordinateController.myCoordinate?.longitude ?? 'Not available'}"),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
