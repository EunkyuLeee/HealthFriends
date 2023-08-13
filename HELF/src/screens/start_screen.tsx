import { View, Text, StyleSheet } from 'react-native'
import { TouchableOpacity } from 'react-native-gesture-handler'
import { MD2Colors as Colors } from 'react-native-paper'


export default function StartScreen({navigation} : any) {
    return (
        <View style={styles.view}>
            <Text>시작 화면(로그인 화면)입니다.</Text>
            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {
                    navigation.navigate('Home')
            }}>GO TO HOME SCREEN</Text>
            </TouchableOpacity>
            <TouchableOpacity>
                <Text style={styles.button} onPress={() => {navigation.navigate('SignUp')}}>SIGN UP</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
    view : {justifyContent: 'center', flex: 1, alignItems: 'center'},
    button : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue400, padding: 5, borderRadius: 10, fontFamily: 'SEBANG_Gothic'}
})
