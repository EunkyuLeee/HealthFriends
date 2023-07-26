import React from 'react';
import type {PropsWithChildren} from 'react';
import { SafeAreaView, ScrollView, StatusBar, StyleSheet, Text, useColorScheme, View } from 'react-native';
import {MD2Colors as Colors} from 'react-native-paper'
import {TopBar} from './src/components/TopBar';
import * as Data from './src/data';

export const User = Data.createUserInfo()

function App(): JSX.Element {

  return (
    <SafeAreaView>
      <TopBar icon_name = 'menu' page_name='HF' User={User} needUser={true}/>
    </SafeAreaView>
  );
}

export default App;
