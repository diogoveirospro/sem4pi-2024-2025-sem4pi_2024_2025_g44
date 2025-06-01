package Shodrone.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import core.Persistence.AppSettings;
import core.Persistence.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Proxy for the CsvBookingProtocol.
 *
 * @author Paulo Gandra de Sousa 2021.05.25
 */
public class CustomerAppServer {
	private static final Logger LOGGER = LogManager.getLogger(CustomerAppServer.class);

	/**
	 * @author Paulo Gandra de Sousa 2021.05.25
	 */
	private static class ClientSocket {

		private Socket sock;
		private PrintWriter output;
		private BufferedReader input;
		private final String address = Application.settings().databaseIP();
		private final String port = Application.settings().databasePort();

		public void connect() throws IOException {
			InetAddress serverIP;
			int serverPort;
			serverIP = InetAddress.getByName(address);
			serverPort = Integer.parseInt(port);
			sock = new Socket(serverIP, serverPort);
			output = new PrintWriter(sock.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			LOGGER.debug("Connected to {}", serverIP);
		}

		/**
		 * @param request
		 */
		public void send(final String request) {
			output.println(request);
			LOGGER.debug("Sent message\n-----\n{}\n-----", request);
		}

		public List<String> recv() throws IOException {
			final var resp = new ArrayList<String>();

			var eof = false;
			do {
				final String inputLine = input.readLine();
				if (inputLine != null) {
					if (inputLine.equals("")) {
						eof = true;
					} else {
						resp.add(inputLine);
					}
				}
			} while (!eof);

			LOGGER.debug("Received message:\n----\n{}\n----", resp);

			return resp;
		}

		/**
		 * @param request
		 *
		 * @return
		 *
		 * @throws IOException
		 */
		public List<String> sendAndRecv(final String request) throws IOException {
			send(request);
			return recv();
		}

		/**
		 * @throws IOException
		 */
		public void stop() throws IOException {
			input.close();
			output.close();
			sock.close();
		}
	}

	/**
	 * @param theDay
	 * @param mealType
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	/*
	public Iterable<MealDTO> getAvailableMeals(final Calendar theDay, final String mealType)
			throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect(getAddress(), getPort());

		final String request = new GetAvailableMealsRequestDTO(theDay, mealType).toRequest();
		final List<String> response = socket.sendAndRecv(request);

		socket.stop();

		final MarshlerUnmarshler mu = new MarshlerUnmarshler();
		return mu.parseResponseMessageGetAvailableMeals(response);
	}
	*/
	/**
	 *
	 * @param identity
	 * @param id
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws FailedRequestException
	 */
	/*
	public BookingTokenDTO bookMeal(final String identity, final Long id, String password)
			throws IOException, FailedRequestException {
		// FIXME username and password are clear text. this is just for demo purposes
		// and MUST NOT be done like this in production code

		final var socket = new ClientSocket();
		socket.connect(getAddress(), getPort());

		final String request = new BookAMealRequestDTO(identity, id, password).toRequest();
		final List<String> response = socket.sendAndRecv(request);

		socket.stop();

		final MarshlerUnmarshler mu = new MarshlerUnmarshler();
		return mu.parseResponseMessageBookMeal(response);
	}
	*/

}
