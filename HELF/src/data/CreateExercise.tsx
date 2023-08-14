import type {ExerciseInfo} from './ExerciseInfo'
import * as Faker from './Random'

export const createExercise = () : ExerciseInfo => {
    return {
        id : Faker.randomNumber(),
        date : new Date,
        name : Faker.randomUsername(),
        repeat : Faker.randomNumber(),
        set : Faker.randomNumber(),
        time : Faker.randomNumber(),
        weight : Faker.randomNumber(),
    }
}