package com.design.bookmyshow;

import com.design.bookmyshow.controllers.*;
import com.design.bookmyshow.dtos.CreateUserRequestDto;
import com.design.bookmyshow.models.Language;
import com.design.bookmyshow.models.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BookmyshowApplication implements CommandLineRunner {

	private UserController userController;
	private CityController cityController;
	private TheatreController theatreController;
	private ShowController showController;
	private TicketController ticketController;

	@Autowired
	public BookmyshowApplication(UserController userController,
								 CityController cityController,
								 TheatreController theatreController,
								 ShowController showController,
								 TicketController ticketController
	) {
		this.userController = userController;
		this.cityController = cityController;
		this.theatreController = theatreController;
		this.showController = showController;
		this.ticketController = ticketController;
	}


	public static void main(String[] args) {
		SpringApplication.run(BookmyshowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateUserRequestDto requestDto = new CreateUserRequestDto();
		requestDto.setEmail("abc.gmail.com");

		this.userController.createUser(requestDto);
		this.cityController.addCity("Chandigarh");
		this.theatreController.createTheatre(
				"PVR",
				"ABC Road, Chandigarh",
				1L
		);

		this.theatreController.addAuditorium(1L, "Audi 1", 123);

		Map<SeatType, Integer> seatsForAudi = new HashMap<>();
		seatsForAudi.put(SeatType.VIP, 20);
		seatsForAudi.put(SeatType.GOLD, 100);

		this.theatreController.addSeats(1L, seatsForAudi);

		this.showController.createShow(
				0L,
				new Date(),
				new Date(),
				1L,
				null,
				Language.ENGLISH
		);

		TicketBookRunner ticketBookRunner1 = new TicketBookRunner(
				this.ticketController,
				3L,
				List.of(58L, 59L, 60L),
				1L
		);

		TicketBookRunner ticketBookRunner2 = new TicketBookRunner(
				this.ticketController,
				3L,
				List.of(60L, 61L, 62L),
				1L
		);

		Thread t1 = new Thread(ticketBookRunner1);
		Thread t2 = new Thread(ticketBookRunner2);
		t1.start();
		t2.start();
	}

}
