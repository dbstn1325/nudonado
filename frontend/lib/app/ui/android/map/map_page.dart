import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:location/location.dart';


class MapPage extends StatefulWidget {
  const MapPage({Key? key}) : super(key: key);

  @override
  State<MapPage> createState() => _MapPageState();
}

class _MapPageState extends State<MapPage> {
  Completer<GoogleMapController> _controller = Completer();
  Map<String, Marker> _markers = {};
  LatLng curLocation = LatLng(35.15320067240981, 128.09971949420316);



  static final CameraPosition _kGooglePlex = CameraPosition(
    target: LatLng(35.15320067240981, 128.09971949420316),
    zoom: 14,
  );

  void _onMapCreated(GoogleMapController controller) {
    _controller.complete(controller);
  }

  void _currentLocation() async {
    final GoogleMapController controller = await _controller.future;
    var location = new Location();

    // Checking for permission
    PermissionStatus permission = await location.hasPermission();
    if (permission == PermissionStatus.denied ||
        permission == PermissionStatus.deniedForever) {
      permission = await location.requestPermission();
      if (permission != PermissionStatus.granted) {
        return;
      }
    }

    LocationData? currentLocation;
    try {
      currentLocation = await location.getLocation();
      print("Location: ${currentLocation.latitude}, ${currentLocation
          .longitude}"); // Print location
    } on Exception catch (e) {
      print("Error fetching location: $e");
      currentLocation = null;
    }

    if (currentLocation != null && currentLocation.latitude != null &&
        currentLocation.longitude != null) {}

  }


  List<Record> selectedRecords = [];

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Stack(
          children:[ GoogleMap(
            initialCameraPosition: _kGooglePlex,
            onMapCreated: _onMapCreated,
            myLocationEnabled: false,
            myLocationButtonEnabled: false,
            markers: _markers.values.toSet(),
            onTap: (latLng) {   // Here we reset the selectedRecord when we tap outside the marker
              setState(() {
                selectedRecords = [];
              });
            },
          ),
            Align(
              alignment: Alignment.bottomRight,
              child: InkWell(  // Wrapping the Container with InkWell
                onTap: () {
                  print("hi");
                  // _currentLocation();
                  addMarker('test', curLocation);
                },
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

          ]
      ),
    );
}



  void addMarker(String id, LatLng location) async {
    var markerIcon = await BitmapDescriptor.fromAssetImage(
      const ImageConfiguration(size: Size(2, 2)),
      'assets/images/four_picture.png',
    );

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