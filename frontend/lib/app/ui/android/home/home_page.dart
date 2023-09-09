import 'package:flutter/material.dart';
import 'package:frontend/app/controller/home/home_controller.dart';
import 'package:frontend/app/ui/android/booth/booth_page.dart';
import 'package:frontend/app/ui/android/booth/slide.dart';
import 'package:frontend/app/ui/android/map/map_page.dart';
import 'package:frontend/app/ui/android/widgets/home/loading_widget.dart';
import 'package:frontend/app/ui/android/login/login_page.dart';
import 'package:get/get.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}
class _HomePageState extends State<HomePage> {
  int _selectedIndex = 0;
  static const TextStyle optionStyle =
  TextStyle(fontSize: 20, fontWeight: FontWeight.bold);
  static final List<Widget> _widgetOptions = <Widget>[
    MapPage(),
    Placeholder(), // Temporary placeholder for the second index
    Text(
      'Index 2: School',
      style: optionStyle,
    ),
  ];

  void _onItemTapped(int index) {
    switch (index) {
      case 0:
        _showHome();
        break;
      case 1:
        _showBoothPage();
        break;
      case 2:
        _showSchoolInfo();
        break;
    }
  }

  void _showHome() {
    setState(() {
      _selectedIndex = 0;
    });
  }

  void _showBoothPage() {
    Navigator.of(context).push(SlideUpRoute(page: BoothPage()));
  }




  void _showSchoolInfo() {
    setState(() {
      _selectedIndex = 2;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home_filled),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.mode_edit_outline_outlined),
            label: '제보하기',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person_outline),
            label: '나의 정보',
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.redAccent,
        onTap: _onItemTapped,
      ),
    );
  }
}
