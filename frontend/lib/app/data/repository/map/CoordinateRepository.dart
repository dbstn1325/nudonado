import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend/app/data/provider/map/coordinate_api.dart';
import 'package:frontend/app/data/provider/member/auth_api.dart';

class CoordinateRepository {
  final CoordinateClient coordinateClient;

  CoordinateRepository({required this.coordinateClient});

  me() async{
    return await coordinateClient.locateMe();
  }

}