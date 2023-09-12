
import 'package:flutter/material.dart';
import 'package:frontend/app/ui/android/global/theme/color_styles.dart';

ThemeData globalThemeData = ThemeData(
  appBarTheme: const AppBarTheme(
    backgroundColor: Colors.white,
    titleTextStyle: TextStyle(
        fontSize: 17,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.bold
    )
    ),
    primaryColor: ColorStyles.normalMainColor,
    textTheme: const TextTheme(
      displayMedium: TextStyle(
        fontSize: 34,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.w500
      ),
      headlineLarge: TextStyle(
        fontSize: 20.0,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.bold,
      ),
      headlineMedium: TextStyle(
        fontSize: 17.5,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.bold,
      ),
      headlineSmall: TextStyle(
        fontSize: 14.0,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.w500,
      ),
      titleLarge: TextStyle(
        fontSize: 18.0,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.bold,
      ),
      titleMedium: TextStyle(
        fontSize: 16.0,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.w500,
      ),
      bodyLarge: TextStyle(
        fontSize: 16.0,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.w400,
      ),
      bodyMedium: TextStyle(
          fontSize: 14.0,
          color: ColorStyles.softBlack,
        fontWeight: FontWeight.w400,
      ),
      labelLarge: TextStyle(
          fontSize: 17.0,
          color: ColorStyles.softWhite,
          fontWeight: FontWeight.bold,
      ),
      labelMedium: TextStyle(
        fontSize: 15.0,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.w500,
      ),
      labelSmall: TextStyle(
        fontSize: 13.5,
        color: ColorStyles.softBlack,
        fontWeight: FontWeight.w500,
      ),

  )
);