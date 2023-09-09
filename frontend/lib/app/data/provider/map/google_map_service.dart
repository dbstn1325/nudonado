import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:flutter/material.dart';

class GoogleMapService {
  late GoogleMapController _mapController;

  void setMapController(GoogleMapController controller) {
    _mapController = controller;
  }


  void moveToLocation(LatLng location, [double zoomLevel = 17.0]) async {
    if (_mapController == null) {
      print("Map Controller not initialized");
      return;
    }
    await _mapController!.animateCamera(
      CameraUpdate.newCameraPosition(CameraPosition(target: location, zoom: zoomLevel)),
    );
  }


  Future<void> moveToUserLocation() async {
    // You'll need to get the user's location here.
    // For this example, I'm hardcoding it.
    LatLng userLocation = LatLng(35.15320067240981, 128.09971949420316);
    moveToLocation(userLocation);
  }
}
