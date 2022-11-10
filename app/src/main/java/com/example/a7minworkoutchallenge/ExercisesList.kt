package com.example.a7minworkoutchallenge

class ExercisesList {

    companion object{

        fun exerciseList():ArrayList<ExerciseModel>{
         val exerciseModelList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1,"Jumping Jacks",R.drawable.ic_jumping_jacks,false,false)
            exerciseModelList.add(jumpingJacks)
            val abdominalCrunch = ExerciseModel(2,"Abdominal Crunch",R.drawable.ic_abdominal_crunch,false,false)
            exerciseModelList.add(abdominalCrunch)
            val highKneesRunningInPlace = ExerciseModel(3,"High knees Running In Place",R.drawable.ic_high_knees_running_in_place,false,false)
            exerciseModelList.add(highKneesRunningInPlace)
            val lunge = ExerciseModel(4,"Lunge",R.drawable.ic_lunge,false,false)
            exerciseModelList.add(lunge)
            val plank = ExerciseModel(5,"Plank",R.drawable.ic_plank,false,false)
            exerciseModelList.add(plank)
            val pushUp = ExerciseModel(6,"Push Up",R.drawable.ic_push_up,false,false)
            exerciseModelList.add(pushUp)
            val pushUpRotation = ExerciseModel(7,"Push Up And Rotation",R.drawable.ic_push_up_and_rotation,false,false)
            exerciseModelList.add(pushUpRotation)
            val sidePlank = ExerciseModel(8,"Side Plank",R.drawable.ic_side_plank,false,false)
            exerciseModelList.add(sidePlank)
            val squat = ExerciseModel(9,"Squat",R.drawable.ic_squat,false,false)
            exerciseModelList.add(squat)
            val stepUponToChair = ExerciseModel(10,"Step up On to chair",R.drawable.ic_step_up_onto_chair,false,false)
            exerciseModelList.add(stepUponToChair)
            val tricepsDipOnChair = ExerciseModel(11,"Triceps Dip On Chair",R.drawable.ic_triceps_dip_on_chair,false,false)
            exerciseModelList.add(tricepsDipOnChair)
            val wallSit = ExerciseModel(12,"Wall sit",R.drawable.ic_wall_sit,false,false)
            exerciseModelList.add(wallSit)
         return exerciseModelList
        }

    }

}