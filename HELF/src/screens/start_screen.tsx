import {StyleSheet, Text, View, Image} from 'react-native';
import {TouchableOpacity} from 'react-native-gesture-handler';
import axios from "axios"
import {KakaoOAuthToken, login, loginWithKakaoAccount} from "@react-native-seoul/kakao-login";

export default function StartScreen({navigation}: any) {

  const Url = "http://10.0.2.2:8080"

  const signInWithKakao = async (): Promise<void> => {
    const accessToken: KakaoOAuthToken = await loginWithKakaoAccount();
    const res = await axios.post(Url + "/auth/kakao", accessToken, {headers: { "User-Agent": "Mozilla/5.0" }})
        .then((response) => {
          console.log(response)
          navigation.navigate('Main', {userToken : response.data.appToken})
        })
        .catch((e) => {
          console.log(e)
        });

  }

  return (
    <View style={styles.view}>
      <TouchableOpacity>
        <Text
          style={styles.button_login}
          onPress={() => {
            signInWithKakao()
          }}>
          카카오로 로그인
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  view: {
    backgroundColor: '#ffffff',
    justifyContent: 'center',
    flex: 1,
    alignItems: 'center',
  },
  button_login: {
    color: '#000000',
    fontSize: 24,
    backgroundColor: '#fef01b',
    padding: 5,
    borderRadius: 10,
    fontFamily: 'SEBANG_Gothic',
    marginLeft: '10%',
    marginRight: '10%',
    marginBottom: 20,
    textAlign: 'center',
  },
});
