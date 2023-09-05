import 'package:get/get.dart';

class Coordinate {
  double latitude = 0.0;
  double longitude = 0.0;

  Coordinate({latitude, longitude});

  double get getLatitude => latitude;
  double get getLongitude => longitude;

  // Coordinate.setCoordinates(this.latitude, this.longitude);

  void setCooridnate(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  Coordinate.fromJson(Map<String, dynamic> json) {
    latitude = json['latitude'];
    longitude = json['longitude'];
  }

  Map<String, dynamic> toJson(){
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['latitude'] = this.latitude;
    data['longitude'] = this.longitude;
    return data;
  }


}