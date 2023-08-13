import {useState} from 'react'
import {createStackNavigator} from '@react-navigation/stack';
import { View, Text, Image, StyleSheet, Alert } from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
import StartScreen from '../screens/start_screen';
import HomeScreen from '../screens/home_screen';
import * as Data from '../data';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { MD2Colors as Colors } from 'react-native-paper';
import ExerciseScreen from '../screens/exercise_screen';
import ExerciseList from '../screens/exercise_list';
import ExerciseStart from '../screens/exercise_start';
import SignUp from '../screens/sign_up';
import CameraScreen from '../screens/camera_screen';
import Profile from '../screens/profile_screen';

export const User = Data.createUserInfo()
const Stack = createStackNavigator()


export default function Navigator({navigation} : any){
    const [ isLogined, setLogin ] = useState(false);

    return (
        <Stack.Navigator>
            <Stack.Screen name='Start' component={StartScreen} options={{headerShown: false}}/>
            <Stack.Screen name='SignUp' component={SignUp} options={{headerShown: false}}/>
            <Stack.Screen name='Home' component={HomeScreen} options={{
                headerLeft: ({onPress}) => (
                    <TouchableOpacity onPress={() => {Alert.alert('Menu pressed')}}>
                        <Icon style={styles.icon} name='menu' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerRight: () => (
                    <View>
                        <Image style={styles.image} source={{uri : User.profileImage}}/>
                    </View>
                ),
                headerTintColor: '#ffffff',
                headerStyle: { 
                    backgroundColor: Colors.blue400,
                },
                headerTitleStyle: {
                    fontFamily: 'SEBANG_Gothic_Bold',
                    fontSize: 20,
                },
                headerTitleAlign: 'center'
            }}/>
            <Stack.Screen name='운동 기록' component={ExerciseScreen} options={{
                headerLeft: ({onPress}) => (
                    <TouchableOpacity onPress={onPress}>
                        <Icon style={styles.icon} name='arrow-left' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerTintColor: '#ffffff',
                headerStyle: { 
                    backgroundColor: Colors.blue400,
                },
                headerTitleStyle: {
                    fontFamily: 'SEBANG_Gothic_Bold',
                    fontSize: 20,
                },
                headerTitleAlign: 'center'
            }}/>
            <Stack.Screen name='운동 조회' component={ExerciseList} options={{
                headerLeft: ({onPress}) => (
                    <TouchableOpacity onPress = {onPress}>
                        <Icon style={styles.icon} name='arrow-left' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerTintColor: '#ffffff',
                headerStyle: { 
                    backgroundColor: Colors.blue400,
                },
                headerTitleStyle: {
                    fontFamily: 'SEBANG_Gothic_Bold',
                    fontSize: 20,
                },
                headerTitleAlign: 'center'
            }}/>
            <Stack.Screen name='운동 기록하기' component={ExerciseStart} options={{
                headerLeft: ({onPress}) => (
                    <TouchableOpacity onPress = {onPress}>
                        <Icon style={styles.icon} name='arrow-left' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerRight: () => (
                    <TouchableOpacity>
                        <Icon style={styles.icon} name='camera' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerTintColor: '#ffffff',
                headerStyle: { 
                    backgroundColor: Colors.blue400,
                },
                headerTitleStyle: {
                    fontFamily: 'SEBANG_Gothic_Bold',
                    fontSize: 20,
                },
                headerTitleAlign: 'center'
            }}/>
            <Stack.Screen name='Camera' component={CameraScreen} options={{
                headerLeft: ({onPress}) => (
                    <TouchableOpacity>
                        <Icon style={styles.icon} name='arrow-left' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerTintColor: '#ffffff',
                headerStyle: { 
                    backgroundColor: Colors.blue400,
                },
                headerTitleStyle: {
                    fontFamily: 'SEBANG_Gothic_Bold',
                    fontSize: 20,
                },
                headerTitleAlign: 'center'
            }}/>
            <Stack.Screen name='Profile' component={Profile} options={{
                headerLeft: ({onPress}) => (
                    <TouchableOpacity onPress={() => {onPress}}>
                        <Icon style={styles.icon} name='arrow-left' size={25} color={Colors.white}/>
                    </TouchableOpacity>
                ),
                headerTintColor: '#ffffff',
                headerStyle: { 
                    backgroundColor: Colors.blue400,
                },
                headerTitleStyle: {
                    fontFamily: 'SEBANG_Gothic_Bold',
                    fontSize: 20,
                },
                headerTitleAlign: 'center'
            }}/>
        </Stack.Navigator>
    )
}

const styles = StyleSheet.create({
    image : {width: 40, height: 40, borderRadius: 20, margin : 10},
    icon : {margin : 10}
})