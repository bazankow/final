package rocket.app.view;


import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable{

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox cboxLoanTerm = new ComboBox();
	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblLoanTerm;
	@FXML
	private Label lblMorgagePayment;
	@FXML
	private Label lblCalculatedPayment;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private Button btnCalculate;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setdAmount(Double.parseDouble(txtCreditScore.getText()));
		lq.setdAmount(Double.parseDouble(txtExpenses.getText()));
		lq.setdAmount(Double.parseDouble(txtIncome.getText()));
		String strLoanTerm = (String)cboxLoanTerm.getSelectionModel().getSelectedItem();
		int loanTerm = Integer.parseInt(strLoanTerm);
		lq.setiTerm(loanTerm);
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		double payment = RateBLL.getPayment(lRequest.getdRate()/100/12, lRequest.getiTerm()*12, lRequest.getdAmount(), 0, false);
		lRequest.setdPayment(payment);
		
		if(lRequest.getdIncome()*0.28<lRequest.getdPayment()){
			lblErrorMessage.setText("You cannot afford this morgage");
		}
		if(lRequest.getdIncome()*0.36-lRequest.getdExpenses()<lRequest.getdPayment()){
			lblErrorMessage.setText("You cannot afford this morgage");
		}else{
			lblCalculatedPayment.setText(String.valueOf(lRequest.getdPayment()));
		}
	}
	
	public void HandleRateException(){
		lblErrorMessage.setText("Credit Score below required minimum");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		this.cboxLoanTerm.getItems().addAll("15","30");
	}
}
