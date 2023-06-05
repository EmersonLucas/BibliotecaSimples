package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Book;
import models.ScenesEnum;
import models.User;
import models.validations.BookValidation;
import models.validations.UserValidation;

public class EmployeeController implements Initializable {
	
	ObservableList<User> listUsers;
	ObservableList<Book> listBooks;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		views.AnimationBorderPane.animationFadeOut(borderPane);
		nameUser.setText(UserValidation.viewNameUser.toUpperCase());
		
		loadDateColumUser();
		loadDateColumBook();		
	}
		
	@FXML
	private TableView<User> tableViewUsers;
	
	@FXML
    private TableColumn<User, String> idUserXML;

    @FXML
    private TableColumn<User, String> isBloqueadoUserXML;
    
    @FXML
    private TableColumn<User, String> cpfUserXML;
    
    @FXML
    private TableColumn<User, String> nomeUserXML;
    
    @FXML
    private TableColumn<User, String> telefoneUserXML;

    @FXML
    private TableColumn<User, String> userEditColum;
	
	@FXML
    private TableColumn<Book, String> autorBookXML;
	
	@FXML
    private TableColumn<Book, String> bookEditColum;

    @FXML
    private TableColumn<Book, String> editoraBookXMl;
    
    @FXML
    private TableColumn<Book, Long> idBookXML;
    
    @FXML
    private TableColumn<Book, String> nomeBookXML;

    @FXML
    private TableColumn<Book, Integer> qunatidadeBookXML;
    
    @FXML
    private TextField nameUser;

    @FXML
    private TableView<Book> tableViewBook;
    
    @FXML
    private TextField autorXML;
   
    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField editoraXML;  

    @FXML
    private TextField nomeXML;

    @FXML
    private TextField quantidadeXML;
 
    @FXML
    private TextField campIdXML;
    
    @FXML
    private TextField bookIdXML;
        
    private void loadDateColumUser(){
		
		idUserXML.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeUserXML.setCellValueFactory(new PropertyValueFactory<>("name"));
		cpfUserXML.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		telefoneUserXML.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
		isBloqueadoUserXML.setCellValueFactory(new PropertyValueFactory<>("blocked"));
		
		List<User> userList = UserValidation.daoOfUser.getAllEntity();
		
		listUsers = FXCollections.observableArrayList(userList);
		
		tableViewUsers.setItems(listUsers);
		
	}
    
    	private void loadDateColumBook(){
		
		idBookXML.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeBookXML.setCellValueFactory(new PropertyValueFactory<>("nome"));
		autorBookXML.setCellValueFactory(new PropertyValueFactory<>("autor"));
		editoraBookXMl.setCellValueFactory(new PropertyValueFactory<>("editora"));
		qunatidadeBookXML.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		
		
		List<Book> bookList = BookValidation.daoOfBook.getAllEntity();
		
		listBooks = FXCollections.observableArrayList(bookList);
		
		tableViewBook.setItems(listBooks);
	}
    	
    @FXML
    void buttonCadastrar(ActionEvent event) throws IOException{
    	if(BookValidation.fieldIsNotEmpty(nomeXML.getText().trim(),
    			autorXML.getText().trim(), editoraXML.getText().trim())){
    		BookValidation.daoOfBook.includeAtomic(createBookObject());
        	PagesControllers.changePage(ScenesEnum.EMPLOYEE);	

    	}
    }

    @FXML
    void buttonAlterar(ActionEvent event) throws IOException {
    	Integer id = Integer.parseInt(bookIdXML.getText().trim());
    	Integer qtd = Integer.parseInt(quantidadeXML.getText().trim());
    	if(id != null && qtd >= 0){
    		if(BookValidation.fieldIsNotEmptyNome(nomeXML.getText().trim()))
    			BookValidation.daoOfBook.editBook(id, nomeXML.getText(), null, null, null);
    		if(BookValidation.fieldIsNotEmptyAutor( autorXML.getText().trim()))
    			BookValidation.daoOfBook.editBook(id, null, autorXML.getText(), null, null);
    		if(BookValidation.fieldIsNotEmptyEditora(editoraXML.getText().trim()))
    			BookValidation.daoOfBook.editBook(id, null, null, editoraXML.getText() , null);
    		if(BookValidation.fieldIsNotEmptyQuantidade(editoraXML.getText().trim()))
    			BookValidation.daoOfBook.editBook(id, null, null, null , qtd);
        PagesControllers.changePage(ScenesEnum.EMPLOYEE);	

    	}
    	
    }
    
    @FXML
    void buttonBloquear(ActionEvent event) throws IOException {
    	int id = Integer.parseInt(campIdXML.getText().trim());
    	UserValidation.daoOfUser.blockUser(id, true);
    	PagesControllers.changePage(ScenesEnum.EMPLOYEE);	

    }
    
    @FXML
    void buttonUnlocked(ActionEvent event) throws IOException{
    	int id = Integer.parseInt(campIdXML.getText().trim());
    	UserValidation.daoOfUser.blockUser(id, false);
    	PagesControllers.changePage(ScenesEnum.EMPLOYEE);	
    }
    
    @FXML
    void buttonExcluir(ActionEvent event) throws IOException{
    	 int id = Integer.parseInt(bookIdXML.getText().trim());
    	 BookValidation.daoOfBook.deleteBook(id);
     	 PagesControllers.changePage(ScenesEnum.EMPLOYEE);	

    }

    @FXML
    void buttonVoltar(ActionEvent event) throws IOException {
    	PagesControllers.changePage(ScenesEnum.LOGIN);	
    }
    
    private Book createBookObject(){
    	Book book = new Book(nomeXML.getText().trim(),
				autorXML.getText().trim(),
				editoraXML.getText().trim(),
				Integer.parseInt(quantidadeXML.getText().trim())); 
		return book;
    }
}
