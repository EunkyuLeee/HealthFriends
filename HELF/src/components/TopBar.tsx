import React from 'react';
import { View, StyleSheet, Text} from 'react-native';
import {MD2Colors as Colors} from 'react-native-paper';

export default function TopBar() {

    const page_name = 'TopBar';

    return (
        <View style={[styles.topbar]}>

            <Text style={[styles.name]}>{page_name}</Text>

        </View>
    )
}

const styles = StyleSheet.create({
    topbar: {
        backgroundColor: Colors.blue500,
    },
    name: {fontSize: 50}
})