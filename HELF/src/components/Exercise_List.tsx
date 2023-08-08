import {FC} from 'react'
import { View, Text, StyleSheet } from 'react-native'

export type listComponent = {
    date : Date,
    exercise : string,
}

export const ExerciseList : FC<listComponent> = ({date, exercise}) => {
    return (
        <View>
            <Text></Text>
        </View>
    )
}