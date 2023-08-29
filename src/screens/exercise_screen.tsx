import React, {useState} from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native';
import { currentDate } from '../data';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { Picker } from '@react-native-picker/picker';
import RNPickerSelect from 'react-native-picker-select';

export default function ExerciseScreen({navigation} : any) {
    const [pickerValue, setPickerValue] = useState("1");
    return (
        <View style={styles.view}>
            <Icon name='dumbbell' size={200} color={Colors.blue900} style={styles.icon}/>

            <View style={styles.picker}>
                    <Picker
                            selectedValue={pickerValue}
                            onValueChange={(item) => setPickerValue(item)}
                        >
                        <Picker.Item label="검색할 옵션을 선택해주세요" value="1" />
                        <Picker.Item label="test1" value="2" />    
                        <Picker.Item label="test2" value="3" />        
                    </Picker>         
            </View> 

            <Text style={styles.text}>또는</Text>
            
            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {Alert.alert('운동 등록 Dialogue')}}>새로운 운동 등록하기</Text>
            </TouchableOpacity>
            
            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {navigation.navigate('운동 기록하기')}}>위 운동으로 시작</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center'},
    icon : {marginBottom: 40, borderRadius: 100, backgroundColor: 'white', padding: 20, shadowColor: 'black', shadowOffset: {width: 0, height: 10}},
    date : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center', fontSize: 25},
    view : {padding : 20, flex : 1, justifyContent : 'center', alignItems: 'center'},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 5, 
        borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 10, width: 270},
    picker : {backgroundColor: Colors.white, borderRadius: 10, borderWidth: 3, 
        borderColor: Colors.blue500, height : 40, width: 270, justifyContent: 'center', marginBottom: 10},
})