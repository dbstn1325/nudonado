import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:frontend/app/data/provider/member/home_api.dart';
import 'package:meta/meta.dart';

class HomeRepository {
  final HomeApiClient homeApiClient;

  HomeRepository({required this.homeApiClient});

  getAll() {
    return homeApiClient.getAll();
  }

  getId(id) {
    return homeApiClient.getId(id);
  }

}