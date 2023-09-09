import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
class TraceWritePainter extends CustomPainter {
  final double _lineHeight = 27;
  final int totalLines;

  TraceWritePainter({required this.totalLines});

  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()
      ..color = Colors.black54
      ..strokeWidth = 0.5;

    for (var i = 0; i < totalLines; i++) {
      final offset = Offset(0, i * _lineHeight);
      canvas.drawLine(offset, offset.translate(size.width, 0), paint);
    }
  }

  @override
  bool shouldRepaint(TraceWritePainter oldDelegate) {
    return totalLines != oldDelegate.totalLines;
  }
}