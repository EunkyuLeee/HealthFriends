import React from 'react';
import { View, TouchableOpacity, Image, Text, StyleSheet } from 'react-native';

let arrowPath = require('./Arrow.png');

interface ToolbarProps {
    title: string;
    backgroundColor: string;
    onArrowPress: () => void;
    arrowPath: number;
  }
  


const Toolbar: React.FC<ToolbarProps> = ({ title, backgroundColor, onArrowPress }) => {
    return (
      <View style={[styles.toolbar, { backgroundColor }]}>
        <TouchableOpacity onPress={onArrowPress} style={styles.arrowButton}>  
          <Image source={arrowPath} style={styles.arrowImage} />
        </TouchableOpacity>
        <Text style={styles.toolbarTitle}>{title}</Text> 
      </View>
    );
  };

  const styles = StyleSheet.create({
    toolbar: {
      flexDirection: 'row',
      alignItems: 'center',
      paddingHorizontal: 10,
      height: 60,
    },
    toolbarTitle: {
      flex: 1,
      textAlign: 'center',
      fontWeight: 'bold',
      fontSize: 16,
      color: 'white'
    },
    arrowButton: {
      width: 40,
      height: 40,
      justifyContent: 'center',
      alignItems: 'center',
    },
    arrowImage: {
      width: 30,
      height: 20,
    },
  });
  
  export default Toolbar;