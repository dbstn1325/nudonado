import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:frontend/app/data/provider/trace/trace_api.dart';
import 'package:meta/meta.dart';

class TraceRepository {
  final TraceApiClient apiClient;

  TraceRepository({required this.apiClient});

  getAll() async {
    return await apiClient.getAll();
  }

}