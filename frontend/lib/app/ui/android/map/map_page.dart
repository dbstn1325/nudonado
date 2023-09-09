import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/controller/map/marker_controller.dart';
import 'package:frontend/app/data/provider/map/google_map_service.dart';
import 'package:frontend/app/ui/android/map/widget/Record.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'dart:ui' as ui;

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

                      // boothController.get
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
            child: InkWell(  // Wrapping the Container with InkWell
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
          Obx(() {
            if (coordinateController.selectedRecord != null) {
              return Align(
                alignment: Alignment.bottomLeft,
                child: Container(
                  height: 130.0,
                  width: MediaQuery.of(context).size.width * 0.6,
                  margin: EdgeInsets.only(bottom: 20.0, left: 10.0, right: 10.0),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(40.0),
                    boxShadow: [
                    BoxShadow(
                    color: Colors.black12,
                    spreadRadius: 0,
                    blurRadius: 5,
                    offset: Offset(0, 3),
                  ),
                  ],),
                  child: Padding(
                    padding: const EdgeInsets.only(left: 15.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text("매장 명 :${coordinateController.selectedRecord!.title}"),
                        Text("타이머 유무 : ${coordinateController.selectedRecord!.isTimer}"),
                        Text("고데기 유무 : ${coordinateController.selectedRecord!.isCurlingIron}"),
                        Text("배경색 다양 정도 : ${coordinateController.selectedRecord!.backgroundColorDiversity}")
                      ],
                    ),

                  ),
                ),
              );
            }
            return SizedBox.shrink();
          }),


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
                        child: Icon(
                          CupertinoIcons.arrow_clockwise,
                          color: Colors.black,
                          size: 17.0,
                        ),
                      ),
                      Text("이 지역 재검색", style: TextStyle(color: Colors.black),),
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


  void addMarker(String id,
      String title,
      String categoryImgSrc,
      String categoryName,
      LatLng location,
      String isTimer,
      String isCurlingIron,
      String backgroundColorDiversity
      ) async {
    print(categoryImgSrc);
    final Uint8List markerIcon = await getBytesFromAsset('assets/images/${categoryImgSrc}.png', 200);
    final marker = Marker(
      markerId: MarkerId(id),
      position: location,
      infoWindow: InfoWindow(
          title: title,
          snippet: categoryName
      ),
      onTap: () {   // Here we set the selectedRecord when a marker is tapped
          coordinateController.clearSelectedRecord();
          coordinateController.updateSelectedRecord(Record(boothId: int.parse(id), title: title, isTimer: isTimer, isCurlingIron: isCurlingIron, backgroundColorDiversity: backgroundColorDiversity));
          // coordinateController.selectedRecords.value.map((element) => print('hello${element.title}'));
          markerController.turnOnIsWrited();
          print(markerController.isWriteVisible.value);
          boothController.getInfoByBoothId(int.parse(id));
      },
      icon: BitmapDescriptor.fromBytes(markerIcon),
    );


    markerController.addMarker(id, marker);




  }
}


