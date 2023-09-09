import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend/app/data/model/booth/booth.dart';
import 'package:frontend/app/data/provider/booth/booth_api.dart';
import 'package:frontend/app/data/provider/member/auth_api.dart';
import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:get/get.dart';
import 'package:meta/meta.dart';

class BoothRepository {
  final BoothApiClient boothApiClient;

  BoothRepository({required this.boothApiClient});

  postBooth(Booth booth) async{
    return await boothApiClient.postBooth(booth);
  }

  getNearBooths() async {
    return await boothApiClient.getNearBooths();
  }

  getInfoByBoothId(int boothId) async {
    return await boothApiClient.getInfoByBoothId(boothId);
  }


}