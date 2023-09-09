import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class WritePageAppBar extends StatelessWidget {
  final String title;

  WritePageAppBar({required this.title});

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
            title,
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
