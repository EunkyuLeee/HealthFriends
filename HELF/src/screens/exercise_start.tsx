import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createMaterialTopTabNavigator} from '@react-navigation/material-top-tabs';
import {View} from 'react-native'
import Tabs from '../components/Tab';
import { SafeAreaProvider } from 'react-native-safe-area-context';

export default function ExerciseStart() {
    return (
        <SafeAreaProvider>
          <Tabs/>
        </SafeAreaProvider>
      );
}