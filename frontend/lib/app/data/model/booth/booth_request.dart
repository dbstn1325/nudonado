import 'package:frontend/app/data/model/booth/location.dart';

class BoothRequest {
  final String title;
  final bool isTimer;
  final bool isCurlingIron;
  final String backgroundColorDiversity;
  final String categoryType;
  final Location location;

  BoothRequest({
    required this.title,
    required this.isTimer,
    required this.isCurlingIron,
    required this.backgroundColorDiversity,
    required this.categoryType,
    required this.location,
  });

  Map<String, dynamic> toJson() => {
    'title': title,
    'isTimer': isTimer,
    'isCurlingIron': isCurlingIron,
    'backgroundColorDiversity': backgroundColorDiversity,
    'categoryType': categoryType,
    'location': location.toJson(),
  };
}