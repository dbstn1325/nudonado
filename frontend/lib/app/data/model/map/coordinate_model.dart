class Location {
  double latitude = 0.0;
  double longitude = 0.0;

  Location({required this.latitude, required this.longitude});

  Location.fromJson(Map<String, dynamic> json) {
    latitude = json['latitude'];
    longitude = json['longitude'];
  }
}