package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException{
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel.");
		System.out.print("Modelo do carro: ");
		String carModel = input.nextLine();
		System.out.print("Data e hora em que será retirado o carro (DD/MM/YYYY HH:MM): ");
		Date start = sdf.parse(input.nextLine());
		System.out.print("Data e hora em que será devolvido o carro (DD/MM/YYYY HH:MM): ");
		Date finish = sdf.parse(input.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o preço por hora do aluguel: ");
		double pricePerHour = input.nextDouble();
		System.out.print("Entre com o preço por dia de aluguel: ");
		double pricePerDay = input.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("\nFATURA:");
		System.out.println("Pagamento base: R$ " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa: R$ " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total: R$ " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		input.close();
		
	}
}
