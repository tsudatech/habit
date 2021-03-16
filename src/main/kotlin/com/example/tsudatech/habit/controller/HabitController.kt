package com.example.tsudatech.habit.controller

import com.example.tsudatech.habit.entity.Habit
import com.example.tsudatech.habit.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class HabitController @Autowired constructor(
        val habitRepository: HabitRepository)
{

  @GetMapping("/habit")
  fun all(): ResponseEntity<Iterable<Habit>> {
    val habits = habitRepository.findAllByOrderByHabitId()
    return ResponseEntity.ok(habits)
  }

  // curl -X PUT localhost:8080/habit/1 -H "Content-type:application/json" -d "{\"habitName\": \"Hello World\"}"
  @PutMapping("/habit/{id}")
  fun newHabit(@RequestBody newHabit: Habit, @PathVariable id: Long): ResponseEntity<Habit> {
    return ResponseEntity.ok(habitRepository.findById(id).map{ habit ->
      habit.habitName = newHabit.habitName
      habitRepository.save(habit)
    }.orElseGet { ->
      habitRepository.save(Habit(newHabit.habitName))
    })
  }


}


