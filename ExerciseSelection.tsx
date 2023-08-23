import React, { useState } from 'react';
import { StackNavigationProp } from '@react-navigation/stack';
import type {PropsWithChildren} from 'react';
import Toolbar from './Toolbar';
import ExerciseRecord from './ExerciseRecord';
import { RootStackParamList } from './types';
import {
  Image,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableOpacity, 
  useColorScheme,
  View,
  Button,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

let dumbbellPath = require('./dumbbell.png');
let PolygonPath = require('./Polygon.png');

type SectionProps = PropsWithChildren<{
  title: string;
}>;

  
  type ExerciseSelectionNavigationProp = StackNavigationProp<RootStackParamList, 'ExerciseSelection'>;
  
  interface ExerciseSelectionProps {
    navigation: ExerciseSelectionNavigationProp;
  }
  
  const ExerciseSelection: React.FC<ExerciseSelectionProps> = ({ navigation }) => {

    const [exerciseOptionsVisible, setExerciseOptionsVisible] = useState(false);
        return (
          <SafeAreaView >
            <ScrollView
              contentInsetAdjustmentBehavior="automatic">
              
              <View style={styles.container}>
              <Toolbar
                  title="운동 선택"
                  backgroundColor="#799EBD"
                  onArrowPress={() => {
                    //  화살표 뒤로 가기 
                  }}
                  arrowPath={require('./Arrow.png')} 
               />
      
              <Image source={dumbbellPath} style={styles.dumbbellImage} />
      
              <View style={styles.selectionBox}>
              <Text style={styles.selectionText}>운동 종류를 선택하세요</Text>
                  <View style={styles.selectionContent}>
                
                    <TouchableOpacity
                      onPress={() => setExerciseOptionsVisible(!exerciseOptionsVisible)}
                      style={styles.imageButton} >
                      <Image source={PolygonPath} style={styles.exerciseImage} />
                    </TouchableOpacity>
                    
                  </View>
                </View>
      
                {exerciseOptionsVisible && (
                  <View style={styles.exerciseOptions}>
                    <Text style={styles.exerciseOption}>걷기</Text>
                    <Text style={styles.exerciseOption}>하체</Text>
                    {/* 다른 운동 종류 추가  */}
                  </View>
                )}
      
                <Text style={styles.orText} > 또는 </Text>
      
               <TouchableOpacity>
               <View style={styles.plusSelectionBox}>
                  <Text style={styles.plusSelectionText}> 새 운동을 추가하세요 </Text>
                </View>
               </TouchableOpacity>
      
               <TouchableOpacity onPress={() => navigation.navigate('ExerciseRecord')}>
                <View style={styles.startBox}>
                <Text style={styles.startText}>위 운동으로 시작</Text>
                </View>
                </TouchableOpacity>
      
              </View>
      
            </ScrollView> 
          </SafeAreaView>
    );
  };

  const styles = StyleSheet.create({
    container: {
      flex: 1,
    },
    
    dumbbellImage: {
      width: 160, 
      height: 180, 
      marginTop: 140, 
      alignSelf: 'center', 
    },
  
    selectionBox: {
      backgroundColor: '#D8E8F2',
      padding: 10,
      margin: 10,
      marginLeft: 75,
      marginTop: 50,
      flexDirection: 'row',
      alignItems: 'center',
      width: 230,
      height: 40,
    },
  
    selectionContent: {
      marginLeft: 10, 
    },
  
    selectionText: {
      color: 'black',
      fontWeight: '900',
      flex: 1,
      fontSize: 14,
      marginLeft: 20,
    },
  
    imageButton: {
      justifyContent: 'center',
      alignItems: 'center',
    },
  
    exerciseImage: {
      width: 30,
      height: 25,
    },
  
  
    exerciseOptions: {
      marginTop: 5,
      marginLeft: 250,
    },
    exerciseOption: {
      fontSize: 15,
      marginVertical: 5,
    },
  
    orText: {
      color: 'black',
      fontWeight: '900',
      flex: 1,
      fontSize: 16,
      marginLeft: 170,
    },
  
    plusSelectionBox: {
      backgroundColor: '#D8E8F2',
      padding: 10,
      margin: 10,
      marginLeft: 100,
      marginTop: 10,
      flexDirection: 'row',
      alignItems: 'center',
      width: 180,
      height: 40,
    },
  
    plusSelectionText: {
      color: 'black',
      fontWeight: '900',
      flex: 1,
      fontSize: 14,
      marginLeft: 15,
      marginBottom: 2,
    },
  
    startBox: {
      backgroundColor: '#A1BFDD',
      padding: 10,
      margin: 10,
      marginLeft: 125,
      marginTop: 40,
      flexDirection: 'row',
      alignItems: 'center',
      width: 130,
      height: 40,
      borderRadius:9,
    },
  
    startText: {
      color: 'black',
      fontWeight: '900',
      flex: 1,
      fontSize: 14,
      marginLeft: 7,
      marginBottom: 2,
    },
  });
  
export default  ExerciseSelection;

