
import 'dart:html';

import 'package:frontend/app/data/model/map/coordinate_model.dart';
import 'package:location/location.dart';

class CoordinateClient {

  locateMe() async {
    late double lat;
    late double lng;
    Location location = new Location();
    late bool _serviceEnabled;
    late PermissionStatus _permissionGranted;

    Coordinate coordinate = Coordinate(latitude: 35.15320067240981, longitude: 128.09971949420316);
    _serviceEnabled = await location.serviceEnabled();
    if (!_serviceEnabled) {
      _serviceEnabled = await location.requestService();
      if (!_serviceEnabled) {
        return;
      }
    }

    _permissionGranted = await location.hasPermission();
    if (_permissionGranted == PermissionStatus.denied) {
      _permissionGranted = await location.requestPermission();
      if (_permissionGranted != PermissionStatus.granted) {
        return;
      }
    }
    try {
      await location.getLocation().then((res) {
        return Coordinate(latitude: res.latitude!, longitude: res.longitude!)
      });

      return Coordinate(latitude: 35.15320067240981, longitude: 128.09971949420316);
    }catch(e){
      print(e);
    }
  }

}