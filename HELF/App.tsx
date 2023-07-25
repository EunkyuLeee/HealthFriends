/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import { SafeAreaView, ScrollView, StatusBar, StyleSheet, Text, useColorScheme, View } from 'react-native';
import {MD2Colors as Colors} from 'react-native-paper'
import TopBar from './src/components/TopBar';

function App(): JSX.Element {

  return (
    <SafeAreaView>
      <TopBar/>
    </SafeAreaView>
  );
}

export default App;
