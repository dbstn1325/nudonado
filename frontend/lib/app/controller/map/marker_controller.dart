import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class MarkerController extends GetxController {
  RxMap<String, Marker> markers = <String, Marker>{}.obs;
  RxBool isWriteVisible = false.obs;

  void addMarker(String id, Marker marker) {
    markers[id] = marker;
  }

  void clearMarkers() {
    markers.clear();
  }

  void turnOnIsWrited() {
    isWriteVisible.value = true;
  }

  void turnOffIsWrited() {
    isWriteVisible.value = false;
  }

}
