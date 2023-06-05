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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Book;
import models.MyBooks;
import models.ScenesEnum;
import models.validations.BookValidation;
import models.validations.UserValidation;

public class ClientController implements Initializable{
	
	ObservableList<Book> listBooks;
	ObservableList<MyBooks> listMyBooks;
	
	@FXML
	private TextField nameUser;
	
	@FXML
    private BorderPane borderPane;
	
	@FXML
	private Button buttonEmprestar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		views.AnimationBorderPane.animationFadeOut(borderPane);
		nameUser.setText(UserValidation.viewNameUser.toUpperCase());
		
		if (UserValidation.user.isBlocked())buttonEmprestar.setDisable(true);
		loadColumMyBooks();
		loadDateColumBook();		
	}
	
	@FXML
    private TableView<Book> tableViewBook;

	@FXML
    private TableColumn<Book, Long> idBookXML;
	
	@FXML
    private TableColumn<Book, String> nomeBookXML;
	
	@FXML
    private TableColumn<Book, String> autorBookXML;

    @FXML
    private TableColumn<Book, String> editoraBookXMl;
    
    @FXML
    private TableColumn<Book, Integer> qunatidadeBookXML;
    
    @FXML
    private TextField bookIdXML;
    
    @FXML
    private TextField nomeXML;
    
    @FXML
    private TextField quantidadeXML;
    
    @FXML
    private TableView<MyBooks> collumMybooks;
    
    @FXML
    private TableColumn<MyBooks, String> idMybooks;
    
    @FXML
    private TableColumn<MyBooks, String> nomeMybooks;
    
    @FXML
    private TableColumn<MyBooks, Integer> qtdMybooks;
    
    @FXML
    private TextField bookIdDevolver;
    	
    @FXML
    void buttonDevolver(ActionEvent event)throws IOException {
    	try {
    		Long id = Long.parseLong(bookIdDevolver.getText().trim());
    		BookValidation.daoOfMybooks.removeBookMyList(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	PagesControllers.changePage(ScenesEnum.CLIENT);	
    }

    @FXML
    void buttonVoltar(ActionEvent event) throws IOException {  	
    	PagesControllers.changePage(ScenesEnum.LOGIN);	
    }

    @FXML
    void actionEmprestar(ActionEvent event)throws IOException {  
    	
    	try {
			Long id = Long.parseLong(bookIdXML.getText().trim());
			Integer qtd = Integer.parseInt(quantidadeXML.getText().trim());
			if (!UserValidation.user.isBlocked()) {
				UserValidation.daoOfUser.includeMyBook(id, qtd);
			}		
	    	PagesControllers.changePage(ScenesEnum.CLIENT);
		} catch (Exception e) {
	    	PagesControllers.changePage(ScenesEnum.CLIENT);
		}
    	
    	
	}
    
    private void loadColumMyBooks(){
    	
    	idMybooks.setCellValueFactory(new PropertyValueFactory<>("id"));
		nomeMybooks.setCellValueFactory(new PropertyValueFactory<>("nome"));
		qtdMybooks.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		
		List<MyBooks> myBookList = BookValidation.daoOfMybooks.getMyBooks(UserValidation.user.getId());
		
		listMyBooks = FXCollections.observableArrayList(myBookList);
		
		collumMybooks.setItems(listMyBooks);
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
}
