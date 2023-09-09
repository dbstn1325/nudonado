import 'package:frontend/app/data/model/trace/trace_model.dart';
import 'package:frontend/app/data/repository/trace/trace_repository.dart';
import 'package:get/get.dart';

class TraceController extends GetxController {
  final TraceRepository repository;
  TraceController({required this.repository});

  final traces = <Trace>[].obs;

  getTraces() async{
    List<Trace> result = await repository.getAll();
    setTraces(result);
  }

  void setTraces(List<Trace> newTraces) {
    traces.clear();
    traces.addAll(newTraces);
  }

}