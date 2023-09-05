import 'package:frontend/app/bindings/booth_binding.dart';
import 'package:frontend/app/bindings/coordinate_binding.dart';
import 'package:frontend/app/bindings/home_binding.dart';
import 'package:get/get_instance/src/bindings_interface.dart';

class MainBinding extends Bindings {
  @override
  void dependencies() {
    HomeBinding().dependencies();
    CoordinateBinding().dependencies();
    BoothBinding().dependencies();
  }
}
