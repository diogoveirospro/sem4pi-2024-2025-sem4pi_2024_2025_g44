package Shodrone.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Shodrone.DTO.CustomerDTO;
import Shodrone.DTO.ShodroneUserDTO;
import Shodrone.DTO.ShowDTO;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.requests.GetCustomerOfRepresentativeRequest;
import Shodrone.requests.GetShodroneUserRequest;
import Shodrone.requests.GetShowsOfCustomerRequest;
import core.Persistence.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerAppProtocolProxy {
	private static final Logger LOGGER = LogManager.getLogger(CustomerAppProtocolProxy.class);

	private static class ClientSocket {
		private Socket sock;
		private PrintWriter output;
		private BufferedReader input;
		private final String address = Application.settings().serverIP();
		private final String port = Application.settings().serverPort();

		public void connect() throws IOException {
			InetAddress serverIP = InetAddress.getByName(address);
			int serverPort = Integer.parseInt(port);
			sock = new Socket(serverIP, serverPort);
			output = new PrintWriter(sock.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			LOGGER.debug("Connected to {}", serverIP);
		}

		public void send(String request) {
			output.println(request);
			LOGGER.debug("Sent message\n-----\n{}\n-----", request);
		}

		public List<String> recv() throws IOException {
			List<String> response = new ArrayList<>();
			String line;
			while ((line = input.readLine()) != null && !line.isEmpty()) {
				response.add(line);
			}
			LOGGER.debug("Received message:\n----\n{}\n----", response);
			return response;
		}

		public List<String> sendAndRecv(String request) throws IOException {
			send(request);
			return recv();
		}

		public void stop() throws IOException {
			input.close();
			output.close();
			sock.close();
		}
	}

	public ShodroneUserDTO getShodroneUser(String username) throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetShodroneUserRequest(username).toRequest();
		final List<String> response = socket.sendAndRecv(request);
		socket.stop();
		final MarshlerUnmarshler mu = new MarshlerUnmarshler();
		System.out.println("Response: " + response);
		return mu.parseResponseMessageShodroneUser(response);
	}

	public CustomerDTO getCustomers(String email) throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetCustomerOfRepresentativeRequest(email).toRequest();
		final List<String> response = socket.sendAndRecv(request);
		socket.stop();
		final MarshlerUnmarshler mu = new MarshlerUnmarshler();
		return mu.parseResponseMessageCustomer(response);
	}

	public Iterable<ShowDTO> getShows(String customerVatNumber) throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetShowsOfCustomerRequest(customerVatNumber).toRequest();
		final List<String> response = socket.sendAndRecv(request);
		socket.stop();
		final MarshlerUnmarshler mu = new MarshlerUnmarshler();
		return mu.parseResponseMessageShow(response);
	}
}