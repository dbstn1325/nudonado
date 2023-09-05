import 'package:frontend/app/data/model/map/coordinate_model.dart';
import 'package:location/location.dart';

class CoordinateClient {

  locateMe() async {
    Location location = new Location();
    Coordinate coordinate = new Coordinate();
    late bool _serviceEnabled;
    late PermissionStatus _permissionGranted;

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

      LocationData res = await location.getLocation();
      coordinate.setCooridnate(35.153173636585834, 128.09971913037515);
      // coordinate.setCooridnate(res.latitude!, res.longitude!);
      return coordinate;
    } catch (e, stacktrace) {
      print("발생 에러 : $e");
      print("Stacktrace: $stacktrace");
      return null;
    }

  }

}