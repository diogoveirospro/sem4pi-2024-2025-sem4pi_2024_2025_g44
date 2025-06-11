package Shodrone.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Shodrone.DTO.*;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.requests.*;
import core.Persistence.Application;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ShowProposal.domain.Entities.ShowProposal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Proxy for the Customer App Protocol, handling communication with the server.
 */
public class CustomerAppProtocolProxy {

	/**
	 * Logger for the CustomerAppProtocolProxy class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(CustomerAppProtocolProxy.class);

	/**
	 * ClientSocket class handles the connection to the server, sending and receiving messages.
	 */
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

		public List<String> receive() throws IOException {
			List<String> response = new ArrayList<>();
			String line;
			while ((line = input.readLine()) != null && !line.isEmpty()) {
				response.add(line);
			}
			LOGGER.debug("Received message:\n----\n{}\n----", response);
			return response;
		}

		public List<String> sendAndReceive(String request) throws IOException {
			send(request);
			return receive();
		}

		public void stop() throws IOException {
			input.close();
			output.close();
			sock.close();
		}
	}

	/**
	 * Retrieves a Shodrone user by username.
	 *
	 * @param username the username of the Shodrone user
	 * @return ShodroneUserDTO containing user details
	 * @throws IOException if an I/O error occurs
	 * @throws FailedRequestException if the request fails
	 */
	public ShodroneUserDTO getShodroneUser(String username) throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetShodroneUserRequest(username).toRequest();
		final List<String> response = socket.sendAndReceive(request);
		socket.stop();
		final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
		System.out.println("Response: " + response);
		return mu.parseResponseMessageShodroneUser(response);
	}

	/**
	 * Retrieves the customer associated with a representative by email.
	 *
	 * @param email the email of the representative
	 * @return CustomerDTO containing customer details
	 * @throws IOException if an I/O error occurs
	 * @throws FailedRequestException if the request fails
	 */
	public CustomerDTO getCustomerOfRepresentative(String email) throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetCustomerOfRepresentativeRequest(email).toRequest();
		final List<String> response = socket.sendAndReceive(request);
		socket.stop();
		final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
		return mu.parseResponseMessageCustomer(response);
	}

	/**
	 * Retrieves the shows associated with a customer by VAT number.
	 *
	 * @param customerVatNumber the VAT number of the customer
	 * @return Iterable of ShowDTO containing show details
	 * @throws IOException if an I/O error occurs
	 * @throws FailedRequestException if the request fails
	 */
	public Iterable<ShowDTO> getShows(String customerVatNumber) throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetShowsOfCustomerRequest(customerVatNumber).toRequest();
		final List<String> response = socket.sendAndReceive(request);
		socket.stop();
		final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
		return mu.parseResponseMessageShow(response);
	}

	/**
	 * Retrieves proposals delivered to a customer by VAT number.
	 *
	 * @param customerVatNumber the VAT number of the customer
	 * @return Iterable of ShowProposalDTO containing proposal details
	 * @throws IOException if an I/O error occurs
	 * @throws FailedRequestException if the request fails
	 */
	public Iterable<ShowProposalDTO> getProposalsDelivered(String customerVatNumber)
			throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new GetProposalsOfCustomerRequest(customerVatNumber).toRequest();
		final List<String> response = socket.sendAndReceive(request);
		socket.stop();
		final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
		return mu.parseResponseMessageProposal(response);
	}

	/**
	 * Sends feedback on a proposal identified by its proposal number.
	 * @param proposalNumber the proposal number to identify the proposal
	 * @param decision the decision on the proposal (e.g., ACCEPTED, REJECTED)
	 * @param feedback the feedback text to provide on the proposal
	 * @return true if the feedback was successfully sent, false otherwise
	 * @throws IOException if an I/O error occurs
	 * @throws FailedRequestException if the request fails
	 */
	public boolean sendFeedbackProposal(String proposalNumber, String decision, String feedback)
			throws IOException, FailedRequestException {
		final var socket = new ClientSocket();
		socket.connect();
		final String request = new SendFeedbackProposalRequest(proposalNumber, decision, feedback).toRequest();
		final List<String> response = socket.sendAndReceive(request);
		socket.stop();
		final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
		return mu.parseResponseMessageFeedback(response);
	}

	public ProposalDeliveryInfoCode getProposalDeliveryInfoCode(String proposalNumber) {
		try {
			final var socket = new ClientSocket();
			socket.connect();
			final String request = new GetProposalDeliveryCodeRequest(proposalNumber).toRequest();
			final List<String> response = socket.sendAndReceive(request);
			socket.stop();
			final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
			return mu.parseResponseMessageProposalDeliveryInfoCode(response);
		} catch (IOException | FailedRequestException e) {
			LOGGER.error("Error retrieving proposal delivery info code: {}", e.getMessage());
			return null;
		}
	}

	public ShowProposalDTO getProposalByCode(String code) {
		try {
			final var socket = new ClientSocket();
			socket.connect();
			final String request = new GetProposalByCodeRequest(code).toRequest();
			final List<String> response = socket.sendAndReceive(request);
			socket.stop();
			final MarshallerUnmarshaller mu = new MarshallerUnmarshaller();
			return mu.parseResponseMessageProposalByCode(response);
		} catch (IOException | FailedRequestException e) {
			LOGGER.error("Error retrieving proposal delivery info code: {}", e.getMessage());
			return null;
		}
	}
}