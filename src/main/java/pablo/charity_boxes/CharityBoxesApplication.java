package pablo.charity_boxes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pablo.charity_boxes.fundraising_event.FundraisingEvent;
import pablo.charity_boxes.fundraising_event.FundraisingEventRepository;

import java.util.Scanner;

@SpringBootApplication
public class CharityBoxesApplication {

	public static void main(String[] args) throws InterruptedException{
		ApplicationContext context = SpringApplication.run(CharityBoxesApplication.class, args);

		FundraisingEventRepository fundraisingRepository = context.getBean(FundraisingEventRepository.class);
		Scanner scanner = new Scanner(System.in);

		System.out.println("F / Q");

		// For debug only
		while (true) {
			String input = scanner.nextLine();
			if (input.equals("f")) {
				System.out.println("Fundraising events and boxes:");

				for (FundraisingEvent event : fundraisingRepository.findAll()) {
					System.out.println("FE: " + event.getName());

					if (event.getBoxes() != null && !event.getBoxes().isEmpty()) {
						event.getBoxes().forEach(box ->
								System.out.println("  Box: " + box.getName() + ", Amount: " + box.getAmount()));
					} else {
						System.out.println("No boxes");
					}
				}
			}
		}
	}
}
