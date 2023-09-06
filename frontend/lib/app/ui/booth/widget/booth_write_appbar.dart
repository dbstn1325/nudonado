import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


class BoothWriteAppBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      elevation: 2.0,
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
    );
  }
}