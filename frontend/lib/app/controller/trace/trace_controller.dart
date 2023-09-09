import 'package:frontend/app/data/model/trace/trace_create_request.dart';
import 'package:frontend/app/data/model/trace/trace_info.dart';
import 'package:frontend/app/data/model/trace/trace_model.dart';
import 'package:frontend/app/data/repository/trace/trace_repository.dart';
import 'package:get/get.dart';

class TraceController extends GetxController {
  final TraceRepository repository;
  TraceController({required this.repository});

  final TraceInfo traceInfo = TraceInfo();
  final traces = <Trace>[].obs;

  createTrace(int boothId) async {
    return await repository.postTrace(TraceCreateRequest(boothId: boothId, memo: traceInfo.memo.value));
  }

  getTraces() async{
    List<Trace> result = await repository.getAll();
    setTraces(result);
  }

  void setTraces(List<Trace> newTraces) {
    traces.clear();
    traces.addAll(newTraces);
  }

}