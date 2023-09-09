import 'package:frontend/app/controller/trace/trace_controller.dart';
import 'package:frontend/app/data/provider/trace/trace_api.dart';
import 'package:frontend/app/data/repository/trace/trace_repository.dart';
import 'package:http/http.dart' as http;
import 'package:get/get.dart';

class TraceBinding implements Bindings {
  @override
  void dependencies() {
    Get.lazyPut<TraceController>(() {
      return TraceController(
          repository:
          TraceRepository(apiClient: TraceApiClient(httpClient: http.Client())));
    });
  }
}