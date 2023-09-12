import 'dart:async';
import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/controller/map/marker_controller.dart';
import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:frontend/app/data/provider/map/google_map_service.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:frontend/app/ui/android/global/theme/color_styles.dart';
import 'package:frontend/app/ui/android/map/widget/Record.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'dart:ui' as ui;

import 'widget/scrollable_booth_info.dart';

class MapPage extends StatefulWidget {
  const MapPage({Key? key}) : super(key: key);

  @override
  State<MapPage> createState() => _MapPageState();
}

class _MapPageState extends State<MapPage> {
  Completer<GoogleMapController> _controller = Completer();
  GoogleMapController? mapController;
  final coordinateController = Get.find<CoordinateController>();
  final boothController = Get.find<BoothController>();
  final markerController = Get.put(MarkerController());
  LatLng curLocation = LatLng(35.15320067240981, 128.09971949420316);
  bool isWriteVisiable = false;
  final GoogleMapService googleMapService = GoogleMapService();
  final traceController = Get.find<TraceController>();

  void _onMapCreated(GoogleMapController controller) {
    mapController = controller;
    _controller.complete(controller);
  }

  void moveToNewLocation(LatLng newLatLng) {
    mapController?.animateCamera(
      CameraUpdate.newCameraPosition(CameraPosition(target: newLatLng, zoom: 17)),
    );
  }



  @override
  Widget build(BuildContext context) {


    return Stack(
        children:[
          Obx(() {
            LatLng currentCoord = LatLng(coordinateController.myCoordinate.getLatitude, coordinateController.myCoordinate.getLongitude);
            print(currentCoord.latitude);
            return GoogleMap(
              initialCameraPosition: CameraPosition(target: currentCoord, zoom: 17),
              myLocationEnabled: false,
              myLocationButtonEnabled: false,
              markers: markerController.markers.values.toSet(),
              onMapCreated: (controller) => _onMapCreated(controller),
              onTap: (latLng) {
                  markerController.resetPreviousMarkerToOriginal();
                  coordinateController.clearSelectedRecord();
                  markerController.turnOffIsWrited();
              },
            );
          }),
          Obx(() {
            if (markerController.isWriteVisible.value) {
              double height = MediaQuery.of(context).size.height;
              return Padding(
                padding: EdgeInsets.only(top: height * (1/2)),
                child: Align(
                  alignment: Alignment.centerRight,
                  // This shifts it up a bit
                  child: InkWell(
                    onTap: () {
                      if(coordinateController.selectedRecord != null) {
                        print('hello${coordinateController.selectedRecord!.boothId.value}');
                        Get.toNamed(Routes.TRACE, arguments: {'boothId': coordinateController.selectedRecord!.boothId.value});
                      }

                    },
                    child: Container(

                      margin: EdgeInsets.fromLTRB(0, 0, 21.0, 70.0),
                      // Adjust the margin so it's placed above the other icon
                      width: 40,
                      height: 40,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(40.0),
                        boxShadow: [
                          BoxShadow(
                            color: Colors.black.withOpacity(0.25),
                            spreadRadius: 1,
                            blurRadius: 5,
                            offset: Offset(0, 3),
                          ),
                        ],
                      ),
                      child: Icon(CupertinoIcons.pencil, color: Colors.black,),
                    ),
                  ),
                ),
              );
            }
            return SizedBox.shrink();
          }),

          Align(
            alignment: Alignment.bottomRight,
            child: InkWell(
              onTap: () async {
                await coordinateController.getMyCoordinate();
                Future.delayed(Duration(milliseconds: 500), () {
                  LatLng newLatLng = LatLng(coordinateController.myCoordinate.getLatitude, coordinateController.myCoordinate.getLongitude);
                  moveToNewLocation(newLatLng);
                });
              }
              , // addMarker('test', curLocation),
              child: Container(
                margin: EdgeInsets.fromLTRB(0, 0, 21.0, 30.0),
                width: 40,
                height: 40,
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(40.0),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withOpacity(0.25), // 그림자의 색상
                      spreadRadius: 1, // 그림자의 확장 반경
                      blurRadius: 5, // 그림자의 흐림 반경
                      offset: Offset(0, 3), // 그림자의 위치
                    ),
                  ],
                ),
                child: Icon(CupertinoIcons.scope, color: Colors.redAccent,),
              ),
            ),
          ),
          ScrollableBoothInfo(context),
          Align(
            alignment: Alignment.topCenter,
            child: Container(
              margin: EdgeInsets.only(top: 100),
              width: 150,
              child: OutlinedButton(
                  style: OutlinedButton.styleFrom(
                      shadowColor: Colors.black,
                      backgroundColor: Colors.white,
                      elevation: 3.0,
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(30.0)),
                      side: BorderSide(
                        color: Colors.white,
                        width: 2.0,
                      )
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Container(
                        margin: EdgeInsets.only(right: 10.0),
                        child: const Icon(
                          CupertinoIcons.arrow_clockwise,
                          color: ColorStyles.softBlack,
                          size: 17.0,
                        ),
                      ),
                      Text("이 지역 재검색", style: Theme.of(context).textTheme.labelSmall),
                    ],
                  ),
                  onPressed: () async {
                    await boothController.getNearBooths();
                    addBoothMarkers();
                  }
              ),
            )
          ),

        ]
    );
}


  Future<Uint8List> getBytesFromAsset(String path, int width) async {
    ByteData data = await rootBundle.load(path);
    ui.Codec codec = await ui.instantiateImageCodec(data.buffer.asUint8List(), targetWidth: width);
    ui.FrameInfo fi = await codec.getNextFrame();
    return (await fi.image.toByteData(format: ui.ImageByteFormat.png))!.buffer.asUint8List();
  }

  void addBoothMarkers() {
    for (var booth in boothController.booths) {
      print(booth.categoryImgSrc.toString());
      addMarker(booth.id.toString(),
          booth.title.toString(),
          booth.categoryImgSrc.toString(),
          booth.categoryName.toString(),
          LatLng(booth.location.latitude,
          booth.location.longitude),
          booth.isTimer.toString(),
          booth.isCurlingIron.toString(),
          booth.backgroundColorDiversity.toString()
      );
    }
  }

  Future<Uint8List> circleImageIcon(String assetPath, double size) async {
    final ByteData data = await rootBundle.load(assetPath);
    final Uint8List bytes = data.buffer.asUint8List();
    final Codec codec = await ui.instantiateImageCodec(bytes);
    final FrameInfo frameInfo = await codec.getNextFrame();
    final ui.Image img = frameInfo.image;

    final recorder = ui.PictureRecorder();
    final canvas = Canvas(recorder, Rect.fromPoints(Offset(0, 0), Offset(size, size)));

    final paint = Paint()..isAntiAlias = true;

    final center = Offset(size / 2, size / 2);
    final radius = size / 2;

    final path = Path()..addOval(Rect.fromCircle(center: center, radius: radius));
    canvas.clipPath(path);

    canvas.drawImageRect(
      img,
      Rect.fromLTWH(0, 0, img.width.toDouble(), img.height.toDouble()),
      Rect.fromLTWH(0, 0, size, size),
      paint,
    );

    final picture = recorder.endRecording();
    final circleImage = await picture.toImage(size.toInt(), size.toInt());
    final circleData = await circleImage.toByteData(format: ui.ImageByteFormat.png);
    return circleData!.buffer.asUint8List();
  }







  void addMarker(String id,
      String title,
      String categoryImgSrc,
      String categoryName,
      LatLng location,
      String isTimer,
      String isCurlingIron,
      String backgroundColorDiversity
      ) async {
    final Uint8List markerIcon = await circleImageIcon('assets/images/${categoryImgSrc}.png', 120.0);
    final largerMarkerIcon = await circleImageIcon('assets/images/${categoryImgSrc}.png', 170.0);

    final marker = Marker(
      markerId: MarkerId(id),
      position: location,
      infoWindow: InfoWindow(
          title: title,
          snippet: categoryName
      ),
      onTap: () async {
          markerController.handleMarkerTap(id, markerIcon, largerMarkerIcon);
          coordinateController.clearSelectedRecord();
          coordinateController.updateSelectedRecord(Record(boothId: int.parse(id), title: title, isTimer: isTimer, isCurlingIron: isCurlingIron, backgroundColorDiversity: backgroundColorDiversity));
          markerController.turnOnIsWrited();
          boothController.getInfoByBoothId(int.parse(id));
          traceController.getTraces();
      },
      icon: BitmapDescriptor.fromBytes(markerIcon),
    );
    markerController.addMarker(id, marker);
  }
}


