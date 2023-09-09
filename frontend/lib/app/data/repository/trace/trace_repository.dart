import 'package:frontend/app/data/model/trace/trace_create_request.dart';
import 'package:frontend/app/data/model/trace/trace_info.dart';
import 'package:frontend/app/data/provider/member/get_member_api.dart';
import 'package:frontend/app/data/provider/trace/trace_api.dart';
import 'package:meta/meta.dart';

class TraceRepository {
  final TraceApiClient apiClient;

  TraceRepository({required this.apiClient});

  postTrace(TraceCreateRequest request) async {
    return await apiClient.postTrace(request);
  }

  getAll() async {
    return await apiClient.getAll();
  }

}