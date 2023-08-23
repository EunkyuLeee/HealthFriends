import React, { useState } from 'react';
import { SafeAreaView, ScrollView, View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';
import { StackNavigationProp } from '@react-navigation/stack';
import { RootStackParamList } from './types';
import Toolbar from './Toolbar';

type ExerciseRecordSetNavigationProp = StackNavigationProp<RootStackParamList, 'ExerciseRecordSet'>;

interface ExerciseRecordSetProps {
    navigation: ExerciseRecordSetNavigationProp;
}


const ExerciseRecordSet: React.FC<ExerciseRecordSetProps> = ({ navigation }) => {
    const [selectedOption, setSelectedOption] = useState<string | null>(null);
    const [recordedCounts, setRecordedCounts] = useState<number | undefined>(undefined);
    const [recordedSets, setRecordedSets] = useState<number | undefined>(undefined);
    const [recordedWeight, setRecordedWeight] = useState<number | undefined>(undefined);

    const handleRecord = () => {
        // 기록된 값들을 사용하여 원하는 처리 수행
        console.log('기록된 횟수:', recordedCounts);
        console.log('기록된 세트 수:', recordedSets);
        console.log('기록된 중량:', recordedWeight);
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

                <View style={styles.buttonContainer}>
                        <TouchableOpacity
                            style={[
                                styles.optionBox,
                                selectedOption === 'time' ? styles.selectedOption : {},
                            ]}
                            onPress={() => {
                                setSelectedOption('time');
                                navigation.navigate('ExerciseRecord');
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
                                setSelectedOption('count');
                                navigation.navigate('ExerciseRecordSet');
                            }}
                        >
                            <Text style={styles.optionText}>횟수/세트</Text>
                        </TouchableOpacity>
                    </View> 

                    <Text style={styles.setText} >몇 회씩 몇 세트를 {'\n'}했는지 기록하세요 :</Text>

            
            
            <View style={styles.recordcontainer}>
            <Text style={styles.label}>횟수 기록</Text>
            <TextInput
                style={styles.input}
                placeholder="횟수를 입력하세요"
                keyboardType="numeric"
                onChangeText={text => setRecordedCounts(parseInt(text))}
            />

            <Text style={styles.label}>세트 수 기록</Text>
            <TextInput
                style={styles.input}
                placeholder="세트 수를 입력하세요"
                keyboardType="numeric"
                onChangeText={text => setRecordedSets(parseInt(text))}
            />

            <Text style={styles.label}>중량 기록(선택)</Text>
            <TextInput
                style={styles.input}
                placeholder="중량을 입력하세요"
                keyboardType="numeric"
                onChangeText={text => setRecordedWeight(parseInt(text))}
            />
            <TouchableOpacity style={styles.recordButton} onPress={handleRecord}>
                <Text style={styles.recordButtonText}>확인</Text>
            </TouchableOpacity>
        </View>

                </View>
            </ScrollView>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
    },
    buttonContainer: {
        flexDirection: 'row', 
        justifyContent: 'center', 
        marginTop: 50,
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
        flexDirection: 'row',
      },
      selectedOption: {
        flexDirection: 'row',
        backgroundColor: '#A1BFDD',
      },
      optionText: {
        fontWeight: 'bold',
        color: 'black',
        fontSize: 16,
      },

      setText: {
        fontWeight: '900',
        color: 'black',
        fontSize: 16,
        marginLeft: 140,
        marginTop: 60,
      },

      recordcontainer:{
        flex: 1,
        padding: 20,
      }, 

      label: {
        fontWeight: 'bold',
        fontSize: 16,
        marginBottom: 5,
        color: 'black',
        marginLeft: 30,
    },
    input: {
        borderRadius: 8,
        padding: 10,
        marginBottom: 20,
        backgroundColor:'#D8E8F2',
        width: 300,
        marginLeft: 30,
    },
    recordButton: {
        backgroundColor: '#A1BFDD',
        borderRadius: 8,
        padding: 10,
        alignItems: 'center',
        width: 100,
        marginLeft: 130,
        marginTop: 70,
    },
    recordButtonText: {
        fontWeight: 'bold',
        color: 'black',
    },



});

export default ExerciseRecordSet;