package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import common.exception.ViewCartException;
import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.*;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.cart.CartScreenHandler;
import views.screen.popup.PopupScreen;


public class HomeScreenHandler extends BaseScreenHandler implements Observer {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;

    @FXML
    private HBox hboxMedia;

    @FXML
    private Button btnLogin;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    private List homeItems;
    private AuthenticationController authenticationController;

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
        try {
            setupData(null);
            setupFunctionality();
        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error("Error when loading resources.");
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }

    public Label getNumMediaCartLabel(){
        return this.numMediaInCart;
    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    protected void setupData(Object dto) throws Exception {
        setBController(new HomeController());
        this.authenticationController = new AuthenticationController();
        try{
            List medium = getBController().getAllMedia();
            this.homeItems = new ArrayList<>();
            for (Object object : medium) {
                Media media = (Media)object;
                MediaHandler m = new MediaHandler(ViewsConfig.HOME_MEDIA_PATH, media);
                m.attach(this);
                this.homeItems.add(m);
            }
        } catch (SQLException | IOException e){
            LOGGER.info("Errors occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void setupFunctionality() throws Exception {

        aimsImage.setOnMouseClicked(e -> {
            addMediaHome(this.homeItems);
        });

        cartImage.setOnMouseClicked(e -> {
            CartScreenHandler cartScreen;
            try {
                LOGGER.info("User clicked to view cart");
                cartScreen = new CartScreenHandler(this.stage, ViewsConfig.CART_SCREEN_PATH);
                cartScreen.setHomeScreenHandler(this);
                cartScreen.setBController(new ViewCartController());
                cartScreen.requestToViewCart(this);
            } catch (IOException | SQLException e1) {
                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }
        });
        addMediaHome(this.homeItems);
        addMenuItem(0, "Book", splitMenuBtnSearch);
        addMenuItem(1, "DVD", splitMenuBtnSearch);
        addMenuItem(2, "CD", splitMenuBtnSearch);
    }

    @Override
    public void show() {
        if (authenticationController.isAnonymousSession()) {
            btnLogin.setText("Login");
            btnLogin.setOnMouseClicked(event -> redirectLoginScreen(event));
        } else {
            btnLogin.setText("User");
            btnLogin.setOnMouseClicked(event -> {});
        }

        numMediaInCart.setText(String.valueOf(SessionInformation.cartInstance.getListMedia().size()) + " media");
        super.show();
    }

    public void setImage() {
        // fix image path caused by fxml
        File file1 = new File(ViewsConfig.IMAGE_PATH + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
        aimsImage.setImage(img1);

        File file2 = new File(ViewsConfig.IMAGE_PATH + "/" + "cart.png");
        Image img2 = new Image(file2.toURI().toString());
        cartImage.setImage(img2);
    }

    public void addMediaHome(List items){
        ArrayList mediaItems = (ArrayList)((ArrayList) items).clone();
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while(!mediaItems.isEmpty()){
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !mediaItems.isEmpty()){
                    MediaHandler media = (MediaHandler) mediaItems.get(0);
                    vBox.getChildren().add(media.getContent());
                    mediaItems.remove(media);
                }
            });
            return;
        }
    }

    private void addMenuItem(int position, String text, MenuButton menuButton){
        MenuItem menuItem = new MenuItem();
        Label label = new Label();
        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
        label.setText(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        menuItem.setGraphic(label);
        menuItem.setOnAction(e -> {
            // empty home media
            hboxMedia.getChildren().forEach(node -> {
                VBox vBox = (VBox) node;
                vBox.getChildren().clear();
            });

            // filter only media with the choosen category
            List filteredItems = new ArrayList<>();
            homeItems.forEach(me -> {
                MediaHandler media = (MediaHandler) me;
                if (media.getMedia().getTitle().toLowerCase().startsWith(text.toLowerCase())){
                    filteredItems.add(media);
                }
            });

            // fill out the home with filted media as category
            addMediaHome(filteredItems);
        });
        menuButton.getItems().add(position, menuItem);
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof MediaHandler) update((MediaHandler) observable);
    }

    private void update(MediaHandler mediaHandler) {
        int requestQuantity = mediaHandler.getRequestQuantity();
        Media media = mediaHandler.getMedia();

        try {
            if (requestQuantity > media.getQuantity()) throw new MediaNotAvailableException();
            Cart cart = SessionInformation.cartInstance;
            // if media already in cart then we will increase the quantity by 1 instead of create the new cartMedia
            CartItem mediaInCart = getBController().checkMediaInCart(media);
            if (mediaInCart != null) {
                mediaInCart.setQuantity(mediaInCart.getQuantity() + 1);
            } else {
                CartItem cartItem = new CartItem(media, cart, requestQuantity, media.getPrice());
                cart.addCartMedia(cartItem);
                LOGGER.info("Added " + cartItem.getQuantity() + " " + media.getTitle() + " to cart");
            }

            // subtract the quantity and redisplay
            media.setQuantity(media.getQuantity() - requestQuantity);
            numMediaInCart.setText(cart.getTotalMedia() + " media");
            PopupScreen.success("The media " + media.getTitle() + " added to Cart");
        } catch (MediaNotAvailableException exp) {
            try {
                String message = "Not enough media:\nRequired: " + requestQuantity + "\nAvail: " + media.getQuantity();
                LOGGER.severe(message);
                PopupScreen.error(message);
            } catch (Exception e) {
                LOGGER.severe("Cannot add media to cart: ");
            }

        } catch (Exception exp) {
            LOGGER.severe("Cannot add media to cart: ");
            exp.printStackTrace();
        }
    }

    @FXML
    void redirectLoginScreen(MouseEvent event) {
        try {
            BaseScreenHandler loginScreen = new LoginScreenHandler(this.stage, ViewsConfig.LOGIN_SCREEN_PATH);
            loginScreen.setHomeScreenHandler(this);
            loginScreen.setBController(this.authenticationController);
            loginScreen.show();
        } catch (Exception ex) {
            try {
                PopupScreen.error("Cant trigger Login");
            } catch (Exception ex1) {
                LOGGER.severe("Cannot login");
                ex.printStackTrace();
            }
        }
    }
}
