package com.technoles.api.contoller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired

import com.technoles.api.model.User
import com.technoles.api.repository.UserRepository

@RestController
class WebController {

	@Autowired
	lateinit var repository: UserRepository

	@RequestMapping("/save")
	fun save(): String {
		repository.save(User("Jack"))
		repository.save(User("Adam"))
		repository.save(User("Kim"))
		repository.save(User("David"))
		repository.save(User("Peter"))

		return "Done"
	}

	@RequestMapping("/findall")
	fun findAll() = repository.findAll()

	@RequestMapping("/findbyid/{id}")
	fun findById(@PathVariable id: Long)
			= repository.findById(id)

	@RequestMapping("findbylastname/{lastName}")
	fun findByLastName(@PathVariable lastName: String)
			= repository.findByName(lastName)
	
}