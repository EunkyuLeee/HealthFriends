import {View, Text} from 'react-native'
import { StyleSheet } from "react-native"

export default function CameraScreen() {
    
  return (
    <View>
      <Text style={styles.view}>카메라 페이지입니다.</Text>
    </View>
  )
}
const styles = StyleSheet.create({
    view: {flex: 1, justifyContent: 'center'},
})
