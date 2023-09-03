import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/home_repository.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:frontend/app/routes/nudonado_pages.dart';
import 'package:get/get.dart';

class HomeController extends GetxController {

  final HomeRepository homeRepository;
  HomeController({required this.homeRepository});

  final _post = Member(id: 0, email: '', displayName: '', profileImage: '').obs;
  get post => this._post.value;
  set post(value) => this._post.value = value;


  getId(int id) async {
    var fetchedMember = await homeRepository.getId(id);
    if (fetchedMember != null) {
      post = fetchedMember;
    }
  }

  details(post){
    this.post = post;
    Get.toNamed(Routes.DETAILS);
  }
}