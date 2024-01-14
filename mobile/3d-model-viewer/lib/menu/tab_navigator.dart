
import 'package:flutter/material.dart';
import '../screens/home/Home.dart';
import '../screens/profil/Profil.dart';
import '../screens/saved/savedScreen.dart';
import 'tab_item.dart';






class TabNavigator extends StatelessWidget {
  TabNavigator({required this.navigatorKey, required this.tabItem});
  final GlobalKey<NavigatorState>? navigatorKey;
  final TabItem tabItem;

  @override
  Widget build(BuildContext context) {

    late Widget child ;
    if(tabItem == TabItem.searche)
      child = Home();
    else if(tabItem == TabItem.saved)
      child = SavedScreen();
    else if(tabItem == TabItem.profil)
      child = Profil();
    return Navigator(
      key: navigatorKey,
      onGenerateRoute: (routeSettings) {
        return MaterialPageRoute(
          builder: (context) => child
        );
      },
    );
  }
}
