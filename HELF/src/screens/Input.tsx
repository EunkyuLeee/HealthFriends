import React, {useState} from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native';
import { currentDate } from '../data';
import { TextInput, TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';

export default function Input() {
    const [title, setTitle] = useState('test')

    return (
        <View style={styles.view}>
            <Text style={styles.instruction}>몇 회씩 몇 세트를 했는지 기록하세요 : </Text>
            <TextInput style={styles.input} placeholder='횟수 기록'/>
            <TextInput style={styles.input} placeholder='세트 수 기록'/>
            <TextInput style={styles.input} placeholder='중량 기록'/>
            <TouchableOpacity>
                <Text style={styles.button}> 
                    기록하기
                </Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    view : {flex: 1, alignItems: 'center', justifyContent: 'center'},
    instruction : {color : 'black', fontFamily: 'SEBANG_Gothic_Bold', fontSize: 15},
    input : {fontFamily: 'SEBANG_Gothic', borderRadius : 10, borderColor: Colors.blue400, 
        fontSize : 15, backgroundColor: 'white', borderWidth: 3, width: '60%' ,height: 45, margin: 10},
    button: {color: Colors.white, fontSize: 20, fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: Colors.blue500, 
        borderRadius: 10, padding: 10, marginLeft: '10%', marginRight: '10%', marginTop: 15, textAlign: 'center'},
})