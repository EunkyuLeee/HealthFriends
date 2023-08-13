import React from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native';
import { currentDate } from '../data';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { Picker } from '@react-native-picker/picker';
import RNPickerSelect from 'react-native-picker-select';

const name = '운동 이름'
const isStarted = false
const today = currentDate()

export default function Timer() {
    return (
        <View style={styles.view}>
            <Icon name='timer' size={200} color={Colors.blue900}/>

            <Text style={styles.timer}>00:00:00</Text>

            <Text style={styles.inform}>{name}의 운동 시간을 기록합니다.</Text>

            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {Alert.alert('운동 등록 Dialogue')}}>기록 시작하기</Text>
            </TouchableOpacity>

            {isStarted &&
            <TouchableOpacity>
                <Text style={styles.button} onPress={()=>{}}>일시정지</Text>
            </TouchableOpacity>
            }

            {!isStarted &&
            <TouchableOpacity>
                <Text style={styles.button} onPress={()=>{}}>운동 기록 저장하기</Text>
            </TouchableOpacity>
            }
        </View>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center'},
    timer : {fontFamily: 'SEBANG_Gothic_Bold', fontSize: 45, marginBottom: 20},
    inform : {fontFamily: 'SEBANG_Gothic', fontSize: 15, marginBottom: 20},
    view : {padding : 20, flex : 1, justifyContent : 'center', alignItems: 'center'},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 5, borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 5}
})