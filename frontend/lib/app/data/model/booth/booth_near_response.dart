import 'dart:ffi';

import 'package:frontend/app/data/model/booth/location.dart';

class BoothNearResponse {
  final int id;
  final String title;
  final String categoryImgSrc;
  final String categoryName;
  final Location location;
  final int removeReq;
  final bool isTimer;
  final bool isCurlingIron;
  final String backgroundColorDiversity;

  BoothNearResponse({
    required this.id,
    required this.title,
    required this.categoryImgSrc,
    required this.categoryName,
    required this.location,
    required this.removeReq,
    required this.isTimer,
    required this.isCurlingIron,
    required this.backgroundColorDiversity,
  });

  factory BoothNearResponse.fromJson(Map<String, dynamic> json) {
    return BoothNearResponse(
      id: json['id'],
      title: json['title'],
      categoryImgSrc: (json['categoryImgSrc'] as String).toLowerCase(),
      categoryName: json['categoryName'],
      location: Location.fromJson(json['location']),
      removeReq: json['removeReq'],
      isTimer: json['isTimer'],
      isCurlingIron: json['isCurlingIron'],
      backgroundColorDiversity: json['backgroundColorDiversity']
    );
  }
}


