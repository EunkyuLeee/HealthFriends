import React, { useState, useEffect }  from 'react';
import  { useRef } from 'react';
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
    Alert
  } from 'react-native';
  import {
    Colors,
    DebugInstructions,
    Header,
    LearnMoreLinks,
    ReloadInstructions,
  } from 'react-native/Libraries/NewAppScreen';
import { StackNavigationProp } from '@react-navigation/stack';
import Toolbar from './Toolbar';
import { RootStackParamList } from './types';
import ExerciseRecordSet from './ExerciseRecordSet';
let stopwatchPath = require('./stopwatch.png');

type ExerciseRecordNavigationProp = StackNavigationProp<RootStackParamList, 'ExerciseRecord'>;

interface ExerciseRecordProps {
  navigation: ExerciseRecordNavigationProp;
}


  
const ExerciseRecord: React.FC<ExerciseRecordProps> = ({ navigation }) => {
    const [selectedOption, setSelectedOption] = useState<string | null>(null);
    const [milliseconds, setMilliseconds] = useState<number>(0);
    const [isRunning, setIsRunning] = useState<boolean>(false);
    const intervalRef = React.useRef<NodeJS.Timeout | null>(null);
    const [recordedMilliseconds, setRecordedMilliseconds] = useState<number>(0);
    const [recordedCounts, setRecordedCounts] = useState<number>(0);
    
    useEffect(() => {
      if (isRunning) {
          intervalRef.current = setInterval(() => {
              setMilliseconds(prevMilliseconds => prevMilliseconds + 10);
          }, 10);
      } else {
          if (intervalRef.current) {
              clearInterval(intervalRef.current);
          }
      }

      return () => {
          if (intervalRef.current) {
              clearInterval(intervalRef.current);
          }
      };
  }, [isRunning]);

  const handleStartStop = () => {
      setIsRunning(prevIsRunning => !prevIsRunning);
  };

  const handleReset = () => {
      setMilliseconds(0);
  };

  const handleRecord = () => {
    
      setRecordedMilliseconds(milliseconds);
 
};

const formatTime = (time: number) => {
  const minutes = Math.floor(time / 60000);
  const seconds = Math.floor((time % 60000) / 1000);
  const milliseconds = (time % 1000).toString().slice(0, 2);
  return (
      <Text>
          {minutes.toString().padStart(2, '0')}:{seconds.toString().padStart(2, '0')}.
          <Text style={styles.millisecondsText}>{milliseconds.padEnd(2, '0')}</Text>
      </Text>
  );
};

    return (
    <SafeAreaView>
        <ScrollView contentInsetAdjustmentBehavior="automatic">
            <View style={styles.container}>
                <Toolbar 
                  title="운동 기록"
                  backgroundColor="#799EBD"
                  onArrowPress={() => {navigation.goBack()}}
                  arrowPath={require('./Arrow.png')} 
                />
        <View style={styles.choiceContainer}>


      <TouchableOpacity
          style={[
          styles.optionBox,
          selectedOption === 'time' ? styles.selectedOption : {},
        ]}
        onPress={() => {
          setSelectedOption('time'); // 시간 선택 옵션 설정
          navigation.navigate('ExerciseRecord'); // ExerciseRecord 스크린으로 이동
      }}
      >
        <Text style={styles.optionText}>시간</Text>
      </TouchableOpacity>


      <TouchableOpacity
        style={[
          styles.optionBox,
          selectedOption === 'count' ? styles.selectedOption : {},
        ]}
        onPress={() => {
          setSelectedOption('count'); // 횟수/세트 선택 옵션 설정
          navigation.navigate('ExerciseRecordSet'); // ExerciseRecord 스크린으로 이동
      }}
      >
        <Text style={styles.optionText}>횟수/세트</Text>
         </TouchableOpacity>
         
        </View>

         <Image source={stopwatchPath} style={styles.stopwatchImage}/>
         

         <Text style={styles.timerText}>{formatTime(milliseconds)}</Text>
         <Text style={styles.walkText} >걷기(의) 시간을 기록합니다.</Text>
                    <TouchableOpacity onPress={handleStartStop} style={styles.button}>
                        <Text style={styles.buttonText}>{isRunning ? '멈춤' : '시작'}</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={handleReset} style={styles.button}>
                        <Text style={styles.buttonText}>초기화</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={handleRecord} style={styles.button}>
                        <Text style={styles.buttonText}>기록하기</Text>
                    </TouchableOpacity>
                    <Text style={styles.recordedText}>기록된 시간: {formatTime(recordedMilliseconds)}</Text>
                       
            </View>
        </ScrollView>
    </SafeAreaView>
            
  );
};


const styles = StyleSheet.create({
    container: {
      flex: 1,
    },
    choiceContainer: {
        flexDirection: 'row',
        justifyContent: 'center',
        marginTop: 50,
      },
      optionBox: {
        width: 100,
        height: 40,
        borderRadius: 8,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#D8E8F2',
        marginHorizontal: 10,
      },
      selectedOption: {
        backgroundColor: '#A1BFDD',
      },
      optionText: {
        fontWeight: 'bold',
        color: 'black',
        fontSize: 16,
      },
      stopwatchImage: {
        width: 160,
        height: 180,
        marginTop: 60,
        alignSelf: 'center'
      },
      walkText: {
        alignSelf: 'center',
        marginTop: 10,
        marginBottom: 20,
        fontWeight: 'bold',
        color: 'black',
        fontSize: 16, 
      },



      timerText: {
        fontSize: 30,
        fontWeight: 'bold',
        alignSelf: 'center',
        color: 'black',
        marginTop: 10,
    },

    millisecondsText: {
      fontSize: 20, 
      color: 'black',
    },

    
    button: {
        backgroundColor: '#A1BFDD',
        width: 120,
        height: 40,
        borderRadius: 8,
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 10,
        marginLeft: 140,
    },
    buttonText: {
        fontWeight: 'bold',
        color: 'black',
    },
    recordedText: {
      marginTop: 40,
  },

});
  

export default ExerciseRecord;