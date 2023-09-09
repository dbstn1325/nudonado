import 'package:flutter/material.dart';

class LoginButton extends StatelessWidget {
  final String path;
  final VoidCallback onTap;

  const LoginButton({required this.path, required this.onTap});

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: onTap,
      style: ButtonStyle(
        backgroundColor: MaterialStateProperty.all(
          const Color(0xff0165E1),
        ),
      ),
      child: const Text('로그아웃'),
    );
  }
}
