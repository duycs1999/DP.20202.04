package views.screen.invoice;

import common.exception.ProcessInvoiceException;
import controller.PaymentController;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;
import entity.order.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class InvoiceScreenHandler extends BaseScreenHandler {

	private static Logger LOGGER = Utils.getLogger(InvoiceScreenHandler.class.getName());

	@FXML
	private Label pageTitle;

	@FXML
	private Label name;

	@FXML
	private Label phone;

	@FXML
	private Label province;

	@FXML
	private Label address;

	@FXML
	private Label instructions;

	@FXML
	private Label subtotal;

	@FXML
	private Label shippingFees;

	@FXML
	private Label total;

	@FXML
	private VBox vboxItems;

	private Invoice invoice;

	public InvoiceScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath);
		try {
			setupData(invoice);
			setupFunctionality();
		} catch (IOException ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error("Error when loading resources.");
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			PopupScreen.error(ex.getMessage());
		}
	}

	protected void setupData(Object dto) throws Exception {
		this.invoice = (Invoice) dto;
		Order order = invoice.getOrder();
		DeliveryInfo deliveryInfo = order.getDeliveryInfo();

		name.setText(deliveryInfo.getName());
		phone.setText(deliveryInfo.getPhone());
		province.setText(deliveryInfo.getProvince());
		instructions.setText(deliveryInfo.getShippingInstructions());
		address.setText(deliveryInfo.getAddress());

		subtotal.setText(ViewsConfig.getCurrencyFormat(order.getSubtotal()));
		shippingFees.setText(ViewsConfig.getCurrencyFormat(order.getShippingFees()));
		total.setText(ViewsConfig.getCurrencyFormat(order.getTotal()));

		invoice.getOrder().getListOrderMedia().forEach(orderMedia -> {
			try {
				MediaInvoiceScreenHandler mis = new MediaInvoiceScreenHandler(ViewsConfig.INVOICE_MEDIA_SCREEN_PATH);
				mis.setOrderItem((OrderItem) orderMedia);
				vboxItems.getChildren().add(mis.getContent());
			} catch (IOException | SQLException e) {
				System.err.println("errors: " + e.getMessage());
				throw new ProcessInvoiceException(e.getMessage());
			}
		});
	}

	protected void setupFunctionality() throws Exception {
		return;
	}

	@FXML
	void confirmInvoice(MouseEvent event) throws IOException {
		BaseScreenHandler paymentScreen = new PaymentScreenHandler(this.stage, ViewsConfig.PAYMENT_SCREEN_PATH, invoice);
		paymentScreen.setBController(new PaymentController());
		paymentScreen.setPreviousScreen(this);
		paymentScreen.setHomeScreenHandler(homeScreenHandler);
		paymentScreen.setScreenTitle("Payment Screen");
		paymentScreen.show();
		LOGGER.info("Confirmed invoice");
	}
}
