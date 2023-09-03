import 'package:frontend/app/bindings/auth_binding.dart';
import 'package:frontend/app/bindings/home_binding.dart';
import 'package:frontend/app/ui/android/home/home_page.dart';
import 'package:frontend/login_screen.dart';
import 'package:get/get.dart';
part './nudonado_routes.dart';

class AppPages {
  static final pages = [
    GetPage(
        name: Routes.INITIAL, page: () => HomePage(), binding: HomeBinding()
    ),
    GetPage(
        name: Routes.LOGIN, page: () => LoginScreen(), binding: AuthBinding()
    ),
  ];
}