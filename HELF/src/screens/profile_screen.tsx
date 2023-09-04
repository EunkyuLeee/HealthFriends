import React from 'react'
import { View, Text, StyleSheet, Alert } from 'react-native';
import { TextInput, TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';

export default function Profile({navigation} : any) {
    return (
        <View style={styles.view}>
            <View style={styles.head}>
                <TextInput style={styles.input} placeholder="검색할 내용을 입력하세요"/>
                <TouchableOpacity>
                    <Text style={styles.button} onPress={() => {Alert.alert('조회 상세')}}>조회</Text>
                </TouchableOpacity>
            </View>

            <View style={styles.body}>
                
            </View>
            
        </View>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center'},
    input: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 3, borderRadius: 10, 
        padding: 10, borderColor: Colors.blue500, height : 40},
    view : {flex : 1, padding : 20, justifyContent : 'center', alignItems: 'center'},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 10, 
        borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 5, height: 40},
    head : {flex : 1, flexDirection: 'row', alignItems : 'center'},
    body : {flex : 7, backgroundColor : Colors.blue200, borderRadius: 15, width: '95%'}
})