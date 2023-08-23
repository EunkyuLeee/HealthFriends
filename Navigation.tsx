import React from 'react';
import { createStackNavigator } from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';
import ExerciseSelection from './ExerciseSelection';
import ExerciseRecord from './ExerciseRecord';
import ExerciseRecordSet from './ExerciseRecordSet';
import { RootStackParamList } from './types';

const Stack = createStackNavigator();

const Navigation = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="ExerciseSelection">
        <Stack.Screen name="ExerciseSelection" component={ExerciseSelection} options={{headerShown: false}} />
        <Stack.Screen name="ExerciseRecord" component={ExerciseRecord} options={{headerShown: false}}/>
        <Stack.Screen name="ExerciseRecordSet" component={ExerciseRecordSet}  options={{headerShown: false}} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default Navigation;