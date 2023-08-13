import { Text, View, StyleSheet, TextInput } from 'react-native'
import Icon from 'react-native-vector-icons/MaterialCommunityIcons'
import { MD2Colors as Colors } from 'react-native-paper'
import { TouchableOpacity } from 'react-native-gesture-handler';

export default function SignUp({navigation} : any) {
  return (
    <View style={[styles.view]}>
      <View>

        <View style={styles.title}>
            <Text style={styles.bold}>REGISTER TO</Text>
            <Text style={styles.regular}>HELF Project</Text>
        </View>

        <View>
          <TextInput style={styles.textInput_1} placeholder="Email"/>
        </View>

        <TouchableOpacity>
          <Text style={styles.button} onPress = {()=>navigation.navigate('SignUp_Password')}> NEXT  
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

  title: {fontSize: 30, fontFamily: 'SEBANG_Gothic_Bold', textAlign:'center', marginBottom: 20, flexDirection: 'column'},
  bold: {color: Colors.blue900, fontSize: 40, fontFamily: 'SEBANG_Gothic_Bold', textAlign:'center'},
  regular: {color: Colors.blue900, fontSize: 20, fontFamily: 'SEBANG_Gothic_Bold', textAlign:'center', paddingTop: 25, paddingLeft: 10},

  textInput_1: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 3, borderRadius: 10, 
    padding: 10, marginLeft: '10%', marginRight: '10%', marginTop: 15, fontSize: 15, borderColor: Colors.blue500},

  textInput_2: {fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: 'white', borderWidth: 3, borderRadius: 10, marginTop: 15, fontSize: 15,
    width: '35%', padding: 10},

  button: {color: Colors.white, fontSize: 20, fontFamily: 'SEBANG_Gothic_Bold', backgroundColor: Colors.blue500, 
    borderRadius: 10, padding: 10, marginLeft: '10%', marginRight: '10%', marginTop: 15, textAlign: 'center'},

  instruction: {fontFamily: 'SEBANG_Gothic_Bold', justifyContent: 'center', marginTop: 15, fontSize: 15, paddingHorizontal: 50},
})