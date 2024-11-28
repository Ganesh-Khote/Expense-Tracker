package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Expense {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 Long id;
 String title;
 String description;
 String category;
 
 LocalDate date;
 Integer amount;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public Integer getAmount() {
	return amount;
}
public void setAmount(Integer amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "Expense [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
			+ ", date=" + date + ", amount=" + amount + "]";
}
public Expense() {
	super();
	// TODO Auto-generated constructor stub
}
public Expense(Long id, String title, String description, String category, LocalDate date, Integer amount) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.category = category;
	this.date = date;
	this.amount = amount;
}
 
 

}
