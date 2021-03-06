package pl.edu.agh.ki.mwo.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.persistence.UserDatabase;

@Controller
public class LoginController {

	@RequestMapping(value={"/", "/Login"}, method=RequestMethod.GET)
	public String displayLoginForm() {
	        return "loginForm";
	   }

    @RequestMapping(value="/Login", method=RequestMethod.POST)
    public String doLogin(@RequestParam(value="login") String login, String pass, Model model, HttpSession session) 
    {
		UserDatabase.getInstance().addUser("AnWie17", "Antoni", "Wieretelny", "male", 1977, 1, 1, "MIT", "Testing", "anton@mail.com", "San Francico 11200", "+44 12345678", false, false, false, "wiara");
		
		UserDatabase.getInstance().addUser("AnSta22", "Anna", "Stańczyk", "female", 1922, 2, 5, "", "", "", "", "", true, false, false, "radość");
		UserDatabase.getInstance().addUser("CzPrz36", "Czesław", "Przywra", "male", 1966, 2, 6, "", "", "", "", "", true, false, false, "destrukcja");
		UserDatabase.getInstance().addUser("HeTra48", "Hermeneutyk", "Trapiński", "male", 1988, 2, 7, "", "", "", "", "", true, false, false, "zmęczenie");
		UserDatabase.getInstance().addUser("KaSla54", "Katarzyna", "Slajdowska", "female", 1944, 2, 8, "", "", "", "", "", true, false, false, "outlook");
		UserDatabase.getInstance().addUser("AnGwi63", "Andrzej", "Gwiazda", "male", 1933, 2, 9, "", "", "", "", "", true, false, false, "oślepienie");
		
		UserDatabase.getInstance().addUser("tadek", "Tadeusz", "Żebro", "male", 1950, 3, 11, "", "", "", "", "", false, false, true, "słowa");
		UserDatabase.getInstance().addUser("admin", "Adminisław", "Adminowski", "other", 1960, 3, 12, "", "", "", "", "", false, false, true, "admin");
		UserDatabase.getInstance().addUser("beatka", "Beata", "Wcisło", "male", 1940, 3, 13, "", "", "", "", "", false, false, true, "nadzieja");
		
		UserDatabase.getInstance().addUser("GrzLe96", "Grzegorz", "Lektor", "male", 1965, 4, 16, "", "", "", "", "", false, true, false, "czytanie");
		UserDatabase.getInstance().addUser("BeWci04", "Beata", "Błyskawica", "female", 1945, 4, 17, "", "", "", "", "", false, true, false, "szybkość");
		UserDatabase.getInstance().addUser("ZeArt13", "Zenon", "Artkowski", "male", 1935, 4, 18, "", "", "", "", "", false, true, false, "sztuka");
		UserDatabase.getInstance().addUser("JeRom22", "Jerzy", "Romantyk", "male", 1925, 4, 19, "", "", "", "", "", false, true, false, "serce");
		
		UserDatabase.getInstance().addUser("artek", "Arthur", "Omnibus", "male", 1932, 5, 21, "UW", "Programming", "i_250@wp.pl", "Warszawa, Marszałkowska 7/13", "48880321060", true, true, true, "wiedza");
		UserDatabase.getInstance().addUser("jarek", "Jarosław", "Naczelny", "other", 1962, 5, 22, "PK", "Hardware", "e-mail@domena.com", "Kraków, ul. Czarnowiejska 90", "122661209", true, true, true, "potęga");
		
		UserDatabase.getInstance().addUser("g0", "Arthur", "Omnibus", "male", 1939, 6, 26, "", "", "", "", "", true, true, true, "infinity");
		UserDatabase.getInstance().addUser("jarek0", "0", "Smętny", "male", 1969, 6, 27,  "", "", "", "", "", true, true, true, "power");
		UserDatabase.getInstance().addUser("JeRom00", "Jerzy", "R", "male", 1929, 6, 28, "", "", "", "", "", false, true, false, "force");
		UserDatabase.getInstance().addUser("Mar00", "Martyneliusz", "Jędrak", "female", 1939, 6, 29, "", "", "", "", "", true, true, true, "pot");
		UserDatabase.getInstance().addUser("Han0", "Hania", "Piskorz", "", 1929, 6, 30, "", "", "", "", "", true, true, true, "fishes");
    	
		session.setAttribute("userCount", UserDatabase.getInstance().getUserCount());
		//if(login.equals("admin") && pass.equals("admin")){
    	if(UserDatabase.getInstance().getLoginCorrect(login, pass)){
    		//session.setAttribute("userCount", UserDatabase.getInstance().getUserCount());
    		////session.setAttribute("userCount", UserDatabase.getInstance().getUserCount());
    		//model.addAttribute("userCount", UserDatabase.getInstance().getUserCount());
    		session.setAttribute("userLogin", login);
    		session.setAttribute("loggedIn", UserDatabase.getInstance().getLoginCorrect(login, pass));
    		session.setAttribute("isPrelegent", UserDatabase.getInstance().getCurrentUserIsPrelegent());
    		session.setAttribute("isRecenzent", UserDatabase.getInstance().getCurrentUserIsRecenzent());
    		session.setAttribute("isAdmin", UserDatabase.getInstance().getCurrentUserIsAdministrator());
    		return "redirect:/Welcome";
    	}
    	else
    		return "redirect:/Login";
    }
    
    @RequestMapping(value="/Logout")
    public String doLogout(Model model, HttpSession session) {
    	session.removeAttribute("userCount");
    	session.removeAttribute("userLogin");
    	session.removeAttribute("loggedIn");
    	session.removeAttribute("isPrelegent");
    	session.removeAttribute("isRecenzent");
    	session.removeAttribute("isAdmin");
    	return "redirect:/Login";
    }

    @RequestMapping(value="/Welcome")
    public String welcome(Model model, HttpSession session) 
    {
    	model.addAttribute("message", "Witamy w systemie Konferencja+ wersja 0.1");
        return "welcome";
    }
}