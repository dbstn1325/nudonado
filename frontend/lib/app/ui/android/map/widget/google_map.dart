import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/data/provider/map/google_map_service.dart';
import 'package:frontend/app/ui/android/map/widget/Record.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class GoogleMapScreen extends StatefulWidget {
  final Completer<GoogleMapController> controllerCompleter;

  const GoogleMapScreen({Key? key, required mapController, required this.controllerCompleter}) : super(key: key);

  @override
  State<GoogleMapScreen> createState() => _GoogleMapScreenState();
}

class _GoogleMapScreenState extends State<GoogleMapScreen> {
  final GoogleMapService googleMapService = GoogleMapService();

  final controller = Get.find<CoordinateController>();
  Map<String, Marker> _markers = {};
  static const double DEFAULT_ZOOM = 17.0;

  void _onMapCreated(GoogleMapController controller) {
    googleMapService.setMapController(controller); // Using the centralized service to store the controller.
  }


// _moveToUserLocation에서 변경
  void _moveToUserLocation(GoogleMapController mapController) {
    print("hello");
    LatLng currentCoord = LatLng(controller.myCoordinate.getLatitude, controller.myCoordinate.getLongitude);
    mapController.animateCamera(CameraUpdate.newCameraPosition(CameraPosition(target: currentCoord, zoom: DEFAULT_ZOOM)));
  }







  @override
  Widget build(BuildContext context) {
    return Obx(() {
      LatLng currentCoord = LatLng(controller.myCoordinate.getLatitude, controller.myCoordinate.getLongitude);
      return GoogleMap(
        initialCameraPosition: CameraPosition(target: currentCoord, zoom: DEFAULT_ZOOM),
        myLocationEnabled: false,
        myLocationButtonEnabled: false,
        markers: _markers.values.toSet(),
        onMapCreated: (controller) => _onMapCreated(controller),
        onTap: (latLng) {
          setState(() {
            selectedRecords = [];
          });
        },
      );
    });
  }
}
