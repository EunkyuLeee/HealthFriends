import {  StyleSheet, Text, View, Image } from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';

export default function StartScreen({navigation} : any) {

    return (
        <View style={styles.view}>
            <TouchableOpacity>
                <Text style={styles.button_login} onPress={() => {
                    navigation.navigate('Home')
            }}>카카오로 로그인</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    view : {backgroundColor: '#ffffff', justifyContent: 'center', flex: 1, alignItems: 'center'},
    button_login : {color: '#000000', fontSize: 24, backgroundColor: '#fef01b', padding: 5, borderRadius: 10, fontFamily: 'SEBANG_Gothic',
        marginLeft: '10%', marginRight: '10%', marginBottom: 20, textAlign: 'center'}
})
