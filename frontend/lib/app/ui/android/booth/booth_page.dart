import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:frontend/app/ui/android/booth/booth_write_page.dart';

import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class BoothPage extends StatefulWidget {


  @override
  State<BoothPage> createState() => _BoothPageState();
}

class _BoothPageState extends State<BoothPage> {
  Completer<GoogleMapController> _controller = Completer();
  GoogleMapController? mapController;

  final controller = Get.find<CoordinateController>();
  final boothController = Get.find<BoothController>();


  @override
  Widget build(BuildContext context) {

    void _onMapCreated(GoogleMapController controller) {
      mapController = controller;
      _controller.complete(controller);
    }

    void moveToNewLocation(LatLng newLatLng) {
      mapController?.animateCamera(
        CameraUpdate.newCameraPosition(CameraPosition(target: newLatLng, zoom: 17)),
      );
    }

    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Expanded(
            flex: 5,
              child: Stack(
                children: [
                  Obx(() {
                    LatLng currentCoord = LatLng(controller.myCoordinate.getLatitude, controller.myCoordinate.getLongitude);
                    print(currentCoord.latitude);
                    return GoogleMap(
                      onCameraIdle: () async {
                        // 카메라 움직임이 멈췄을 때의 로직.
                        // 일반적으로 이 부분에서 중앙의 위치를 최종적으로 업데이트하거나 다른 로직을 수행합니다.
                        if (mapController != null) {
                          print("hi");
                          LatLngBounds bounds = await mapController!.getVisibleRegion();
                          LatLng centerLatLng = LatLng(
                            (bounds.northeast.latitude + bounds.southwest.latitude) / 2,
                            (bounds.northeast.longitude + bounds.southwest.longitude) / 2,
                          );

                          print(centerLatLng.latitude);
                          print(centerLatLng.longitude);
                          controller.updateBoothCoordinate(centerLatLng.latitude, centerLatLng.longitude);
                        }
                      },
                      initialCameraPosition: CameraPosition(target: currentCoord, zoom: 17),
                      myLocationEnabled: false,
                      myLocationButtonEnabled: false,
                      // markers: _markers.values.toSet(),
                      onMapCreated: (controller) => _onMapCreated(controller),
                      onTap: (latLng) {
                        // setState(() {
                        //   selectedRecords = [];
                        // });
                      },
                    );
                  }),
        Positioned(
          top: 0,
          left: 0,
          right: 0,
          child: AppBar(
            backgroundColor: Colors.transparent, // AppBar를 투명하게 설정
            elevation: 0, // 그림자를 제거
            title:
              Text(
              "부스 제보",
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 17,
                  fontWeight: FontWeight.w600
              ),
              ),

            leading: IconButton(
              icon: Icon(Icons.close, color: Colors.black),
              onPressed: () => Navigator.of(context).pop(),
            ),
          ),
        ),

                  Align(
                    alignment: Alignment.bottomRight,
                    child: InkWell(  // Wrapping the Container with InkWell
                      onTap: () async {
                        await controller.getMyCoordinate();
                        Future.delayed(Duration(milliseconds: 500), () {
                          LatLng newLatLng = LatLng(controller.myCoordinate.getLatitude, controller.myCoordinate.getLongitude);
                          moveToNewLocation(newLatLng);
                        });
                      }
                      , // addMarker('test', curLocation),
                      child: Container(
                        margin: EdgeInsets.fromLTRB(0, 0, 24.0, 30.0),
                        width: 50,
                        height: 50,
                        decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(40.0),
                        ),
                        child: Icon(CupertinoIcons.scope),
                      ),
                    ),
                  ),
                  Align(
                    alignment: Alignment.center,
                    child: Container(
                      width: 50,
                      height: 50,
                      // margin: EdgeInsets.only(bottom: 40),
                      child: Image.asset("assets/images/haruflim_marker.png"),
                    ),
                  ),
                ],
              ),


      ),

          Expanded(
            flex: 2,
            child: Container(
              color: Colors.white24,
              child: Column(
                children: [
                  Text("부스 위치는 바로 여기!"),
                  Obx(() {
                    return Text("위도: ${controller.myCoordinate.getLatitude}");
                  }),
                  Obx(() {
                    return Text("경도: ${controller.myCoordinate.getLongitude}");
                  }),

                  CupertinoButton(
                    onPressed: () async {
                      if (mapController != null) {
                        LatLngBounds bounds = await mapController!.getVisibleRegion();
                        LatLng centerLatLng = LatLng(
                          (bounds.northeast.latitude + bounds.southwest.latitude) / 2,
                          (bounds.northeast.longitude + bounds.southwest.longitude) / 2,
                        );

                        print(centerLatLng.latitude);
                        print(centerLatLng.longitude);
                        controller.updateBoothCoordinate(centerLatLng.latitude, centerLatLng.longitude);

                        // 여기서 다음 페이지로 이동
                        Get.to(() => BoothWritePage());

                        // addMarker('center_marker', centerLatLng);
                      }
                    },
                    child: Container(
                      margin: EdgeInsets.fromLTRB(30, 0, 30, 30),
                      constraints: BoxConstraints(
                        minWidth: MediaQuery.of(context).size.width,
                        minHeight: 40,
                      ),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10.0),
                        // color: const Color(0xffFFD596),
                        color: Colors.orange
                      ),
                      child: Center(
                        child: Text(
                          "이 위치로 주소 설정",
                          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
                        ),
                      ),
                    )

                    ),

                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
