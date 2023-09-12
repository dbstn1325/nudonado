import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'dart:typed_data';

class MarkerController extends GetxController {
  RxMap<String, Marker> markers = <String, Marker>{}.obs;
  RxBool isWriteVisible = false.obs;

  RxString? prevSelectedMarkerId = ''.obs;
  Rx<Uint8List?> prevSelectedMarkerOriginalIcon = Rx<Uint8List?>(null);

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

  void updateMarker(String id, Marker updatedMarker) {
    markers[id] = updatedMarker;
    update();
  }

  void handleMarkerTap(String currentMarkerId, Uint8List originalIcon, Uint8List largerIcon) {
    resetPreviousMarkerToOriginal();
    updateMarkerWithLargerIcon(currentMarkerId, largerIcon);

    prevSelectedMarkerId!.value = currentMarkerId;
    prevSelectedMarkerOriginalIcon.value = originalIcon;
  }


  void resetPreviousMarkerToOriginal() {
    if (prevSelectedMarkerId!.value.isEmpty || prevSelectedMarkerOriginalIcon.value == null) return;

    final currentMarker = markers[prevSelectedMarkerId!.value];
    if (currentMarker != null) {
      final originalIconMarker = currentMarker.copyWith(
        iconParam: BitmapDescriptor.fromBytes(prevSelectedMarkerOriginalIcon.value!),
      );
      updateMarker(prevSelectedMarkerId!.value, originalIconMarker);
    }
  }

  void updateMarkerWithLargerIcon(String id, Uint8List largerIcon) {
    final currentMarker = markers[id];
    if (currentMarker != null) {

      final largerIconMarker = currentMarker.copyWith(
        iconParam: BitmapDescriptor.fromBytes(largerIcon),
      );
      updateMarker(id, largerIconMarker);
    }
  }
}
