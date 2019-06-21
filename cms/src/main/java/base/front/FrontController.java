package base.front;

import com.jfinal.core.Controller;

public class FrontController extends Controller {
    public void index(){
    	render("login.html");
    }
}
