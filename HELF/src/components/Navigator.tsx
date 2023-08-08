import {createStackNavigator} from '@react-navigation/stack';
import { View, Text, Image, StyleSheet, Alert } from 'react-native';
import { TouchableOpacity } from 'react-native-gesture-handler';
import StartScreen from '../screens/start_screen';
import HomeScreen from '../screens/home_screen';
import * as Data from '../data';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { MD2Colors as Colors } from 'react-native-paper';

export const User = Data.createUserInfo()
const Stack = createStackNavigator()
const onPress = () => {Alert.alert('Menu pressed')}

export default function Navigator(){
    return (
        <Stack.Navigator>
            <Stack.Screen name='Start' component={StartScreen} options={{headerShown: false}}/>
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
        </Stack.Navigator>
    )
}

const styles = StyleSheet.create({
    image : {width: 40, height: 40, borderRadius: 20, margin : 10},
    icon : {margin : 10}
})