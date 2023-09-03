import {  StyleSheet, Text, View, Image } from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';

declare module '*.png' {
  const value: any;
  export default value;
}

export default function StartScreen({navigation} : any) {

    return (
        <View style={styles.view}>
            <View>
                <Image
                source={require('../assets/ic_launcher.png')}
                style={{ width: 300, height: 150}}
                />
            </View>

            <TouchableOpacity>
                <Text style={styles.button_login} onPress={() => {
                    navigation.navigate('Home')
            }}>카카오 로그인</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    view : {backgroundColor: '#ffffff', justifyContent: 'center', flex: 1, alignItems: 'center'},
    button_login : {color: '#000000', fontSize: 20, backgroundColor: '#fef01b', padding: 5, borderRadius: 10, width: 200, height: 30, fontFamily: 'SEBANG_Gothic',
         justifyContent: 'center', marginTop: 40, textAlign: 'center'}
})
