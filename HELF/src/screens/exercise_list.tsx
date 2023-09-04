import React, {useState} from 'react'
import { View, Text, StyleSheet, Alert, SafeAreaView, FlatList } from 'react-native';
import { currentDate, makeArray } from '../data';
import { ScrollView, TextInput, TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';
import * as Data from '../data';
import { Picker } from '@react-native-picker/picker';
import { ListBox } from '../components/ListBox';


const array = Data.makeArray(50).map(Data.createExercise)
// test data입니다.

export default function ExerciseList({navigation} : any) {
    const [pickerValue, setPickerValue] = useState("1");
    const moveDetails = () => {navigation.navigate('Details')}

    return (
        <View style={styles.view}>
            <View style={styles.head}>
                <View style={styles.picker}>
                    <Picker
                            selectedValue={pickerValue}
                            onValueChange={(item) => setPickerValue(item)}
                        >
                        <Picker.Item label="필터 옵션" value="1" />
                        <Picker.Item label="test1" value="2" />    
                        <Picker.Item label="test2" value="3" />        
                    </Picker>         
                </View>    
                <TouchableOpacity>
                    <Text style={styles.button} onPress={() => {Alert.alert('조회 상세')}}>조회</Text>
                </TouchableOpacity>  
            </View>
            
            <View style={styles.body}>
                <FlatList
                data = {array}
                renderItem={({item}) => <TouchableOpacity onPress={moveDetails}><ListBox exercise={item}/></TouchableOpacity>}
                windowSize={7}
                keyExtractor={(item,index) => String(index)}/>
            </View>
            
        </View>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center'},
    view : {flex : 1, padding : 20, justifyContent : 'center', alignItems: 'center'},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 10, 
        borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 5, height: 40},
    head : {flexDirection: 'row', alignItems : 'center', marginBottom: 10},
    body : {flex: 1, backgroundColor : Colors.blue100, borderRadius: 15, width: '95%'},
    picker : {backgroundColor: Colors.white, borderRadius: 10, borderWidth: 3, 
        borderColor: Colors.blue500, height : 40, width: 270, justifyContent: 'center'},
})