import {FC} from 'react'
import { View, Text, StyleSheet } from 'react-native'
import { ExerciseInfo } from '../data/ExerciseInfo'
import { MD2Colors as Colors } from 'react-native-paper';

export type ExerciseProps = {
    exercise : ExerciseInfo
}

export const ListBox : FC<ExerciseProps> = ({exercise}) => {

    return (
        <View style={styles.view}>
            <Text style={styles.text}>{exercise.date.toDateString()}</Text>
            <Text style={styles.text}>{exercise.name}</Text>
            <Text style={styles.text}>{exercise.repeat}</Text>
            <Text style={styles.text}>{exercise.id}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    view : {flex : 1, flexDirection : 'column', justifyContent : 'center', alignContent: 'space-between', 
        margin: 10, padding: 10, borderRadius: 20, backgroundColor: Colors.blue700},
    text : {color : Colors.white, fontFamily: 'SEBANG_Gothic'}
})