import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

Widget boothInfo() {
  final textController = TextEditingController();

  return Column(
    children: [
      Text("부스 정보"),
      Text("가게 이름"),
      Padding(
        padding: EdgeInsets.symmetric(horizontal: 8, vertical: 16),
        child: TextField(
          controller: textController,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            hintText: 'Enter a search term',
          ),
        ),
      ),
    ],
  );
}