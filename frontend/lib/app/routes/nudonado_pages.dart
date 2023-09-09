import 'package:frontend/app/bindings/auth_binding.dart';
import 'package:frontend/app/bindings/coordinate_binding.dart';
import 'package:frontend/app/bindings/home_binding.dart';
import 'package:frontend/app/bindings/main_binding.dart';
import 'package:frontend/app/bindings/map/map_binding.dart';
import 'package:frontend/app/bindings/trace_controller.dart';
import 'package:frontend/app/ui/android/home/home_page.dart';
import 'package:frontend/app/ui/android/auth/login_page.dart';
import 'package:frontend/app/ui/android/map/map_page.dart';
import 'package:frontend/app/ui/android/map/trace_write_page.dart';
import 'package:get/get.dart';
part './nudonado_routes.dart';

class AppPages {
  static final pages = [
    GetPage(
        name: Routes.HOME, page: () => HomePage(), binding: MainBinding()
    ),
    GetPage(
        name: Routes.LOGIN, page: () => LoginPage(), binding: AuthBinding()
    ),
    GetPage(
        name: Routes.MAP,
        page: () => MapPage(),
        binding: MapBinding()),
    GetPage(name: Routes.TRACE, page: () => TraceWritePage(), binding: TraceBinding())
  ];
}