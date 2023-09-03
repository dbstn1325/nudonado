import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:meta/meta.dart';

class MemberRepository {
  final MemberApiClient memberApiClient;

  MemberRepository({required this.memberApiClient});

  getAll() {
    return memberApiClient.getAll();
  }

  getId(id) {
    return memberApiClient.getId(id);
  }

}