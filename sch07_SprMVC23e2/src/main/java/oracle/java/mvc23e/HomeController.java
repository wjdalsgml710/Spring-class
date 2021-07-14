package oracle.java.mvc23e;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oracle.java.mvc23e.command.ITicketCommand;
import oracle.java.mvc23e.command.TicketCommand;
import oracle.java.mvc23e.dao.TicketDao;
import oracle.java.mvc23e.dto.TicketDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	private TicketDao dao;
	private ITicketCommand ticketCommand;
	
//	@Autowired
//	public void setDao(TicketDao dao) {
//		this.dao = dao;
//	}
	
	@Autowired // 빈 인스턴스 생성 이후, 그 인스턴스를 매핑해서 모두 다 편하게 쓸수있게 하기 위한 것
	public void setTicketCommand(ITicketCommand ticketCommand) {
		this.ticketCommand = ticketCommand;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
//		return "home";
		return "buy_ticket";
	}
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		System.out.println("/buy_ticket");
		return "buy_ticket";
		
	}
	
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(TicketDto ticketDto, Model model) {
		System.out.println( "buy_ticket_card" );
		System.out.println( "ticketDto : " + ticketDto.getConsumerId() );
		System.out.println( "ticketDto : " + ticketDto.getAmount() );
		
//		dao.buyTicket(ticketDto);
		
		ticketCommand.execute(ticketDto);
		
		model.addAttribute("ticketInfo", ticketDto);
		
		return "buy_ticket_end";
	}
	
}
