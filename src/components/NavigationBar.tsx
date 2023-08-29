import React, {FC} from 'react';
import { View, StyleSheet, Text, Image } from 'react-native';
import {MD2Colors as Colors} from 'react-native-paper';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons'
import * as Data from '../data'

export type NavigationBarProps = {
    icon_name: string
    page_name: string
    User : Data.UserInfo
    needUser: boolean
}

export const NavigationBar : FC<NavigationBarProps> = ({icon_name, User, page_name, needUser}) => {

    return (
        <View style={[styles.topbar]}>
            <Icon name = {icon_name} style={[styles.icon]} size={40} />
            <Text style={[styles.name]}>{page_name}</Text>
            {needUser && <Image style={[styles.image]} source={{uri: User.profileImage}}/>}   
        </View>
    )
}

const styles = StyleSheet.create({
    topbar: {
        backgroundColor: Colors.blueA100, flexDirection: 'row', padding: 5, justifyContent: 'space-evenly'
    },
    icon: {color: Colors.white, padding: 5},
    name: {fontSize: 30, textAlign: 'center', fontWeight: 'bold', color: Colors.white, flex: 3, padding: 5},
    image: {height: 40, width: 40, borderRadius: 20, backgroundColor: Colors.white, margin: 5}
})
