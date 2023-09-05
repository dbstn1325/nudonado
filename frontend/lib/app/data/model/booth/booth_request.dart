import 'package:frontend/app/data/model/booth/location.dart';

class BoothRequest {
  final String categoryType;
  final Location location;

  BoothRequest({
    required this.categoryType,
    required this.location,
  });

  Map<String, dynamic> toJson() => {
    'categoryType': categoryType,
    'location': location.toJson(),
  };
}