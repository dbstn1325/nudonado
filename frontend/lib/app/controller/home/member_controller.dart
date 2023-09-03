import 'package:frontend/app/data/model/member.dart';
import 'package:frontend/app/data/repository/member_repository.dart';
import 'package:get/get.dart';
import 'package:meta/meta.dart';

class MemberController extends GetxController {

  final MemberRepository memberRepository;
  MemberController({required this.memberRepository}) : assert(memberRepository != null);


  // final _postsList = List<Member>().obs;
  // get postList => this._postsList.value;
  // set postList(value) => this._postsList.value = value;
  //
  //
  // final _post = Member().obs;
  // get post => this._post.value;
  // set post(value) => this._post.value = value;
  //
  // getAll(){
  //   repository.getAll().then( (data){ this.postList = data; } );
  // }

  adicionar(post){

  }
  //dismissible
  excluir(id){

  }
  //dismissible
  editar(){

  }
  details(post){
    this.post = post;
    Get.toNamed(Routes.DETAILS);
  }
}