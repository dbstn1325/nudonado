import 'package:get/get.dart';
class Coordinate {
  RxDouble latitude = 0.0.obs;
  RxDouble longitude = 0.0.obs;

  Coordinate({double? latitude, double? longitude}) {
    if (latitude != null) {
      this.latitude.value = latitude;
    }
    if (longitude != null) {
      this.longitude.value = longitude;
    }
  }

  double get getLatitude => latitude.value;
  double get getLongitude => longitude.value;

  void setCooridnate(double newLatitude, double newLongitude) {
    this.latitude.value = newLatitude;
    this.longitude.value = newLongitude;
  }

  Coordinate.fromJson(Map<String, dynamic> json) {
    latitude.value = json['latitude'];
    longitude.value = json['longitude'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['latitude'] = this.latitude.value;
    data['longitude'] = this.longitude.value;
    return data;
  }

}