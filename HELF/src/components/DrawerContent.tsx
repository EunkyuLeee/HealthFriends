import type { DrawerContentComponentProps } from "@react-navigation/drawer";
import {FC} from 'react';
import {Text} from 'react-native';
import { SafeAreaView } from "react-native-safe-area-context";

const title = 'Drawer Page'

const DrawerContent: FC<DrawerContentComponentProps> = (props) => {
    return (
        <SafeAreaView>
            <Text>Drawer test</Text>
        </SafeAreaView>
    )
}