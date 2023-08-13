import type {ExerciseInfo} from './ExerciseInfo'
import * as Faker from './Random'

export const createExercise = () : ExerciseInfo => {
    return {
        date : new Date,
        name : Faker.randomUsername(),
        info : Faker.randomNumber()
    }
}