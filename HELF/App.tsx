import React from 'react';;
import { SafeAreaView, Text, View } from 'react-native';
import * as Data from './src/data';
import StartScreen from './src/screens/start_screen';
import { NavigationContainer } from '@react-navigation/native';
import Navigator from './src/components/Navigator';



function App(): JSX.Element {

  return (
    <NavigationContainer>
        <Navigator/>
    </NavigationContainer>
  );
}

export default App;