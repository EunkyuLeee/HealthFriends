import { View, Text, StyleSheet } from 'react-native';
import { currentDate } from '../data';
import { ScrollView, TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';

const today = currentDate()

export default function HomeScreen({navigation} : any) {
    return (
        <ScrollView style={styles.view}>
            <Text style={styles.text}>{today}</Text>
            <Text style={styles.text}>홈 화면입니다.</Text>

            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {navigation.navigate('운동 기록')}}>운동 기록 화면으로</Text>
            </TouchableOpacity>

            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {navigation.navigate('운동 조회')}}>운동 기록 조회 화면으로</Text>
            </TouchableOpacity>

            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {navigation.navigate('운동 조회')}}>식단 조회 화면으로</Text>
            </TouchableOpacity>
        </ScrollView>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center'},
    date : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center', fontSize: 25},
    view : {padding : 20},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 5, borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 5}
})