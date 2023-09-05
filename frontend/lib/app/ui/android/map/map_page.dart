import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:frontend/app/controller/auth/auth_controller.dart';
import 'package:frontend/app/controller/booth/booth_controller.dart';
import 'package:frontend/app/controller/map/coordinate_controller.dart';
import 'package:get/get.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class MapPage extends StatefulWidget {
  const MapPage({Key? key}) : super(key: key);

  @override
  State<MapPage> createState() => _MapPageState();
}

class _MapPageState extends State<MapPage> {
  Completer<GoogleMapController> _controller = Completer();
  GoogleMapController? mapController;
  final controller = Get.find<CoordinateController>();
  final boothController = Get.find<BoothController>();
  Map<String, Marker> _markers = {};
  LatLng curLocation = LatLng(35.15320067240981, 128.09971949420316);
  List<Record> selectedRecords = [];

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

    return Scaffold(
      body: Stack(
          children:[
            Obx(() {
              LatLng currentCoord = LatLng(controller.myCoordinate.getLatitude, controller.myCoordinate.getLongitude);
              print(currentCoord.latitude);
              return GoogleMap(
                initialCameraPosition: CameraPosition(target: currentCoord, zoom: 17),
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
            }),
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
                  margin: EdgeInsets.fromLTRB(0, 0, 14.0, 180.0),
                  width: 60,
                  height: 60,
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.circular(40.0),
                  ),
                  child: Icon(CupertinoIcons.scope),
                ),
              ),
            ),
            if (selectedRecords.isNotEmpty)
              Align(
                alignment: Alignment.bottomCenter.add(Alignment(0, -0.1)),  // This shifts it up a bit
                child: Container(
                  height: 130.0,
                  child: ListView.builder(
                    scrollDirection: Axis.horizontal,
                    itemCount: selectedRecords.length,
                    itemBuilder: (BuildContext context, int index) {
                      return Container(
                        width: MediaQuery.of(context).size.width * 0.6,
                        margin: EdgeInsets.only(bottom: 20.0, left: 10.0, right: 10.0),
                        decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(40.0),  // Radius for each Record object
                          boxShadow: [  // Optional: To give a slight elevation effect
                            BoxShadow(
                              color: Colors.black12,
                              spreadRadius: 0,
                              blurRadius: 5,
                              offset: Offset(0, 3),
                            ),
                          ],
                        ),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text("매장 명: ${selectedRecords[index].title}"),
                            Text("고데기 유무: ${selectedRecords[index].availability}")
                          ],
                        ),
                      );
                    },
                  ),
                ),
              ),
            Align(
              alignment: Alignment.bottomCenter,
              child: ElevatedButton(
                child: Text("마커 고정"),
                onPressed: () async {
                  if (mapController != null) {
                    LatLngBounds bounds = await mapController!.getVisibleRegion();
                    LatLng centerLatLng = LatLng(
                      (bounds.northeast.latitude + bounds.southwest.latitude) / 2,
                      (bounds.northeast.longitude + bounds.southwest.longitude) / 2,
                    );

                    addMarker('center_marker', centerLatLng);
                  }
                },
              ),
            ),

            Align(
              alignment: Alignment.center,
              child: Container(
                width: 50,
                height: 50,
                margin: EdgeInsets.only(bottom: 40),
                child: Image.asset("assets/images/haruflim_marker.png"),
              ),
            ),

            Align(
              alignment: Alignment.center,
              child: ElevatedButton(
                  child: Text("부스 생성"),
                  onPressed: () async {
                    await boothController.createBooth();
                  }
              )
            ),


          ]
      ),
    );
}




  void addMarker(String id, LatLng location) async {
    final ImageConfiguration imageConfiguration = ImageConfiguration(size: Size(20, 20)); // Change 48x48 to your desired size
    var markerIcon = await BitmapDescriptor.fromAssetImage(
      imageConfiguration,
      'assets/images/haruflim_marker.png',
    );

    print("요청 위치!!");
    print(location.latitude);
    print(location.longitude);

    var marker = Marker(
      markerId: MarkerId(id),
      position: location,
      infoWindow: const InfoWindow(
          title: '인생 네컷',
          snippet: '친구들과 함께 사진을 찍어용'
      ),
      onTap: () {   // Here we set the selectedRecord when a marker is tapped
        setState(() {
          selectedRecords!.add(Record(title: "인생네컷 강남지점", availability: "N"));
          selectedRecords!.add(Record(title: "인생네컷 강남지점", availability: "N"));
        });
      },
      icon: markerIcon,
    );

    setState(() {
      _markers[id] = marker;
    });



  }
}

class Record {
  final String title;
  final String availability;

  Record({required this.title, required this.availability});
}

