import 'package:flutter/material.dart';
import 'package:frontend/app/controller/home/home_controller.dart';
import 'package:frontend/app/ui/android/widgets/home/loading_widget.dart';
import 'package:frontend/app/ui/android/login/login_page.dart';
import 'package:get/get.dart';

class HomePage extends GetView<HomeController> {
  @override
  Widget build(BuildContext context) {
    final controller = Get.find<HomeController>();
    return Scaffold(
      body: Text("Hi"),
    );
    // return Scaffold(
    //   body: FutureBuilder(
    //     future: controller.getId(1),
    //     builder: (context, snapshot) {
    //       if (snapshot.connectionState == ConnectionState.waiting) {
    //         return LoadingWidget();
    //       }
    //
    //
    //       return Center(
    //         child: Column(
    //           mainAxisAlignment: MainAxisAlignment.center,
    //           children: [
    //             Text(controller.post.displayName ?? 'a'),
    //             Text(controller.post.email ?? 'b'),
    //             Text(controller.post.profileImage),
    //           ],
    //         ),
    //       );
    //     },
    //   ),
    // );
  }
}
