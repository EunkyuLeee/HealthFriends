import React from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native';
import { currentDate } from '../data';
import { TextInput, TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { Picker } from '@react-native-picker/picker';
import RNPickerSelect from 'react-native-picker-select';

const today = currentDate()

export default function ExerciseList({navigation}) {
    return (
        <View style={styles.view}>
            <TextInput/>
            
            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {Alert.alert('조회 상세')}}>조회</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center'},
    date : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center', fontSize: 25},
    view : {padding : 20, flex : 1, justifyContent : 'center', alignItems: 'center'},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 5, borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 5}
})