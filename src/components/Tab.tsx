import React from 'react';
import Timer from '../screens/Timer';
import Input from '../screens/Input';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import {NavigationContainer} from '@react-navigation/native';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';

const Tab = createMaterialTopTabNavigator();

export default function Tabs() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Timer" component={Timer} />
      <Tab.Screen name="Input" component={Input} />
    </Tab.Navigator>
  );
}