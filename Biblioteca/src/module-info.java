/**
 * 
 */
/**
 * @author Emerson
 *
 */
module Biblioteca {
	requires javafx.controls;
	requires javafx.fxml;
	requires jakarta.persistence;

	opens models;
	opens controllers;
	exports controllers;
	
}