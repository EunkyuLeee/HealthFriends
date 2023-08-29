import { View, Text, StyleSheet, Share, Alert } from 'react-native';
import { currentDate } from '../data';
import { ScrollView, TouchableOpacity } from 'react-native-gesture-handler';
import { MD2Colors as Colors } from 'react-native-paper';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons'

const today = currentDate()
const onShare = async () => {
    try {
      const result = await Share.share(
        {
          message: '당신의 운동 기록을 공유하세요!',
        } 
      );

      if (result.action === Share.sharedAction) {
        if (result.activityType) {
          console.log('activityType!');
        } else {
          console.log('Share!');
        }
      } else if (result.action === Share.dismissedAction) {
        console.log('dismissed');
      }
    } catch (error) {
      Alert.alert(error.message);
    }
  };

export default function ExerciseDetails({navigation} : any) {
    return (
        <ScrollView style={styles.view}>
            <TouchableOpacity style={styles.share}>
                <Text onPress={() => onShare()} style={styles.text}>
                    공유하기
                    <Icon name = 'share' size = {25}/> 
                </Text>
            </TouchableOpacity>
            
        </ScrollView>
    )
}

const styles = StyleSheet.create({
    text : {fontFamily: 'SEBANG_Gothic_Bold', textAlign: 'center', fontSize:20},
    view : {padding : 20},
    share : {color: Colors.white, fontSize: 20, backgroundColor: Colors.blue200, padding: 5, 
        borderRadius: 10, fontFamily: 'SEBANG_Gothic', textAlign: 'center', margin: 5, justifyContent: 'center', alignContent: 'center'},
})