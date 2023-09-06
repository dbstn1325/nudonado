import 'package:flutter/material.dart';
import 'package:frontend/app/data/model/booth/booth.dart';
import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/auth/auth_repository.dart';
import 'package:frontend/app/data/repository/booth/booth_repository.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:frontend/app/ui/android/widgets/auth/expired_token_dialog.dart';
import 'package:get/get.dart';

class BoothController extends GetxController {
  final BoothRepository boothRepository;
  BoothController({required this.boothRepository});

  final Booth booth = Booth();


  Future<void> createBooth() async {
    await boothRepository.postBooth(booth);
  }

}