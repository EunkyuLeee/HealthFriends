import React, {useState} from 'react'
import { Text, View, StyleSheet, TextInput, Alert, Button } from 'react-native'
import Icon from 'react-native-vector-icons/MaterialCommunityIcons'
import { MD2Colors as Colors } from 'react-native-paper'
import { TouchableOpacity } from 'react-native-gesture-handler';
import { Picker } from '@react-native-picker/picker';
import RNPickerSelect from 'react-native-picker-select';

export default function SignUp({navigation} : any) {
    const [pickerValue, setPickerValue] = useState("1");
    const [ value, setValue] = useState('ID 중복 확인을 해주세요.');
  return (
    <View style={[styles.view]}>
      <View>

        <View>
          <TextInput style={styles.textInput_id} placeholder="ID"/>
        </View>

        <View style={styles.instruction}>

            <Text>{value}</Text>
          <Button
            title="change Value"
            onPress={() => setValue('사용가능한 ID입니다.')}
          />
        </View>

        <View>
          <TextInput style={styles.textInput_pw} placeholder="PASSWORD"/>
        </View>

        <View>
          <TextInput style={styles.textInput_pw} placeholder="PASSWORD CHECK"/>
        </View>

        <View>
           <TextInput style={styles.textInput_name} placeholder="NAME"/>
        </View>

        <View>
          <TextInput style={styles.textInput_name} placeholder="E-MAIL"/>
        </View>

        <View>
          <TextInput style={styles.textInput_name} placeholder="전화번호"/>
        </View>

        <View>
          <TextInput style={styles.textInput_name} placeholder="생년월일"/>
        </View>

        <TouchableOpacity>
          <Text style={styles.button} onPress = {()=>Alert.alert('회원가입이 완료되었습니다.')}> 가입하기
              <Icon name = 'arrow-right' size = {20}/> 
          </Text>
        </TouchableOpacity>

        <Text style={styles.instruction}> Instructions : Set Your Email. Make sure your emaill address is valid.
        </Text>

      </View>
    </View>
  );
}


const styles = StyleSheet.create({
  view: {backgroundColor: Colors.grey200, flex: 1, justifyContent: 'center'},

  container : { flex: 1, backgroundColor: '#ffffff', alignItems: 'center', justifyContent: 'center'},

  textInput_id: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 1, borderRadius: 10,
    padding: 10, marginLeft: 40, marginRight: 150, marginTop: 15, fontSize: 15, borderColor: "#D9D9D9"},

  textInput_pw: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 1, borderRadius: 10,
    padding: 10, marginLeft: '10%', marginRight: '10%', marginTop: 5, fontSize: 15, borderColor: "#D9D9D9"},

  textInput_name: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 1, borderRadius: 10,
    padding: 10, marginLeft: '10%', marginRight: '10%', marginTop: 15, fontSize: 15, borderColor: "#D9D9D9"},

  textInput_email: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 1, borderRadius: 10,
    padding: 10, marginLeft: 40, marginRight: 150, marginTop: 15, fontSize: 15, borderColor: "#D9D9D9"},

  button: {color: "#000000", fontSize: 20, fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: "#A1BFDD",
    borderRadius: 10, padding: 10, marginLeft: '10%', marginRight: '10%', marginTop: 15, textAlign: 'center'},

  button_IdCheck: {color: "#000000", fontSize: 20, fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: "#D8E8F2",
    borderRadius: 10, padding: 10, marginLeft: 200, marginRight: 10, marginTop: 15, textAlign: 'center'},

  picker : {backgroundColor: "#ffffff", borderRadius: 10, borderWidth: 1,
          borderColor: "#D9D9D9", height : 40, width: 150, marginLeft: 250, marginRight: 100, marginTop: 20},

  instruction: {fontFamily: 'SEBANG_Gothic_Bold', justifyContent: 'center', marginTop: 5, fontSize: 10, paddingHorizontal: 50},
  instruction_2: {fontFamily: 'SEBANG_Gothic_Bold', justifyContent: 'center', marginTop: 5, fontSize: 15, paddingHorizontal: 50}
})