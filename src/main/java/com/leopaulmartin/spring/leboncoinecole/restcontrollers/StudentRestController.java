package com.leopaulmartin.spring.leboncoinecole.restcontrollers;

import com.leopaulmartin.spring.leboncoinecole.persistence.entities.Student;
import com.leopaulmartin.spring.leboncoinecole.persistence.repositories.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRestController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public List<Student> list() {
		return studentRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{id}")
	public Student get(@PathVariable Long id) {
//		if (!studentRepository.existsById(id)) throw new StudentNotfoundException();
		return studentRepository.getOne(id);
	}

	@PostMapping
	public Student create(@RequestBody final Student student) {
//		List<PhoneNumber> phoneNumbers = student.getPhoneNumber();
//		for (PhoneNumber phoneNumber :
//				phoneNumbers) {
//			phoneNumberRepository.saveAndFlush(phoneNumber);
//		}

		return studentRepository.saveAndFlush(student);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		// also need to check for children records before deleting.
		studentRepository.deleteById(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Student update(@PathVariable Long id, @RequestBody Student student) {
		// PATCH will focus on one attribut, PUT will expect all attributes to be passed in.
		//TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
		Student existingStudent = studentRepository.getOne(id);
		BeanUtils.copyProperties(student, existingStudent, "student_id");
		return studentRepository.saveAndFlush(existingStudent);
	}
}
