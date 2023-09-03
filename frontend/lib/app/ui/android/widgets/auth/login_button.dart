import 'package:flutter/material.dart';

class LoginButton extends StatelessWidget {
  final String path;
  final VoidCallback onTap;

  const LoginButton({required this.path, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 280,
      height: 60,
      child: IconButton(
        onPressed: onTap,
        icon: Image.asset('assets/images/$path.png', fit: BoxFit.fill),
      ),
    );
  }
}
