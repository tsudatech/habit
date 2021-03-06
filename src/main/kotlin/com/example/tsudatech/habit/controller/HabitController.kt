package com.example.tsudatech.habit.controller

import com.example.tsudatech.habit.entity.Habit
import com.example.tsudatech.habit.model.SearchHabitModel
import com.example.tsudatech.habit.repository.HabitRepository
import com.example.tsudatech.habit.repository.HabitRepositoryCustomImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

// https://spring.io/guides/tutorials/rest/
@RestController
class HabitController @Autowired constructor(
        val habitRepository: HabitRepository,
        val habitRepositoryCustomImpl: HabitRepositoryCustomImpl)
{
  // curl localhost:8080/habit/
  @GetMapping("/habit")
  fun all(): ResponseEntity<Iterable<Habit?>> {
    val habits = habitRepository.findAllByOrderByHabitId()
    return ResponseEntity.ok(habits)
  }

  // curl -X POST localhost:8080/habit/search -H "Content-type:application/json" -d "{\"habitName\": \"Hello New World\"}"
  @PostMapping("/habit/search")
  fun search(@RequestBody @Validated searchModel: SearchHabitModel,
             bindingResult: BindingResult): ResponseEntity<Any> {
    if(bindingResult.hasErrors()){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.allErrors.toList())
    }
    val habits = habitRepositoryCustomImpl.findHabitsWithSearchCondition(searchModel)
    return ResponseEntity.ok(habits)
  }

  // curl -X POST localhost:8080/habit/ -H "Content-type:application/json" -d "{\"habitName\": \"Hello New World\"}"
  @PostMapping("/habit/")
  fun newHabit(@RequestBody newHabit: Habit): ResponseEntity<Habit> {
    return ResponseEntity.ok(habitRepository.save(newHabit))
  }

  // curl -X PUT localhost:8080/habit/1 -H "Content-type:application/json" -d "{\"habitName\": \"Hello World\"}"
  @PutMapping("/habit/{id}")
  fun replaceHabit(@RequestBody newHabit: Habit, @PathVariable id: Long): ResponseEntity<Habit> {
    return ResponseEntity.ok(habitRepository.findById(id).map{ habit ->
      habit.habitName = newHabit.habitName
      habit.updateDateTime = LocalDateTime.now()
      habitRepository.save(habit)
    }.orElseGet { ->
      habitRepository.save(Habit(newHabit.habitName))
    })
  }

  // curl -X DELETE localhost:8080/habit/5
  @DeleteMapping("/habit/{id}")
  fun deleteHabit(@PathVariable id: Long) {
    habitRepository.deleteById(id);
  }
}


